package com.nagarro.yourmart_admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.yourmart_admin.model.Admin;
import com.nagarro.yourmart_admin.service.AdminService;

@Controller
public class LoginController
{

//	@Autowired
//	AdminService adminService;
//	
	@RequestMapping(value="/",method = RequestMethod.GET)  
    public ModelAndView login(){  
		System.out.println("call login");
		return new ModelAndView("index");  
		}  
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.GET)
	  public ModelAndView showLogin(HttpServletRequest request, HttpServletResponse response) {
		 ModelAndView mav = new ModelAndView("login");
		    mav.addObject("login", new Admin());
		    return mav;
	  }
	
	
}
