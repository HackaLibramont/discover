package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 20/03/2015.
 */
public class CategoryDAO extends AbstractDAO<Category>{

    @Override
    public List<Category> findAll() {
        List<Category> result = new ArrayList<Category>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from category");
        try {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {
                result.add(new Category(res.getLong("id"), res.getString("name"), res.getString("lang")));
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
            ResultSet res = statement.executeQuery();
            if (res.next())
            {
                result = new Category(res.getLong("id"), res.getString("name"), res.getString("lang"));
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
        StringBuilder sql = new StringBuilder();
        sql.append("insert into category values(?, ?, ?)");
        try {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, category.getId());
            statement.setString(2, category.getLib());
            statement.setString(2, category.getLang());
            statement.executeUpdate();
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
    }

    @Override
    public void update(Category category) {
        StringBuilder sql = new StringBuilder();
        sql.append("update category set lib = ?");
        try {
            PreparedStatement statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setString(1, category.getLib());
            statement.executeUpdate();
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
    }

    @Override
    public Category delete(Long id) {
        return null;
    }
}
