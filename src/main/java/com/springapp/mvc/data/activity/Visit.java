package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Visit extends Activity
{
    public Visit(String name, double longitude, double lattitude)
    {
        super(name, longitude, lattitude);
    }

    @Override
    public Timestamp getStart()
    {
        //TODO
        return null;
    }

    @Override
    public Timestamp getEnd()
    {
        //TODO
        return null;
    }
}
