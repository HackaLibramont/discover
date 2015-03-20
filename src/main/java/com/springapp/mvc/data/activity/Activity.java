package com.springapp.mvc.data.activity;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Activity {
    private String name;

    public Activity(String name)
    {
        this.name = name;
    }

    public String toString()
    {
        return this.name;
    }
}
