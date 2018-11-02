package com.nagarro.yourmartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.service.ProductSearchService;

@RestController
public class ProductSearchController 
{
	@Autowired
	ProductSearchService productSearchService;
	
	
	@GetMapping("/product")
	public Response sortProduct(@RequestParam(value="sortBy", required=false) List<String> sortBy)
	{
		return productSearchService.sortProduct(sortBy);
		
	}
	
	
}
