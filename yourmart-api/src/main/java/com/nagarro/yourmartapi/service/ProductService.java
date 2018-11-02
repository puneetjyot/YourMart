package com.nagarro.yourmartapi.service;

import java.util.List;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.ProductStatusDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Product;

public interface ProductService {

	Response<String> addProduct(NewProductDto product);

	Response getProducts(int id);

	Response getProduct(int id);

	Response updateProductStatus(List<ProductStatusDto> productStatusDto);

	Response getAllProduct();


}
