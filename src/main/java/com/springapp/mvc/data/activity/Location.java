package com.springapp.mvc.data.activity;

/**
 * Created by Nicolas on 20/03/2015 at 21:32.
 */
public class Location
{
    private Long id;
    private String name;
    private String postcode;
    private double longitude;
    private double lattitude;
    private String com_name;

    public Location(Long id, double longitude, double lattitude)
    {
        this.id = id;
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public Location(double longitude, double lattitude)
    {
        this.longitude = longitude;
        this.lattitude = lattitude;
    }

    public Location(Long id, String name, String postcode, double longitude, double lattitude, String com_name)
    {
        this.id = id;
        this.name = name;
        this.postcode = postcode;
        this.longitude = longitude;
        this.lattitude = lattitude;
        this.com_name = com_name;
    }

    public void setPostcode(String postcode)
    {
        this.postcode = postcode;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public void setCom_name(String com_name)
    {
        this.com_name = com_name;
    }

    public Long getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public String getPostcode()
    {
        return postcode;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public double getLattitude()
    {
        return lattitude;
    }

    public String getCom_name()
    {
        return com_name;
    }
}
