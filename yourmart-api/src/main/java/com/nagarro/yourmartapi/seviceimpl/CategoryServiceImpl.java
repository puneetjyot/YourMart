package com.nagarro.yourmartapi.seviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.CategoryDao;
import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {
	
	@Autowired
	CategoryDao categoryDao;

	@Override
	public Response<List<String>>  getCategories() {
		return categoryDao.getCategories();
	}

	@Override
	public Response<List<NewProductDto>> getProductViaCategory(String category) {
		return categoryDao.getProductViaCategory(category);
	}

}
