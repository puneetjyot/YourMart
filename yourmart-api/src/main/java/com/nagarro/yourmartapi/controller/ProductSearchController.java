package com.nagarro.yourmartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.service.ProductSearchService;

@RestController
@CrossOrigin

public class ProductSearchController 
{
	@Autowired
	ProductSearchService productSearchService;
	
	
	@GetMapping("/product")
	public Response sortProduct(@RequestParam(value="status", required=false) List<String> status,@RequestParam(value="sortBy",required=false)String sortBy)
	{
		return productSearchService.sortProduct(status,sortBy);
		
	}
	
	@GetMapping("/list/product/search")
	public Response searchProduct(@RequestParam(value="companyname",required=false) String companysearch,@RequestParam(value="productcode",required=false) String codesearch,@RequestParam(value="productname",required=false) String productnamesearch,@RequestParam(value="id",required=false) String productid)
	{
		
		return productSearchService.searchProduct(companysearch,codesearch,productnamesearch,productid);
		
	}
	
	
	
	
	
}
