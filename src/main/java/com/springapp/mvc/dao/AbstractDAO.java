package com.springapp.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> {

    private static Connection connection;

    private static String databaseName = "discover";

    public abstract List<T> findAll();

    public abstract T find(Long id);

    public abstract void insert (T t);

    public abstract void update (T t);

    public abstract T delete (Long id);

    public static Connection connection()
    {
        if(connection == null)
            try {
                connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + databaseName, "root", "");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return connection;
    }
}
