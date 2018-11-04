package com.nagarro.yourmart_admin.controller;

import javax.enterprise.inject.Model;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.yourmart_admin.dto.CategoryResponseDto;
import com.nagarro.yourmart_admin.dto.CreateCategory;
import com.nagarro.yourmart_admin.dto.CreateCategoryDto;
import com.nagarro.yourmart_admin.dto.ProductDto;
import com.nagarro.yourmart_admin.service.CategoryService;

@Controller
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	
	@RequestMapping(value="/category",method = RequestMethod.GET)  
    public ModelAndView getCategories(HttpServletRequest request){  
		ModelAndView mav = new ModelAndView();
		System.out.println("getting Categories");
		HttpSession session=request.getSession();
		String user=(String) session.getAttribute("user");

		CategoryResponseDto categories=categoryService.getCategories();

			mav.addObject("categoryList",categories.getData());
		
		
		mav.addObject("user",user);
		mav.setViewName("category");
		return mav;
		
		
		}  
	
	@RequestMapping(value="/category/{name}",method = RequestMethod.GET)  
    public ModelAndView getProductViaCategories(@PathVariable("name") String name){  
		ModelAndView mav = new ModelAndView();
		
		
		ProductDto products=categoryService.getProduct(name);

			//mav.addObject("categoryList",categories.getData());
		
			mav.addObject("productList",products.getData());
	mav.addObject("category",name);
		mav.setViewName("categorywiseproducts");
		return mav;
		
		
		}  
	
	@RequestMapping(value="/create",method = RequestMethod.POST)  
	
	public ModelAndView createCategory(@ModelAttribute CreateCategory create){  
		ModelAndView mav = new ModelAndView();
		
		System.out.println(create.getName());
		//ProductDto products=categoryService.getProduct(name);

			//mav.addObject("categoryList",categories.getData());
		
			//mav.addObject("productList",products.getData());
		
		CreateCategoryDto newCategory=categoryService.createCategory(create.getName());

		
		CategoryResponseDto categories=categoryService.getCategories();

		mav.addObject("categoryList",categories.getData());
		
		mav.setViewName("category");
		return mav;
		
		
		}  
	
	@RequestMapping(value="/category/delete/{name}",method = RequestMethod.GET)  
    public ModelAndView deleteCategory(@PathVariable("name") String name){  
		ModelAndView mav = new ModelAndView();
		
		
		CreateCategoryDto category=categoryService.deleteCategory(name);

			//mav.addObject("categoryList",categories.getData());
		
			//mav.addObject("productList",category.getData());
	mav.addObject("category",name);

	CategoryResponseDto categories=categoryService.getCategories();

	mav.addObject("categoryList",categories.getData());
		mav.setViewName("category");
		return mav;
		
		
		}  
	
	
	
}
