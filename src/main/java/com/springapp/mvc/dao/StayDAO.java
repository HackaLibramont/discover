package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.CategoryName;
import com.springapp.mvc.data.activity.Stay;
import com.springapp.mvc.data.utils.Schedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 21/03/2015.
 */
public class StayDAO extends AbstractDAO<Stay, Long> {

    @Override
    public List<Stay> findAll() {
        return this.findAll(LANG.EN);
    }

    @Override
    public List<Stay> findAll(LANG lang) {
        ActivityDAO actDao = new ActivityDAO();
        PreparedStatement statement = null;
        List<Stay> result = new ArrayList<Stay>();
        StringBuilder sql = new StringBuilder();
        sql.append("select sta.id staId, sta.start staStart, sta.end staEnd");
        sql.append("     , sch.id schId, sch.start schStart, sch.end schEnd, sch.activity_id schAct");
        sql.append("  from stay sta, schedule sch");
        sql.append(" where sch.stay_id = sta.id");
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            ResultSet res = statement.executeQuery();
            Stay stay = null;
            while (res.next())
            {
                if (stay == null)
                {
                    stay = new Stay(res.getLong("staId"), res.getTimestamp("staStart"), res.getTimestamp("staEnd"));
                }
                else if (res.getLong("staId") != stay.getId())
                {
                    result.add(stay);
                    stay = new Stay(res.getLong("staId"), res.getTimestamp("staStart"), res.getTimestamp("staEnd"));
                }
                stay.addActivity(new Schedule(res.getLong("schId"), res.getTimestamp("schStart"), res.getTimestamp("schEnd")), actDao.find(res.getLong("schAct"), lang));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally
        {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Stay find(Long id) {
        return this.find(id, LANG.EN);
    }

    @Override
    public Stay find(Long id, LANG lang) {
        ActivityDAO actDao = new ActivityDAO();
        PreparedStatement statement = null;
        Stay result = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select sta.id staId, sta.start staStart, sta.end staEnd");
        sql.append("     , sch.id schId, sch.start schStart, sch.end schEnd, sch.activity_id schAct");
        sql.append("  from stay sta, schedule sch");
        sql.append(" where sta.id = ?");
        sql.append("       sch.stay_id = sta.id");
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {

            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        finally
        {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public void insert(Stay entries) {

    }

    @Override
    public void update(Stay entries) {

    }

    @Override
    public Stay delete(Long id) {
        return null;
    }
}
