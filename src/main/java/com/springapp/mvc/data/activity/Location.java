package com.springapp.mvc.data.activity;

/**
 * Created by Nicolas on 20/03/2015 at 21:32.
 */
public class Location
{
    private String name;
    private double longitude;
    private double lattitude;
    private String com_name;

    public Location(double longitude, double lattitude)
    {
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public Location(String name, double longitude, double lattitude, String com_name)
    {
        this.name = name;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.com_name = com_name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCom_name(String com_name)
    {
        this.com_name = com_name;
    }
}
