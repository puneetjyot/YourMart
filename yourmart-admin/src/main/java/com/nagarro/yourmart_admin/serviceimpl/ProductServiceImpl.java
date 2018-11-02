package com.nagarro.yourmart_admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.yourmart_admin.dto.ProductDto;
import com.nagarro.yourmart_admin.dto.ProductResponseDto;
import com.nagarro.yourmart_admin.dto.ProductStatusDto;
import com.nagarro.yourmart_admin.dto.SellerDto;
import com.nagarro.yourmart_admin.dto.SellerStausDto;
import com.nagarro.yourmart_admin.service.ProductService;

public class ProductServiceImpl implements ProductService{

	@Override
	public ProductDto getAllProducts() {
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        Response response = target.path("products").request(MediaType.APPLICATION_JSON)
                     .get();
        
       // System.out.println(response.readEntity(SellerDto.class).getData());
        
       ProductDto products = response.readEntity(ProductDto.class);
        return products;
	}

	@Override
	public ProductDto searchAndFilter(Map<String, String> queryParameters) {
		
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        target = target.path("product");

        	for(String key: queryParameters.keySet())
        	{		
        		String value = queryParameters.get(key);
    			target = target.queryParam(value, key);  
    			System.out.println(key+" "+value);
    			}
    		
       
        Response response = target.request(MediaType.APPLICATION_JSON).get();
		
        ProductDto products = response.readEntity(ProductDto.class);
        
        return products;

	}

	@Override
	public ProductDto search(String search, String text) {
		ProductDto products=new ProductDto();

		System.out.println(search+" "+text);
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        try {
        if(search.equals("sellerid")) {
        	int id=Integer.parseInt(text);
            target = target.path("products/"+id);

        }
        
        else {
        target = target.path("list/product/search");
        }
        Response response = target.queryParam(search, text).request(MediaType.APPLICATION_JSON).get();
        products = response.readEntity(ProductDto.class);
        System.out.println(products.getStatus());
		return products;
        }
        catch(Exception e) {
        	System.out.println(e);
        	products.setStatus(405);
        	products.setData(null);
        	products.setMessage(e.getMessage());
        	return products;
        }
	        
	}

	@Override
	public void approveSeller(List<Integer> products) {

		 
			Client client = ClientBuilder.newClient();
	        WebTarget target = client.target("http://localhost:8090/");
	        target = target.path("product/status");
	        List<ProductStatusDto> productStausDto=new ArrayList<ProductStatusDto>();
	        for(Integer id:products) {
	       ProductStatusDto product=new ProductStatusDto();
	        product.setId(id);
	        product.setStatus("APPROVED");
	        productStausDto.add(product);
	        }
	        Response response = target.request(MediaType.APPLICATION_JSON)
	        		.header("token", "Bearer " + "... encoded token ...")
	                .put(Entity.entity(productStausDto, MediaType.APPLICATION_JSON));
	}

}
