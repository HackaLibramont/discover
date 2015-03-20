package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Eating extends Activity
{
    private Timestamp start;
    private Timestamp end;

    public Eating(String name, Location location, Timestamp start, Timestamp end)
    {
        super(name, location);
        this.start = start;
        this.end = end;
    }

    public Timestamp getStart()
    {
        return start;
    }

    public Timestamp getEnd()
    {
        return end;
    }

    @Override
    public Cathegory getCathegory()
    {
        //TODO
        return null;
    }
}
