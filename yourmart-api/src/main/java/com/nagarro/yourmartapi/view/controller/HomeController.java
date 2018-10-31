package com.nagarro.yourmartapi.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HomeController {

	 @RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	    public String index(Model model) {
	 
	        String message = "Hello Spring Boot + JSP";
	        System.out.println(message);
	        model.addAttribute("message", message);
	 
	        return "login";
	    }
	
}
