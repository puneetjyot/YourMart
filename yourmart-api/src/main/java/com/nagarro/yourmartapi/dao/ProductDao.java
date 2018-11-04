package com.nagarro.yourmartapi.dao;

import java.util.List;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.ProductStatusDto;
import com.nagarro.yourmartapi.dto.Response;

public interface ProductDao {

	Response<String> addProduct(NewProductDto product);

	Response<List<NewProductDto>> getProducts(int id, int offset);

	Response<NewProductDto> getProduct(int id);

	Response updateProductStatus(List<ProductStatusDto> productStatusDto);

	Response<List<NewProductDto>> getAllProducts();

	Response updateProduct(NewProductDto newProductDto);

}
