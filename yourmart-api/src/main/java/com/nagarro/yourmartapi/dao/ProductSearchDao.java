package com.nagarro.yourmartapi.dao;

import java.util.List;

import com.nagarro.yourmartapi.dto.Response;

public interface ProductSearchDao {

	Response sortProduct(List<String> sortBy);

}
