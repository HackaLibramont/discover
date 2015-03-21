package com.springapp.mvc.dao;

import com.mysql.jdbc.Statement;
import com.springapp.mvc.data.activity.Activity;
import com.springapp.mvc.data.activity.Stay;
import com.springapp.mvc.data.utils.Schedule;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
            while (res.next()) {
                if (stay == null) {
                    stay = new Stay(res.getLong("staId"), res.getTimestamp("staStart"), res.getTimestamp("staEnd"));
                } else if (res.getLong("staId") != stay.getId()) {
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
        } finally {
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
            while (res.next()) {
                if (result == null) {
                    result = new Stay(res.getLong("staId"), res.getTimestamp("staStart"), res.getTimestamp("staEnd"));
                }
                result.addActivity(new Schedule(res.getLong("schId"), res.getTimestamp("schStart"), res.getTimestamp("schEnd")), actDao.find(res.getLong("schAct"), lang));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
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
    public void insert(Stay entry) {
        PreparedStatement statementSta = null;
        PreparedStatement statementSch = null;
        Stay result = null;
        StringBuilder sqlSta = new StringBuilder();
        sqlSta.append("insert into stay(start, end) values (?, ?)");
        StringBuilder sqlSch = new StringBuilder();
        sqlSch.append("insert into schedule(start, end, activity_id, stay_id) values (?, ?, ?, ?)");
        try {
            statementSta = AbstractDAO.connection().prepareStatement(sqlSta.toString(), Statement.RETURN_GENERATED_KEYS);
            statementSta.setTimestamp(1, entry.getStart());
            statementSta.setTimestamp(2, entry.getEnd());
            statementSta.executeUpdate();
            ResultSet res = statementSta.getGeneratedKeys();
            if (res.next())
                entry.setId(res.getLong("GENERATED_KEY"));
            for (Map.Entry<Schedule, Activity> schedule : entry) {
                try {
                    statementSch = AbstractDAO.connection().prepareStatement(sqlSch.toString(), Statement.RETURN_GENERATED_KEYS);
                    statementSch.setTimestamp(1, schedule.getKey().start());
                    statementSch.setTimestamp(2, schedule.getKey().end());
                    statementSch.setLong(3, schedule.getValue().getId());
                    statementSch.setLong(4, entry.getId());
                    statementSch.executeUpdate();
                    res = statementSch.getGeneratedKeys();
                    if (res.next())
                        schedule.getKey().setId(res.getLong("GENERATED_KEY"));
                } catch (SQLException e) {
                    throw (e);
                } finally {
                    try {
                        if (statementSch != null)
                            statementSch.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection().rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                if (statementSta != null)
                    statementSta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Stay entries) {
        this.delete(entries.getId());
        this.insert(entries);
    }

    @Override
    public Stay delete(Long id) {
        PreparedStatement statementSta = null;
        PreparedStatement statementSch = null;
        StringBuilder sqlSta = new StringBuilder();
        sqlSta.append("delete from stay where id=?");
        StringBuilder sqlSch = new StringBuilder();
        sqlSch.append("delete from schedule where stay_id=?");
        try {
            statementSch = AbstractDAO.connection().prepareStatement(sqlSch.toString());
            statementSch.executeQuery();
            statementSta = AbstractDAO.connection().prepareStatement(sqlSta.toString());
            statementSta.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection().rollback();
            } catch (SQLException e1) {
                e1.
                        printStackTrace();
            }
        } finally {
            try {
                if (statementSch != null)
                    statementSch.close();
                if (statementSta != null)
                    statementSta.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
