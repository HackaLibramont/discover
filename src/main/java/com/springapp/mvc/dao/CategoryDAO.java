package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Nathan on 20/03/2015.
 */
public class CategoryDAO extends AbstractDAO<Category>{

    @Override
    public List<Category> findAll() {
        return null;
    }

    @Override
    public Category find(Long id) {
        return find(id, LANG.find(null));
    }

    @Override
    public Category find(Long id, LANG lang) {
        Category result = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from " + ".category where id = ? and lang = ?");
        try {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, id);
            statement.setString(2, lang.getDbName());
            connection().commit();
            ResultSet res = statement.executeQuery();
            if (res.next())
            {
                result = new Category(res.getLong("id"), res.getString("name"));
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
    public void insert(Category category) {

    }

    @Override
    public void update(Category category) {

    }

    @Override
    public Category delete(Long id) {
        return null;
    }
}
