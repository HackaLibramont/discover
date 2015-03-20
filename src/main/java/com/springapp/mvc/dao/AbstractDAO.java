package com.springapp.mvc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public abstract class AbstractDAO<T> {

    private static Connection connection;

    public static enum LANG {FR, EN, NL;

        public String getDbName()
        {
            return this.name().toLowerCase();
        }

        public static LANG find (String name)
        {
            for (LANG l : LANG.values())
            if (name != null)
                if (l.name().toUpperCase().equals(name.toUpperCase()))
                    return l;
            return EN;
        }
    };


    private static String databaseName = "discover";

    public abstract List<T> findAll();

    public abstract T find(Long id);

    public abstract T find(Long id, LANG lang);

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
