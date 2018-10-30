package com.nagarro.yourmartapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public void addProduct(@RequestBody Product product)
	{
		productService.addProduct(product);
	}
	
	
}
