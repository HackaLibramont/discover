package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Category;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by Nathan on 20/03/2015.
 */
public class CategoryDAO extends AbstractDAO<Category>{

    @Override
    public Category find(Long id) {
        Category result = null;
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * from " + ".category where id = ?");
        try {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, id);
            connection().commit();
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
