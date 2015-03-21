package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Accomodation extends Activity
{
    public Accomodation(Long id, String name, Location location, Contact mainContact, Double geoX, Double geoY)
    {
        super(id, name, location, mainContact, geoX, geoY);
    }

    public Timestamp getStart()
    {
        return Timestamp.valueOf("1970-01-01 00:00:00");
    }

    public Timestamp getEnd()
    {
        return Timestamp.valueOf("3000-01-01 23:59:59");
    }

    @Override
    public Category getCategory()
    {
        return null;
    }
}
