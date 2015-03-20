package com.springapp.mvc.controllers;

import com.springapp.mvc.data.activity.Activity;
import com.springapp.mvc.data.activity.Eating;
import com.springapp.mvc.data.activity.Visit;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nathan on 20/03/2015.
 */
@Controller
@RequestMapping("/index")
public class MainController {

    @RequestMapping(method = RequestMethod.GET)
    public String index(ModelMap model)
    {
        List<Activity> activities = new ArrayList<Activity>();
        activities.add(new Visit("Visite d'un truc", 3., 2.));
        activities.add(new Visit("Visite d'un machin", 2., 3.));
        activities.add(new Eating("Restaurant", 4., 2., new Timestamp(1891518874L), new Timestamp(1891528874L)));
        model.addAttribute(activities);
        return "index";
    }
}
