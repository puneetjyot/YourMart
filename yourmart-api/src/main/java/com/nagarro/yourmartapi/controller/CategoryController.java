package com.nagarro.yourmartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.service.CategoryService;

@RestController
@CrossOrigin
public class CategoryController {

	@Autowired
	CategoryService categoryService;
	
	@GetMapping("/category")
	public Response<List<String>> getCategories() {
		
		
		return categoryService.getCategories();
		
	}
	
	@GetMapping("/category/product/{categoryname}")
	public Response<List<NewProductDto>> getProductViaCategory(@PathVariable(value="categoryname")String category  ){
		return categoryService.getProductViaCategory(category);
		
	}
	

}
