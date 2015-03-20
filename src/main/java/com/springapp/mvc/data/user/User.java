package com.springapp.mvc.data.user;

import java.sql.Timestamp;

/**
 * Created by Nathan on 20/03/2015.
 */
public class User extends VirtualUser
{
    private final String mail;

    public User(String name, String firstName, Timestamp birthday, String mail)
    {
        super(name, firstName, birthday);
        this.mail = mail;
    }

    public String getMail()
    {
        return this.mail;
    }
}
