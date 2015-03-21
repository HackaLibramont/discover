package com.springapp.mvc.xml;


import com.springapp.mvc.dao.*;
import com.springapp.mvc.dao.data.Filter;
import com.springapp.mvc.data.activity.Category;
import com.springapp.mvc.data.activity.CategoryName;
import com.springapp.mvc.data.activity.Contact;
import com.springapp.mvc.data.activity.Location;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by Nathan on 20/03/2015.
 */
public class XMLParser {

    private static Long autoIncActivity = 1L;
    private static Long autoIncCategory = 1L;
    private static Long autoIncContact = 1L;
    private static CategoryDAO catDAO = new CategoryDAO();
    private static CategoryNameDAO catNameDAO = new CategoryNameDAO();
    private static ContactDAO ctctDAO = new ContactDAO();
    private static LocationDAO localDAO = new LocationDAO();
    private static ActivityDAO actDAO = new ActivityDAO();
    private static Activity_labelDAO actLabelDAO = new Activity_labelDAO();


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        Filter filter = new Filter();
        filter.addSuperCategoryFilter("3");
        new ActivityDAO().filter(filter, AbstractDAO.LANG.EN);
       /* Scanner keyboard = new Scanner(System.in);
        System.out.print("path : c:/Users/Nathan/Desktop/hackathon.xml");
        parse("c:/Users/Nathan/Desktop/hackathon.xml");*/
    }

    public static void parse(String path) throws ParserConfigurationException, IOException, SAXException {

        File fXmlFile = new File(path);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(fXmlFile);
        doc.getDocumentElement().normalize();
        NodeList offers = ((Element) doc.getChildNodes().item(0)).getElementsByTagName("offres").item(0).getChildNodes();
        for (int i = 0; i < offers.getLength(); i++) {

            try {
                Node offer = offers.item(i);
                Long id = Long.parseLong(((Element) offer).getAttribute("id"));
                Double geoX = Double.parseDouble(((Element) ((Element) ((Element) offer).getElementsByTagName("geocodes").item(0)).getElementsByTagName("geocode").item(0)).getElementsByTagName("x").item(0).getChildNodes().item(0).getNodeValue());
                Double geoY = Double.parseDouble(((Element) ((Element) ((Element) offer).getElementsByTagName("geocodes").item(0)).getElementsByTagName("geocode").item(0)).getElementsByTagName("y").item(0).getChildNodes().item(0).getNodeValue());
                String media = null;
                String categoryName = null;
                Map<String, String> titles = new HashMap<String, String>();
                Map<String, String> descriptions = new HashMap<String, String>();
                Map<String, String> categoryNames = new HashMap<String, String>();
                for (int j = 0; j < ((Element) offer).getElementsByTagName("titre").getLength(); ++j) {
                    Element title = (Element) ((Element) offer).getElementsByTagName("titre").item(j);
                    if (title.getAttribute("lg") != null && !(title.getAttribute("lg").equals("")))
                        titles.put(title.getAttribute("lg"), title.getChildNodes().item(0).getNodeValue());
                }
                try {
                    Node descr = ((Element) ((Element) offer).getElementsByTagName("descriptions").item(0)).getElementsByTagName("description").item(0);
                    for (int j = 0; j < ((Element) descr).getElementsByTagName("texte").getLength(); ++j) {
                        Element text = (Element) ((Element) descr).getElementsByTagName("texte").item(j);
                        if (text.getAttribute("lg") != null && !(text.getAttribute("lg").equals("")))
                            descriptions.put(text.getAttribute("lg"), text.getChildNodes().item(0).getNodeValue());
                    }
                } catch (NullPointerException e) {
                }
                Node category = ((Element) ((Element) offer).getElementsByTagName("categories").item(0)).getElementsByTagName("categorie").item(0);
                categoryName = ((Element) category).getAttribute("id");
                for (int j = 0; j < ((Element) category).getElementsByTagName("lib").getLength(); ++j) {
                    Element lib = (Element) ((Element) category).getElementsByTagName("lib").item(j);
                    try {
                        if (lib.getAttribute("lg") != null && !(lib.getAttribute("lg").equals("")))
                            categoryNames.put(lib.getAttribute("lg"), lib.getChildNodes().item(0).getNodeValue());
                    } catch (NullPointerException e) {
                    }
                }

                String cLastName = null;
                String cFirstName = null;
                String cAdress = null;
                String cNumber = null;
                String cPhone = null;
                String cWebsite = null;
                String cMail = null;
                try {
                    Element contact = (Element) ((Element) ((Element) offer).getElementsByTagName("contacts").item(0)).getElementsByTagName("contact").item(0);
                    cLastName = contact.getElementsByTagName("noms").item(0).getChildNodes().item(0).getNodeValue();
                } catch (NullPointerException e) {
                }
                try {
                    Element contact = (Element) ((Element) ((Element) offer).getElementsByTagName("contacts").item(0)).getElementsByTagName("contact").item(0);
                    cFirstName = contact.getElementsByTagName("prenoms").item(0).getChildNodes().item(0).getNodeValue();
                } catch (NullPointerException e) {
                }
                try {
                    Element contact = (Element) ((Element) ((Element) offer).getElementsByTagName("contacts").item(0)).getElementsByTagName("contact").item(0);
                    cAdress = contact.getElementsByTagName("adresse").item(0).getChildNodes().item(0).getNodeValue();
                } catch (NullPointerException e) {
                }
                try {
                    Element contact = (Element) ((Element) ((Element) offer).getElementsByTagName("contacts").item(0)).getElementsByTagName("contact").item(0);
                    cNumber = contact.getElementsByTagName("numero").item(0).getChildNodes().item(0).getNodeValue();
                } catch (NullPointerException e) {
                }
                try {
                    Element contact = (Element) ((Element) ((Element) offer).getElementsByTagName("contacts").item(0)).getElementsByTagName("contact").item(0);
                    NodeList communications = contact.getElementsByTagName("communications");
                    try {
                        for (int j = 0; j < communications.item(0).getChildNodes().getLength(); ++j) {
                            Element comm = (Element) communications.item(0).getChildNodes().item(j);
                            if (comm.getAttribute("typ").equals("tel"))
                                cPhone = comm.getElementsByTagName("val").item(0).getChildNodes().item(0).getNodeValue();
                            else if (comm.getAttribute("typ").equals("mail"))
                                cMail = comm.getElementsByTagName("val").item(0).getChildNodes().item(0).getNodeValue();
                            else if (comm.getAttribute("typ").equals("url"))
                                cWebsite = comm.getElementsByTagName("val").item(0).getChildNodes().item(0).getNodeValue();

                        }
                    } catch (NullPointerException e) {
                    }
                } catch (NullPointerException e){}
                Element location = (Element) ((Element) ((Element) offer).getElementsByTagName("localisation").item(0)).getElementsByTagName("localite").item(0);
                Long lId = null;
                String lName = null;
                String lPostCode = null;
                Double lX = null;
                Double lY = null;
                String lComName = null;
                try {
                    lId = Long.parseLong(location.getAttribute("id"));
                    lName = location.getElementsByTagName("l_nom").item(0).getChildNodes().item(0).getNodeValue();
                    lPostCode = location.getElementsByTagName("postal").item(0).getChildNodes().item(0).getNodeValue();
                    lX = Double.parseDouble(location.getElementsByTagName("x").item(0).getChildNodes().item(0).getNodeValue());
                    lY = Double.parseDouble(location.getElementsByTagName("y").item(0).getChildNodes().item(0).getNodeValue());
                    lComName = location.getElementsByTagName("c_nom").item(0).getChildNodes().item(0).getNodeValue();

                    media = ((Element) ((Element) ((Element) offer).getElementsByTagName("medias").item(0)).getElementsByTagName("media").item(0)).getElementsByTagName("url").item(0).getChildNodes().item(0).getNodeValue();
                } catch (NullPointerException e) {

                }

                if (lId != null) {
                    Location localiteObj = XMLParser.localDAO.find(lId);
                    if (localiteObj == null) {
                        localiteObj = new Location(lId.longValue(), lName, lPostCode, lX.doubleValue(), lY.doubleValue(), lComName);
                        XMLParser.localDAO.insert(localiteObj);
                    }
                }
                CategoryName categoryNameObj = XMLParser.catNameDAO.find(categoryName);
                if (categoryNameObj == null) {
                    categoryNameObj = new CategoryName(categoryName, ++XMLParser.autoIncCategory);
                    XMLParser.catNameDAO.insert(categoryNameObj);
                    for (String lang : categoryNames.keySet()) {
                        Category categoryObj = new Category(XMLParser.autoIncCategory, categoryNames.get(lang), lang);
                        catDAO.insert(categoryObj);
                    }
                }

                Contact contactObj = new Contact(++autoIncContact, cFirstName, cLastName, cPhone, cWebsite, cMail, cNumber, cAdress);

                XMLParser.ctctDAO.insert(contactObj);
                actDAO.insert(id, XMLParser.autoIncCategory, XMLParser.autoIncContact, media, lId, geoX, geoY);
                Long actId = ++autoIncActivity;
                for (String lang : titles.keySet())
                    actLabelDAO.insert(actId, lang, id, titles.get(lang), descriptions.get(lang));
                System.out.println("added " + i);
            } catch (NullPointerException e) {
                System.out.println("null " + i + " ");
                e.printStackTrace();
            }
        }
    }
}
