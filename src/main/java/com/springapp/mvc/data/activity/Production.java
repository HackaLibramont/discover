package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nicolas on 21/03/2015 at 09:40.
 */
public class Production extends Activity
{
    private Timestamp start;
    private Timestamp end;

    public Production(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY, String media)
    {
        super(id, n, location, mainContact, geoX, geoY, media);
    }

    public Production(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY, Timestamp start, Timestamp end, String media)
    {
        super(id, n, location, mainContact, geoX, geoY, media);
        this.start = start;
        this.end = end;
    }

    public void setStart(Timestamp start)
    {
        this.start = start;
    }

    public void setEnd(Timestamp end)
    {
        this.end = end;
    }

    @Override
    public Timestamp getStart()
    {
        return start;
    }

    @Override
    public Timestamp getEnd()
    {
        return end;
    }

    @Override
    public Category getCategory()
    {
        return null;
    }
}
