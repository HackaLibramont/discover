package com.springapp.mvc.dao.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Nathan on 21/03/2015.
 */
public class Filter {



    public  abstract class FilterLine {

        private final String dbColName;

        protected FilterLine(String dbColName)
        {
            this.dbColName = dbColName;
        }

        protected String getDbColName()
        {
            return this.dbColName;
        }

        public abstract String toWhere();
    }

    public class StringFilterLine extends FilterLine {
        private final List<String> values;

        protected StringFilterLine(String dbColName) {
            super(dbColName);
            this.values = new ArrayList<String>();
        }

        public void addValue(String value) {
            if (!this.values.contains(value))
                this.values.add(value);
        }

        public String toWhere() {
            if (values.size() == 0)
                return "";
            if (values.size() == 1)
                return " " + this.getDbColName() + " = " + values.get(0);
            else {
                StringBuilder str = new StringBuilder(this.getDbColName() + " in (");
                for (String value : values) {
                    if (values.indexOf(value) != 0)
                        str.append(",");
                    str.append(value);
                }
                str.append(")");
                return str.toString();
            }
        }
    }

    public class QuadriFilterLine extends FilterLine {
        private final String dbColName2;
        private double minX;
        private double minY;
        private double maxX;
        private double maxY;

        protected QuadriFilterLine(String dbColName, String dbColName2, double minX, double minY, double maxX, double maxY) {
            super(dbColName);
            this.dbColName2 = dbColName2;
            this.minX = minX;
            this.minY = minY;
            this.maxX = maxX;
            this.maxY = maxY;
        }

        public String toWhere() {
            return this.getDbColName() + " > " + this.minX + " AND " + this.getDbColName() + " < " + this.maxX + " AND " + dbColName2 + " > " + this.minY + " AND " + dbColName2 + " < " + this.maxY;
        }
    }

    private final Map<String, FilterLine> lines;

    public Filter()
    {
        this.lines = new HashMap<String, FilterLine>();
    }

    private void addStringFilter(String dbVal, String value)
    {
        StringFilterLine line = (StringFilterLine)this.lines.get(dbVal);
        if (line == null)
        {
            line = new StringFilterLine(dbVal);
            this.lines.put(dbVal, line);
        }
        line.addValue(value);
    }

    public void addQuadriFilter(double minX, double minY, double maxX, double maxY)
    {
        QuadriFilterLine line = new QuadriFilterLine("act.latitude", "act.longitude", minX, minY, maxX, maxY);
            this.lines.put("geoloc", line);

    }

    public void addCategoryFilter(String value)
    {
        this.addStringFilter("cat.id", value);
    }

    public void addSuperCategoryFilter(String value)
    {
        this.addStringFilter("scat.id", value);
    }

    public String toWhere(boolean startWithWhere)
    {
        StringBuilder where = new StringBuilder();
        String separator = " AND ";
        if(startWithWhere)
            separator = " WHERE ";
        for (String key : this.lines.keySet()) {
            where.append(separator);
            separator = " AND ";
            where.append(this.lines.get(key).toWhere());
        }
        where.append(" ");
        return where.toString();

    }

}
