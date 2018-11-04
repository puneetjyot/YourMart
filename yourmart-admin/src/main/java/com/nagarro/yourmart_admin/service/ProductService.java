package com.nagarro.yourmart_admin.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.nagarro.yourmart_admin.dto.ProductDto;
import com.nagarro.yourmart_admin.dto.ProductResponseDto;
import com.nagarro.yourmart_admin.dto.ProductStatusDto;
import com.nagarro.yourmart_admin.dto.SingleProductResponseDto;

public interface ProductService {

	ProductDto getAllProducts();

	ProductDto searchAndFilter(Map<String, String> mapQueries);

	ProductDto search(String search, String text);

	void approveSeller(List<Integer> products);

	SingleProductResponseDto getProduct(int id);

	void changeStatusProduct(ProductStatusDto productStatusDto);

}
