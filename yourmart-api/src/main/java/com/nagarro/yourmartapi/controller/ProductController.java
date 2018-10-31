package com.nagarro.yourmartapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public Response<String> addProduct(@RequestBody NewProductDto product)
	{
		return productService.addProduct(product);
	}
	
	@GetMapping("/products/{id}")
	public Response getProduct(@PathVariable String id)
	{
		return productService.getProducts(Integer.parseInt(id));
	}
	
	
}
