package com.springapp.mvc.data.activity;

import java.security.Timestamp;

/**
 * Created by Nicolas on 21/03/2015 at 09:32.
 */
public class Leisure extends Activity
{
    private Timestamp start;
    private Timestamp end;


    public Leisure(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY, String media)
    {
        super(id, n, location, mainContact, geoX, geoY, media);
    }

    public Leisure(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY, Timestamp start, Timestamp end, String media)
    {
        super(id, n, location, mainContact, geoX, geoY, media);
        this.start = start;
        this.end = end;
    }

    public void setStart(Timestamp start)
    {
        this.start = start;
    }

    public void setEnd(Timestamp end)
    {
        this.end = end;
    }

    @Override
    public java.sql.Timestamp getStart()
    {
        return null;
    }

    @Override
    public java.sql.Timestamp getEnd()
    {
        return null;
    }

    @Override
    public Category getCategory()
    {
        return null;
    }
}
