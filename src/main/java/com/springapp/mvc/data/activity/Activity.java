package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public abstract class Activity
{

    private Long Id;
    private String name;
    private final Location location;
    private final Double geoX;
    private final Double geoY;
    private String description;
    private final Contact mainContact;
    private final String mediaUrl;

    public Activity(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY, String media)
    {
        Id = id;
        name = n;
        this.location = location;
        this.mainContact = mainContact;
        this.geoX = geoX;
        this.geoY = geoY;
        this.mediaUrl = media;
    }

    public void setDescription(String d)
    {
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

    public String toString()
    {
        return this.name;
    }

    public Long getId()
    {
        return Id;
    }

    public void setId(long id)
    {
        Id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public Long getLocationId()
    {
        return this.location.getId();
    }

    public Double getGeoX()
    {
        return geoX;
    }

    public Double getGeoY()
    {
        return geoY;
    }

    public String getMediaUrl() {
        return mediaUrl;
    }
}
