package com.nagarro.yourmartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.AddCategoryDto;
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
		System.out.println("sdsdd");
		return categoryService.getProductViaCategory(category);
		
	}
	
	@PostMapping("/newcategory")
	public Response<String> addCategory(@RequestBody AddCategoryDto addCategoryDto){
		
	String categoryname=addCategoryDto.getCategoryname();
		return categoryService.addCategory(categoryname);
	}
	
	@DeleteMapping("/deletecategory/{name}")
	public Response<String> deletCategory(@PathVariable(value="name")String name){
		
		return categoryService.deleteCategory(name);
	}

}
