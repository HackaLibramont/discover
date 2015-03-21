package com.springapp.mvc.dao;


import com.springapp.mvc.dao.data.Filter;
import com.springapp.mvc.data.activity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 20/03/2015.
 */
public class ActivityDAO extends AbstractDAO<Activity, Long>{


    @Override
    public List<Activity> findAll() {
        return findAll(LANG.EN);
    }

    @Override
    public List<Activity> findAll(LANG lang) {
        List<Activity> result = new ArrayList<Activity>();
        StringBuilder sql = new StringBuilder();
        sql.append("select * from category");
        PreparedStatement statement = null;
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {
                LocationDAO locDao = new LocationDAO();
                Location loc = locDao.find(res.getLong("location_id"));
                ContactDAO contDao = new ContactDAO();
                Contact con = contDao.find(res.getLong("contact_id"));
                //result.add(new Activity(res.getString("name"),loc, con));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return result;
    }

    @Override
    public Activity find(Long id) {
        return this.find(id, LANG.EN);
    }

    @Override
    public Activity find(Long id, LANG lang) {

        Activity activity = null;
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct act.id actId");
        sql.append("      , act.media actMedia ");
        sql.append("      , act.latitude actX ");
        sql.append("      , act.longitude actY ");
        sql.append("      , actl.title actlTitle ");
        sql.append("      , actl.description actlDescr ");
        sql.append("      , loc.id locId ");
        sql.append("      , loc.name locName ");
        sql.append("      , loc.postcode locPostCode ");
        sql.append("      , loc.longitude locX ");
        sql.append("      , loc.latitude locY ");
        sql.append("      , loc.com_name locComm ");
        sql.append("      , con.id conId ");
        sql.append("      , con.lastName conLastName ");
        sql.append("      , con.firstName conFirstName ");
        sql.append("      , con.phone conPhone ");
        sql.append("      , con.website conWebsite ");
        sql.append("      , con.email conMail ");
        sql.append("      , con.number conNumber ");
        sql.append("      , con.address conAddress ");
        sql.append("      , scat.name scatName ");
        sql.append("   from category cat");
        sql.append("      , category_name catn");
        sql.append("      , super_category scat");
        sql.append("      , activity act");
        sql.append("      , activity_label actl");
        sql.append("      , contact con");
        sql.append("      , location loc");
        sql.append("  where loc.id = act.location_id");
        sql.append("    and con.id = act.contact_id");
        sql.append("    and act.id = ?");
        sql.append("    and catn.super_id = scat.id");
        sql.append("    and cat.id = catn.val");
        sql.append("    and cat.id = act.category_id");
        sql.append("    and actl.activity_id = act.id");
        sql.append("    and actl.lang = ?");
        PreparedStatement statement = null;
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, id);
            statement.setString(1, lang.getDbName());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {
                Long actId = res.getLong("actId");
                String actMedia = res.getString("actMedia");
                Double actX = res.getDouble("actX");
                Double actY = res.getDouble("actY");
                String actlTitle = res.getString("actlTitle");
                String actlDescr = res.getString("actlDescr");
                Long locId = res.getLong("locId");
                String locName = res.getString("locName");
                String locPostcode = res.getString("locPostcode");
                Double locX = res.getDouble("locX");
                Double locY = res.getDouble("locY");
                String locComm = res.getString("locComm");
                Long conId = res.getLong("conId");
                String conLastName = res.getString("conLastName");
                String conFirstName = res.getString("conFirstName");
                String conPhone = res.getString("conPhone");
                String conWebsite = res.getString("conWebsite");
                String conMail = res.getString("conMail");
                String conNumber = res.getString("conNumber");
                String conAddress = res.getString("conAddress");
                String scatName = res.getString("scatName");
                Location localisation = new Location(locId, locName, locPostcode, locX, locY, locComm);
                Contact contact = new Contact(conId, conLastName, conFirstName, conPhone, conWebsite, conMail, conNumber, conAddress);

                if (scatName.equals("Hébergement"))
                {
                    activity = new Accomodation(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Lieu"))
                {
                    activity = new Place(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Loisir"))
                {
                    activity = new Leisure(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Sport"))
                {
                    activity = new Sport(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Patrimoine"))
                {
                    activity = new Heritage(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Production"))
                {
                    activity = new Production(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Evènement"))
                {
                    activity = new Tourism(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return activity;

    }

    @Override
    public void insert(Activity activity) {

    }

    @Override
    public void update(Activity activity) {

    }

    @Override
    public Activity delete(Long id) {
        return null;
    }

    public void insert(Long id, Long categorie, Long contact, String media, Long location, Double geoX, Double geoY)
    {
        PreparedStatement statement = null;
                StringBuilder sql = new StringBuilder();
        sql.append("insert into activity values(?, ?, ?, ?, ?, ?, ?)");
        try
        {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setLong(1, id);
            statement.setLong(2, categorie);
            statement.setLong(3, contact);
            statement.setString(4, media);
            statement.setLong(5, location);
            statement.setDouble(6, geoX);
            statement.setDouble(7, geoY);
            statement.executeUpdate();
        } catch (SQLException e)
        {
            e.printStackTrace();
            try
            {
                connection().rollback();
            } catch (SQLException e1)
            {
                e1.printStackTrace();
            }
        } finally
        {
            try
            {
                if (statement != null)
                    statement.close();
            } catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }

    public List<Activity> filter(Filter filter, LANG lang)
    {
        List<Activity> result = new ArrayList<Activity>();
        StringBuilder sql = new StringBuilder();
        sql.append(" select distinct act.id actId");
        sql.append("      , act.media actMedia ");
        sql.append("      , act.latitude actX ");
        sql.append("      , act.longitude actY ");
        sql.append("      , actl.title actlTitle ");
        sql.append("      , actl.description actlDescr ");
        sql.append("      , loc.id locId ");
        sql.append("      , loc.name locName ");
        sql.append("      , loc.postcode locPostCode ");
        sql.append("      , loc.longitude locX ");
        sql.append("      , loc.latitude locY ");
        sql.append("      , loc.com_name locComm ");
        sql.append("      , con.id conId ");
        sql.append("      , con.lastName conLastName ");
        sql.append("      , con.firstName conFirstName ");
        sql.append("      , con.phone conPhone ");
        sql.append("      , con.website conWebsite ");
        sql.append("      , con.email conMail ");
        sql.append("      , con.number conNumber ");
        sql.append("      , con.address conAddress ");
        sql.append("      , scat.name scatName ");
        sql.append("   from category cat");
        sql.append("      , category_name catn");
        sql.append("      , super_category scat");
        sql.append("      , activity act");
        sql.append("      , activity_label actl");
        sql.append("      , contact con");
        sql.append("      , location loc");
        sql.append("  where loc.id = act.location_id");
        sql.append("    and con.id = act.contact_id");
        sql.append("    and catn.super_id = scat.id");
        sql.append("    and cat.id = catn.val");
        sql.append("    and cat.id = act.category_id");
        sql.append("    and actl.activity_id = act.id");
        sql.append("    and actl.lang = ?");
        sql.append(filter.toWhere(false));
        PreparedStatement statement = null;
        try {
            statement = AbstractDAO.connection().prepareStatement(sql.toString());
            statement.setString(1, lang.getDbName());
            ResultSet res = statement.executeQuery();
            while (res.next())
            {
                Long actId = res.getLong("actId");
                String actMedia = res.getString("actMedia");
                Double actX = res.getDouble("actX");
                Double actY = res.getDouble("actY");
                String actlTitle = res.getString("actlTitle");
                String actlDescr = res.getString("actlDescr");
                Long locId = res.getLong("locId");
                String locName = res.getString("locName");
                String locPostcode = res.getString("locPostcode");
                Double locX = res.getDouble("locX");
                Double locY = res.getDouble("locY");
                String locComm = res.getString("locComm");
                Long conId = res.getLong("conId");
                String conLastName = res.getString("conLastName");
                String conFirstName = res.getString("conFirstName");
                String conPhone = res.getString("conPhone");
                String conWebsite = res.getString("conWebsite");
                String conMail = res.getString("conMail");
                String conNumber = res.getString("conNumber");
                String conAddress = res.getString("conAddress");
                String scatName = res.getString("scatName");
                Location localisation = new Location(locId, locName, locPostcode, locX, locY, locComm);
                Contact contact = new Contact(conId, conLastName, conFirstName, conPhone, conWebsite, conMail, conNumber, conAddress);
                Activity activity = null;
                if (scatName.equals("Hébergement"))
                {
                    activity = new Accomodation(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Lieu"))
                {
                    activity = new Place(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Loisir"))
                {
                    activity = new Leisure(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Sport"))
                {
                    activity = new Sport(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Patrimoine"))
                {
                    activity = new Heritage(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Production"))
                {
                    activity = new Production(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                else if (scatName.equals("Evènement"))
                {
                    activity = new Tourism(actId, actlTitle, localisation, contact, actX, actY, actMedia);
                }
                if (activity != null)
                    result.add(activity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally
        {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException sqlEx) {
                sqlEx.printStackTrace();
            }
        }
        return result;
    }
}
