package com.nagarro.yourmartapi.service;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Product;

public interface ProductService {

	Response<String> addProduct(NewProductDto product);

	Response getProducts(int id);


}
