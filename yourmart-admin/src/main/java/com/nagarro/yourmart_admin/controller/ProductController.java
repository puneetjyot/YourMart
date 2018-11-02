package com.nagarro.yourmart_admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.yourmart_admin.dto.ProductDto;
import com.nagarro.yourmart_admin.dto.SellerDto;
import com.nagarro.yourmart_admin.service.ProductService;
import com.nagarro.yourmart_admin.dto.SingleProductResponseDto;

@Controller
public class ProductController {

	
	@Autowired
	ProductService productService;
	
	@RequestMapping(value="/products" ,method=RequestMethod.GET)
	public ModelAndView getAllProducts(HttpServletRequest request) {
		
		ModelAndView mav=new ModelAndView();
		mav.setViewName("products");
		 ProductDto products=productService.getAllProducts();
			mav.addObject("productList",products.getData());
			HttpSession session=request.getSession();
			String user=(String) session.getAttribute("user");
			mav.addObject("user",user);
			

		 return mav;
	}
	
	@RequestMapping(value="/filterproduct" ,method=RequestMethod.GET)
	public ModelAndView filterAndSearchProducts(@RequestParam(value="sortBy", required=false)String sortValues,@RequestParam(value="status", required=false)List<String> filter) {
		ModelAndView mav=new ModelAndView();
		mav.setViewName("products");
		
		if(Objects.isNull(filter)&&Objects.isNull(sortValues) ) {
			 ProductDto products=productService.getAllProducts();
				mav.addObject("productList",products.getData());
			return mav;
		}
		
		
	
	
	Map<String,String> mapQueries=new HashMap<String,String>();
	if(!Objects.isNull(filter)) {
	for(String status:filter) {
	mapQueries.put(status, "status");
	}
	}
	if(!Objects.isNull(sortValues)) {
	mapQueries.put(sortValues, "sortBy");
	}
	ProductDto products=productService.searchAndFilter(mapQueries);
	mav.addObject("productList",products.getData());
		
		return mav;
	}
	
	@RequestMapping(value="/searchproducts" ,method=RequestMethod.GET)
	
	public ModelAndView search(ModelAndView model,@RequestParam(value="search", required=false)String search,@RequestParam(value="searchtext", required=false)String text) {
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("products");
		if(Objects.isNull(search)||Objects.isNull(text)) {

			 ProductDto products=productService.getAllProducts();
				mav.addObject("productList",products.getData());
			return mav;
		}
		

	
	ProductDto products=productService.search(search,text);
	System.out.println(products.getStatus()+"-------");
	mav.addObject("productList",products.getData());
	
		return mav;
	} 
	
	
	@RequestMapping(value="/approveproduct",method=RequestMethod.GET)
	public ModelAndView approveSeller(ModelAndView model,@RequestParam(value="check",required=false)List<Integer> products ) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("products");
		
		if(Objects.isNull(products)) {

			 ProductDto productDto=productService.getAllProducts();
				mav.addObject("productList",productDto.getData());
			return mav;
		}
		
		productService.approveSeller(products);
		
		ProductDto productDto=productService.getAllProducts();
		mav.addObject("productList",productDto.getData());
		return mav;
	}
	
	@RequestMapping(value="/productprofile/{id}",method=RequestMethod.GET)
	public ModelAndView productProfile(ModelAndView model,@PathVariable("id") int id ) {
		
		System.out.println("getting profile");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("products");
		System.out.println(id);
		
		try {
			
			//SingleProductResponseDto singleProductResponseDto=productService.getProduct(id);
			//mav.addObject("seller",SingleProductResponseDto.getData());
			
		}
		
		catch(Exception e) {
			System.out.println("dmd");
			System.out.println(e);
		}
		
		return mav;
	}
	
	
}
