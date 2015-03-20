package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public abstract class Activity
{
    private String name;
    private final Location location;
    private String description;
    private final Contact mainContact;

    public Activity(String n, Location location, Contact mainContact) {
        name = n;
        this.location = location;
        this.mainContact = mainContact;
    }

    public void setDescription(String d) {
        description = d;
    }

    public abstract Timestamp getStart();

    public abstract Timestamp getEnd();

    public Location getLocation()
    {
        return this.location;
    }

    public Contact getMainContact()
    {
        return this.mainContact;
    }

    public abstract Category getCategory();

    public String toString() {
        return this.name;
    }
}
