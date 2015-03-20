package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Contact;

import java.util.List;

/**
 * Created by Paul-Henri Froidmont on 20/3/2015.
 */
public class ContactDAO extends AbstractDAO<Contact> {
    @Override
    public List<Contact> findAll() {
        return null;
    }

    @Override
    public Contact find(Long id) {
        return null;
    }

    @Override
    public Contact find(Long id, LANG lang) {
        return null;
    }

    @Override
    public void insert(Contact contact) {

    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public Contact delete(Long id) {
        return null;
    }
}
