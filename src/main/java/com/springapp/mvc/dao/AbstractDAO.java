package com.springapp.mvc.dao;

import java.sql.Connection;

/**
 * Created by Nathan on 20/03/2015.
 */
public abstract class AbstractDAO<T> {

    //TODO
    private final static Connection connection = null;

    public abstract T find(Long id);

    public abstract void insert (T t);

    public abstract void update (T t);

    public abstract T delete (Long id);

    public static Connection connection()
    {
        return connection;
    }
}
