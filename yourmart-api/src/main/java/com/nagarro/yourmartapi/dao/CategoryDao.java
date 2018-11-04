package com.nagarro.yourmartapi.dao;

import java.util.List;

import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;

public interface CategoryDao {

	Response<List<String>>  getCategories();

	Response<List<NewProductDto>> getProductViaCategory(String category);

}
