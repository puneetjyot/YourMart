package com.nagarro.yourmart_admin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.yourmart_admin.dto.AdminBeforeLogin;
import com.nagarro.yourmart_admin.dto.AdminResponseDto;
import com.nagarro.yourmart_admin.model.Admin;
import com.nagarro.yourmart_admin.service.AdminService;

@Controller
public class LoginController
{

	@Autowired
	AdminService adminService;
//	
	@RequestMapping(value="/",method = RequestMethod.GET)  
    public ModelAndView login(){  
		System.out.println("call login");
		return new ModelAndView("index");  
		}  
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	  public ModelAndView authenticate(HttpServletRequest request,ModelMap model,@ModelAttribute("authenticate") AdminBeforeLogin adminBeforeLogin) {
		System.out.println("in login");
		ModelAndView mav=new ModelAndView();
		AdminResponseDto adminResponseDto=adminService.authenticateAdmin(adminBeforeLogin);
		if(adminResponseDto.getStatus()==200) {
		System.out.println(adminResponseDto.getData().getUsername());
		
		HttpSession session=request.getSession(false);
		session.setAttribute("user",adminResponseDto.getData().getUsername() );
		 mav.addObject("data",adminResponseDto.getData());
		 mav.setViewName("redirect:/home");
		  
		    return mav;
		}
		else {
			HttpSession session=request.getSession(true);
		    session.setAttribute("isValid", "false");
		    return new ModelAndView("redirect:/");  
		}
	  }
	
	
}
