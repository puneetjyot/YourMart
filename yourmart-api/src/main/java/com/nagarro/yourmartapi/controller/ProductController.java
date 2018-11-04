package com.nagarro.yourmartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.ProductStatusDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.service.ProductService;

@RestController
@CrossOrigin

public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/product")
	public Response<String> addProduct(@RequestBody NewProductDto product)
	{
		return productService.addProduct(product);
	}
	
	@GetMapping("/products")
	public Response<List<NewProductDto>> getAllProducts() {
		
		return productService.getAllProduct();
	}
	
	@GetMapping("/products/{id}")
	public Response getProductList(@PathVariable String id)
	{
		
		System.out.println("--------------"+id);
		return productService.getProducts(Integer.parseInt(id));
	}
	@PutMapping("/product")
	public Response updateProduct(@RequestBody NewProductDto newProductDto,@RequestHeader String token) {
		Response response=new Response<>();
		if(token!=null) {
			System.out.println(token);
		return productService.updateProduct(newProductDto);
	}
		else {
			response.setData(null);
			response.setMessage(QueriesConstant.UNAUTHENTICATED_MESSAGE);
			response.setStatus(QueriesConstant.UNAUTHORISED_CODE);
			return response;
		}

	}
	
	@GetMapping("/singleproduct/{id}")
	public Response getProduct(@PathVariable String id) {
		
		return productService.getProduct(Integer.parseInt(id));
	}
	
	@PutMapping("/product/status")
	public Response updateProductStatus(@RequestBody List<ProductStatusDto> productStatusDto,@RequestHeader String token) {
		
		Response response=new Response<>();
		if(token!=null) {
			System.out.println(token);
		return productService.updateProductStatus(productStatusDto);
	}
		else {
			response.setData(null);
			response.setMessage(QueriesConstant.UNAUTHENTICATED_MESSAGE);
			response.setStatus(QueriesConstant.UNAUTHORISED_CODE);
			return response;
		}
	}
	
	
	
	
	
	
}
