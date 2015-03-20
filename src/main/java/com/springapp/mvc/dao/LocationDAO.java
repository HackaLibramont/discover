package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Location;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nicolas on 20/03/2015 at 23:29.
 */
public class LocationDAO extends AbstractDAO<Location, Long>
{

    @Override
    public List<Location> findAll()
    {
        List<Location> result = new ArrayList<Location>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from location");
        try
        {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {
                result.add(new Location(res.getLong("id"), res.getString("name"), res.getString("postcode"), res.getDouble("longitude"), res.getDouble("lattitude"), res.getString("com_name")));
            }
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
        return result;
    }

    @Override
    public List<Location> findAll(LANG lang)
    {
        return null;
    }

    @Override
    public Location find(Long id)
    {
        return find(id, LANG.find(null));
    }

    @Override
    public Location find(Long id, LANG lang)
    {
        Location result = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from " + ".location where id = ? and lang = ?");
        try
        {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, id);
            statement.setString(2, lang.getDbName());
            ResultSet res = statement.executeQuery();
            if (res.next())
            {
                result = new Location(res.getLong("id"), res.getString("name"), res.getString("postcode"), res.getDouble("longitude"), res.getDouble("latitude"), res.getString("com_name"));
            }
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
        return result;
    }

    @Override
    public void insert(Location location)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into location values(?, ?, ?, ?, ?, ?)");
        try
        {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, location.getId());
            statement.setString(2, location.getName());
            statement.setString(3, location.getPostcode());
            statement.setDouble(4, location.getLongitude());
            statement.setDouble(5, location.getLattitude());
            statement.setString(6, location.getCom_name());
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

    @Override
    public void update(Location location)
    {
    }

    @Override
    public Location delete(Long id)
    {
        return null;
    }
}
