package com.springapp.mvc.dao;


import com.springapp.mvc.data.activity.Activity;
import com.springapp.mvc.data.activity.Contact;
import com.springapp.mvc.data.activity.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 20/03/2015.
 */
public class ActivityDAO extends AbstractDAO<Activity, Long>{


    @Override
    public List<Activity> findAll() {
        return null;
    }

    @Override
    public List<Activity> findAll(LANG lang) {
        List<Activity> result = new ArrayList<Activity>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from category");
        try {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {
                LocationDAO locDao = new LocationDAO();
                Location loc = locDao.find(res.getLong("location_id"));
                ContactDAO contDao = new ContactDAO();
                Contact con = contDao.find(res.getLong("contact_id"));
                //result.add(new Activity(res.getString("name"),loc, con));
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
                connection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Activity find(Long id) {
        return null;
    }

    @Override
    public Activity find(Long id, LANG lang) {
        return null;
    }

    @Override
    public void insert(Activity activity) {

    }

    @Override
    public void update(Activity activity) {

    }

    @Override
    public Activity delete(Long id) {
        return null;
    }

    public void insert(Long id, Long categorie, Long contact, String media, Long location)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into activity values(?, ?, ?, ?, ?)");
        try
        {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, id);
            statement.setLong(2, categorie);
            statement.setLong(3, contact);
            statement.setString(4,media);
            statement.setLong(5, location);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            try
            {
                connection().rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        } finally
        {
            try
            {
                connection().close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
