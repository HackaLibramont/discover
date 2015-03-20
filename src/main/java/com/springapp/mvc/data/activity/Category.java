package com.springapp.mvc.data.activity;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Category {
    private final Long id;
    private final String lib;
    private final String lang;

    public Category(Long id, String lib, String lang)
    {
        this.id = id;
        this.lib = lib;
        this.lang = lang;
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

    public String getLang()
    {
        return this.lang;
    }

}
