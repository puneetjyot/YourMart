package com.nagarro.yourmartapi.service;

import java.util.List;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;

public interface CategoryService 
{

	Response<List<String>>  getCategories();

	Response<List<NewProductDto>> getProductViaCategory(String category);

}
