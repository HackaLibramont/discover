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

    public Activity(String n, Location lo) {
        name = n;
       location = lo;
    }

    public void setDescription(String d) {
        description = d;
    }

    public abstract Timestamp getStart();

    public abstract Timestamp getEnd();


    public String toString() {
        return this.name;
    }
}
