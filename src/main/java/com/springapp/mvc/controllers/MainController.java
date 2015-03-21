package com.springapp.mvc.controllers;

import com.springapp.mvc.data.activity.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/index")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model)
    {
       /* List<Activity> activities = new ArrayList<Activity>();
        Location loc = new Location(2., 3.);
        Contact contact = new Contact();
        activities.add(new Visit("Visite d'un truc", loc, contact));
        activities.add(new Visit("Visite d'un machin", loc, contact));
        activities.add(new Eating("Restaurant", loc, contact, new Timestamp(1891518874L), new Timestamp(1891528874L)));
        model.addAttribute("activities", activities);
        return "index";*/

        return "index";
    }
}
