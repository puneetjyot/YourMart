package com.nagarro.yourmart_admin.serviceimpl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.yourmart_admin.dto.CategoryNameDto;
import com.nagarro.yourmart_admin.dto.CategoryResponseDto;
import com.nagarro.yourmart_admin.dto.CreateCategoryDto;
import com.nagarro.yourmart_admin.dto.ProductDto;
import com.nagarro.yourmart_admin.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {

	@Override
	public CategoryResponseDto getCategories() {
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        Response response = target.path("category").request(MediaType.APPLICATION_JSON)
                     .get();
        
        
        return response.readEntity(CategoryResponseDto.class);
        
	}

	@Override
	public ProductDto getProduct(String name) {
		
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        Response response = target.path("category/product/"+name).request(MediaType.APPLICATION_JSON)
                     .get();
        		return response.readEntity(ProductDto.class);
		}

	@Override
	public CreateCategoryDto createCategory(String name) {
		
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        CategoryNameDto categoryNameDto=new CategoryNameDto();
        categoryNameDto.setCategoryname(name);
        Response response = target.path("newcategory").request(MediaType.APPLICATION_JSON)
        		.post(Entity.entity(categoryNameDto, MediaType.APPLICATION_JSON));
		
		
		return response.readEntity(CreateCategoryDto.class);
	}

	@Override
	public CreateCategoryDto deleteCategory(String name) {
		
		

		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        CategoryNameDto categoryNameDto=new CategoryNameDto();
        categoryNameDto.setCategoryname(name);
        Response response = target.path("deletecategory/"+name).request(MediaType.APPLICATION_JSON)
        		.delete();
		
		
		return response.readEntity(CreateCategoryDto.class);
		
		
	}

}
