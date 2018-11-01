package com.nagarro.yourmartapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.Response;

@RestController
public class ProductSearchController 
{
	
	@GetMapping("/product")
	public Response getProductList(@PathVariable String id)
	{
	return null;
	}
	
	
}
