package com.nagarro.yourmartapi.service;

import java.util.List;

import com.nagarro.yourmartapi.dto.Response;

public interface ProductSearchService {

	Response sortProduct(List<String> sortBy, String status);

}
