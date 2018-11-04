package com.nagarro.yourmartapi.seviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.ProductStatusDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.service.ProductService;


@Component
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductDao productDao;
	
	@Override
	public Response<String> addProduct(NewProductDto product) 
	{
	return productDao.addProduct(product);	
	}

	@Override
	public Response getProducts(int id) {
		return productDao.getProducts(id);
	}

	@Override
	public Response getProduct(int id) {
		return productDao.getProduct(id);
	}

	@Override
	public Response updateProductStatus(List<ProductStatusDto> productStatusDto) {
		return productDao.updateProductStatus(productStatusDto);
	}

	@Override
	public Response getAllProduct() {
		return productDao.getAllProducts();
	}

	@Override
	public Response updateProduct(NewProductDto newProductDto) {
		// TODO Auto-generated method stub
		return productDao.updateProduct(newProductDto);
	}

}
