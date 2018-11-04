package com.nagarro.yourmart_admin.service;

import com.nagarro.yourmart_admin.dto.CategoryResponseDto;
import com.nagarro.yourmart_admin.dto.CreateCategoryDto;
import com.nagarro.yourmart_admin.dto.ProductDto;

public interface CategoryService {

	CategoryResponseDto getCategories();

	ProductDto getProduct(String name);

	CreateCategoryDto createCategory(String name);

	CreateCategoryDto deleteCategory(String name);

}
