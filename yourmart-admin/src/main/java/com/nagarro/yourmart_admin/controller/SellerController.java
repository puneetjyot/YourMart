package com.nagarro.yourmart_admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.yourmart_admin.dto.SellerDto;
import com.nagarro.yourmart_admin.dto.SingleSellerResponseDto;
import com.nagarro.yourmart_admin.service.SellerService;
import com.nagarro.yourmart_admin.serviceimpl.SellerServiceImpl;

@Controller
public class SellerController 
{
	//@Autowired
	SellerService sellerService=new SellerServiceImpl();
	
		@RequestMapping(value="/home",method = RequestMethod.GET)
			public ModelAndView home(ModelAndView model,HttpServletRequest request) {
				ModelAndView mav = new ModelAndView();
				System.out.println("getting list");
				HttpSession session=request.getSession();
				SellerDto sellerDto=sellerService.getSellerList();
				if(sellerDto.getStatus()==200) {
				mav.addObject("sellerList",sellerDto.getData());
				mav.setViewName("login");
				System.out.println(model);
				
				
				String user=(String) session.getAttribute("user");

				mav.addObject("user",user);
				}
				else {
					session.setAttribute("isValid", "false");
				    return new ModelAndView("redirect:/home");  
				}
				return mav;
			} 
		
		@RequestMapping(value="/seller",method = RequestMethod.GET)
		public ModelAndView getSeller(ModelAndView model,int id) {
			//create seller .jsp
			//create path by /seller
			//int id is temp
			
			ModelAndView mav = new ModelAndView();
			System.out.println("getting seller");
			SingleSellerResponseDto sellerDto=sellerService.getSeller(id);
			
			mav.addObject("seller",sellerDto.getData());
			mav.setViewName("seller");
			System.out.println(model);
			
			
			
			return mav;
		} 
		
		@RequestMapping(value="/search",method = RequestMethod.GET)
		public ModelAndView searchAndFilter(ModelAndView model,@RequestParam(value="sortBy", required=false)List<String> sortValues,@RequestParam(value="status", required=false)String filter) {
			
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		Map<String,String> mapQueries=new HashMap<String,String>();
		for(String sort:sortValues) {
		mapQueries.put("sortBy", sort);
		}
		mapQueries.put("status", filter);
		SellerDto sellerDto=sellerService.searchAndFilter(mapQueries);
		mav.addObject("sellerList",sellerDto.getData());
		
			return mav;
		} 
		 
		
	
}
