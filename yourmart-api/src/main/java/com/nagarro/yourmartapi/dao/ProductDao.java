package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;

public interface ProductDao {

	Response<String> addProduct(NewProductDto product);

	Response getProducts(int id);

	Response getProduct(int id);

}
