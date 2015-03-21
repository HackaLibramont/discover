package com.springapp.mvc.data.activity;

public class Contact
{
    private long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String website;
    private String email;
    private String number;
    private String address;

    public Contact(){
        this.id = -1;
    }

    public Contact(String firstName, String lastName)
    {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Contact(String firstName, String lastName, String phone, String website, String email, String number, String address)
    {
        this(firstName, lastName);
        this.phone = phone;
        this.website = website;
        this.email = email;
        this.number = number;
        this.address = address;
    }

    public Contact(long id, String firstName, String lastName, String phone, String website, String email, String number, String address){
        this(firstName, lastName, phone, website, email, number, address);
        this.id = id;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
