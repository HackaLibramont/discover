package com.springapp.mvc.data.activity;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class Accomodation extends Activity
{
    private Timestamp start;
    private Timestamp end;

    public Accomodation(String name, double longitude, double lattitude, Timestamp start, Timestamp end)
    {
        super(name, longitude, lattitude);
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
}
