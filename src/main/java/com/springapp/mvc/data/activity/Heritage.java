package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nicolas on 21/03/2015 at 09:38.
 */
public class Heritage extends Activity
{
    public Heritage(Long id, String n, Location location, Contact mainContact, Double geoX, Double geoY)
    {
        super(id, n, location, mainContact, geoX, geoY);
    }

    @Override
    public Timestamp getStart()
    {
        return null;
    }

    @Override
    public Timestamp getEnd()
    {
        return null;
    }

    @Override
    public Category getCategory()
    {
        return null;
    }
}
