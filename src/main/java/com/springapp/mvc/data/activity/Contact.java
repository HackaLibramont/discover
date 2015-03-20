package com.springapp.mvc.data.activity;

/**
 * Created by Nicolas on 20/03/2015 at 21:31.
 */
public class Contact
{
    private String firstName;
    private String lastName;
    private String phone;
    private String website;
    private String email;

    public Contact()
    {
    }

    public Contact(String firstName, String lastName)
    {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String phone, String website, String email)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.website = website;
        this.email = email;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public void setWebsite(String website)
    {
        this.website = website;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
