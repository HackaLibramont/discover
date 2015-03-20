package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Visit extends Activity
{
    public Visit(String name, Location location)
    {
        super(name, location);
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


    public Cathegory getCathegory()
    {
        //TODO
        return null;
    }
}
