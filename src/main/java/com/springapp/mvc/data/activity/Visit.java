package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Visit extends Activity
{
    public Visit(String name, Location location, Contact mainContact)
    {
        super(name, location, mainContact);
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

    @Override
    public Category getCategory() {
        //TODO
        return null;
    }

}
