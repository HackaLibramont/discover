package com.springapp.mvc.data.activity;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Category {
    private final Long id;
    private final String lib;

    public Category(Long id, String lib)
    {
        this.id = id;
        this.lib = lib;
    }

    public String getLib()
    {
        return this.lib;
    }

    public Long getId()
    {
        return this.id;
    }

    public String toString()
    {
        return this.getLib();
    }


}
