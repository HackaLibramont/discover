package com.springapp.mvc.controllers;

import com.springapp.mvc.data.activity.Stay;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;

@Controller
@RequestMapping("/")
public class HelloController {

	@RequestMapping(method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
        Stay test = new Stay(new Timestamp(1426873494884L), new Timestamp(1426873495984L));
        model.addAttribute("stay", test);
		return "hello";
	}

}