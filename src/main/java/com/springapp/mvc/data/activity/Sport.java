package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nicolas on 21/03/2015 at 09:36.
 */
public class Sport extends Activity
{
    private Timestamp start;
    private Timestamp end;

    public Sport(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY)
    {
        super(id, n, location, mainContact, geoX, geoY);
    }

    public Sport(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY, Timestamp end, Timestamp start)
    {
        super(id, n, location, mainContact, geoX, geoY);
        this.end = end;
        this.start = start;
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
    public Timestamp getStart()
    {
        return start;
    }

    @Override
    public Timestamp getEnd()
    {
        return end;
    }

    @Override
    public Category getCategory()
    {
        return null;
    }
}
