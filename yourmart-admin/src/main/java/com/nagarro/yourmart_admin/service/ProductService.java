package com.nagarro.yourmart_admin.service;

import java.util.List;
import java.util.Map;

import com.nagarro.yourmart_admin.dto.ProductDto;
import com.nagarro.yourmart_admin.dto.ProductResponseDto;

public interface ProductService {

	ProductDto getAllProducts();

	ProductDto searchAndFilter(Map<String, String> mapQueries);

	ProductDto search(String search, String text);

	void approveSeller(List<Integer> products);

}
