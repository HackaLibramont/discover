package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public abstract class Activity
{
    private String name;

    private Location location;
    private String description;
    private Contact contact;

    public Activity(String name, Location location)
    {
        this.name = name;
        this.location = location;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public abstract Timestamp getStart();

    public abstract Timestamp getEnd();

    public abstract Cathegory getCathegory();
}
