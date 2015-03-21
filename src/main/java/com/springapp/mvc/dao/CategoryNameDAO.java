package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Category;
import com.springapp.mvc.data.activity.CategoryName;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 20/03/2015.
 */
public class CategoryNameDAO extends AbstractDAO<CategoryName, String> {

    @Override
    public List<CategoryName> findAll() {
        PreparedStatement statement = null;
        List<CategoryName> result = new ArrayList<CategoryName>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from category_name");
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {
                result.add(new CategoryName(res.getString("id"), res.getLong("val")));
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
    public List<CategoryName> findAll(LANG lang)
    {
        return null;
    }

    @Override
    public CategoryName find(String id) {
        PreparedStatement statement = null;
                CategoryName result = null;
        StringBuilder sql = new StringBuilder();
        sql.append("select * from category_name where id = ?");
        try {
           statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setString(1, id);
            ResultSet res = statement.executeQuery();
            if (res.next())
            {
                result = new CategoryName(res.getString("id"), res.getLong("val"));
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
    public CategoryName find(String id, LANG lang) {
        return this.find(id);
    }

    @Override
    public void insert(CategoryName categoryName) {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        sql.append("insert into category_name values(?, ?)");
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setString(1, categoryName.getId());
            statement.setLong(2, categoryName.getForeignKey());
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
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(CategoryName categoryName) {
        PreparedStatement statement = null;
        StringBuilder sql = new StringBuilder();
        sql.append("update category_name set val = ?");
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, categoryName.getForeignKey());
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
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public CategoryName delete(String id) {
        return null;
    }
}
