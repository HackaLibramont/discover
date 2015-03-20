package com.springapp.mvc.data.user;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Nathan on 20/03/2015.
 */
public class VirtualUser {
    private final String name;
    private final String firstName;
    private final Timestamp birthday;


    public VirtualUser(String name, String firstName, Timestamp birthday)
    {
        this.name = name;
        this.firstName = firstName;
        this.birthday = birthday;
    }

    public String getName()
    {
        return this.name;
    }

    public String getFirstName()
    {
        return this.firstName;
    }

    public Timestamp getBirthday()
    {
        return this.birthday;
    }




}
