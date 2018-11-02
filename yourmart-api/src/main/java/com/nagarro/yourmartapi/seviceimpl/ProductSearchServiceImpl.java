package com.nagarro.yourmartapi.seviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.ProductSearchDao;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.service.ProductSearchService;

@Component
public class ProductSearchServiceImpl implements ProductSearchService {

	@Autowired
	ProductSearchDao productSearchDao;

	@Override
	public Response sortProduct(List<String> sortBy,String status) {
		// TODO Auto-generated method stub
		return productSearchDao.sortProduct(sortBy,status);
	}
	
}
