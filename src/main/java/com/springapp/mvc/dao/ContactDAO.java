package com.springapp.mvc.dao;

import com.springapp.mvc.data.activity.Contact;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO extends AbstractDAO<Contact, Long> {
    @Override
    public List<Contact> findAll() {
        ArrayList<Contact> contacts = new ArrayList<Contact>();
        PreparedStatement preparedStatement = null;
        String query = "SELECT * FROM contact";
        try {
            preparedStatement = connection().prepareStatement(query);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                contacts.add(new Contact(resultSet.getLong("id"), resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("phone"), resultSet.getString("website"), resultSet.getString("email"), resultSet.getString("number"), resultSet.getString("address")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }
        return contacts;
    }

    @Override
    public List<Contact> findAll(LANG lang) {
        return null;
    }

    @Override
    public Contact find(Long id) {
        Contact contact = null;
        PreparedStatement preparedStatement = null;
        String query = "SELECT sectionName, annee FROM Classe WHERE numClasse =?";
        try {
            preparedStatement = connection().prepareStatement(query);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                contact = new Contact(id, resultSet.getString("firstname"), resultSet.getString("lastname"), resultSet.getString("phone"), resultSet.getString("website"), resultSet.getString("email"), resultSet.getString("number"), resultSet.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }

        }
        return contact;
    }

    @Override
    public Contact find(Long id, LANG lang) {
        return null;
    }

    @Override
    public void insert(Contact contact) {
        boolean lineInserted = false;
        PreparedStatement preparedStatement = null;
        String query = "INSERT INTO contact (id, firstname, lastname, phone, website, email, number, address) VALUES (?,?,?,?,?,?,?,?)";
        try {
            preparedStatement = connection().prepareStatement(query);
            preparedStatement.setLong(1, contact.getId());
            preparedStatement.setString(2, contact.getFirstName());
            preparedStatement.setString(3, contact.getLastName());
            preparedStatement.setString(4, contact.getPhone());
            preparedStatement.setString(5, contact.getWebsite());
            preparedStatement.setString(6, contact.getEmail());
            preparedStatement.setString(7, contact.getNumber());
            preparedStatement.setString(8, contact.getAddress());
            int nbrLigneInsert = preparedStatement.executeUpdate();
            if (nbrLigneInsert > 0) {
                lineInserted = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
    }

    @Override
    public void update(Contact contact) {

    }

    @Override
    public Contact delete(Long id) {
        return null;
    }
}
