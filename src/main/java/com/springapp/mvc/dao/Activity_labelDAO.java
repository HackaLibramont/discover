package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Category;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nicolas on 21/03/2015 at 00:48.
 */
public class Activity_labelDAO extends AbstractDAO<Category, Long>
{
    @Override
    public List<Category> findAll()
    {
        return null;
    }

    @Override
    public List<Category> findAll(LANG lang)
    {
        return null;
    }

    @Override
    public Category find(Long id)
    {
        return null;
    }

    @Override
    public Category find(Long id, LANG lang)
    {
        return null;
    }

    @Override
    public void insert(Category category)
    {

    }

    public void insert(long id, String lang, long activity_id, String title, long description)
    {
        StringBuilder sql = new StringBuilder();
        sql.append("insert into activity_label values(?, ?, ?, ?, ?)");
        try
        {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, id);
            statement.setString(2, lang);
            statement.setLong(3, activity_id);
            statement.setString(4, title);
            statement.setLong(5, description);
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
    public void update(Category category)
    {

    }

    @Override
    public Category delete(Long id)
    {
        return null;
    }
}
