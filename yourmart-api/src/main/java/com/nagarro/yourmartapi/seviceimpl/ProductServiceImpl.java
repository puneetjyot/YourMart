package com.nagarro.yourmartapi.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.service.ProductService;


@Component
public class ProductServiceImpl implements ProductService{

//	@Autowired
//	ProductDao productDao;
//	
	@Override
	public void addProduct(Product product) 
	{
	productDao.addProduct();	
	}

}
