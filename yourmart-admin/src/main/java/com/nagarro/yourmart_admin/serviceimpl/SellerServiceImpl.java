package com.nagarro.yourmart_admin.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.yourmart_admin.dto.AdminResponseDto;
import com.nagarro.yourmart_admin.dto.SellerDto;
import com.nagarro.yourmart_admin.dto.SellerStausDto;
import com.nagarro.yourmart_admin.dto.SingleSellerResponseDto;
import com.nagarro.yourmart_admin.service.SellerService;

public class SellerServiceImpl implements SellerService {

	@Override
	public SellerDto getSellerList() {
		System.out.println("getting list");
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        Response response = target.path("list/seller").request(MediaType.APPLICATION_JSON)
                     .get();
        
       // System.out.println(response.readEntity(SellerDto.class).getData());
        
        SellerDto sellers = response.readEntity(SellerDto.class);
        return sellers;
	}

	@Override
	public SingleSellerResponseDto getSeller(int id) {
		
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        Response response = target.path("list/seller/"+id).request(MediaType.APPLICATION_JSON)
                     .get();
        
        SingleSellerResponseDto seller = response.readEntity(SingleSellerResponseDto.class);
        return seller;
	}

	@Override
	public SellerDto searchAndFilter(Map<String, String> queryParameters) {
	
		System.out.println("hiiiiii");
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        target = target.path("list/seller");

        	for(String key: queryParameters.keySet())
        	{		
        		String value = queryParameters.get(key);
    			target = target.queryParam(key, value);  
    			System.out.println(key+" "+value);
    			}
    		
       
        Response response = target.request(MediaType.APPLICATION_JSON).get();
		
        
        
        SellerDto sellers = response.readEntity(SellerDto.class);
       
        
        // System.out.println(sellers.getData().get(0).getAddress());
        
        return sellers;
        
	}

	@Override
	public SellerDto search(String search, String text) {
		 
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        target = target.path("list/seller/search");
		   
        Response response = target.queryParam(search, text).request(MediaType.APPLICATION_JSON).get();
		
		SellerDto sellers = response.readEntity(SellerDto.class);
	       	        
	        return sellers;
	}

	@Override
	public void approveSeller(List<Integer> sellers) {

		 
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        target = target.path("seller/status");
        List<SellerStausDto> sellerStausDto=new ArrayList<SellerStausDto>();
        for(Integer id:sellers) {
       SellerStausDto seller=new SellerStausDto();
        seller.setId(id);
        seller.setStatus("APPROVED");
        sellerStausDto.add(seller);
        }
        Response response = target.request(MediaType.APPLICATION_JSON)
        		.header("token", "Bearer " + "... encoded token ...")
                .put(Entity.entity(sellerStausDto, MediaType.APPLICATION_JSON));
                
  // AdminResponseDto loggedInUser = response.readEntity(AdminResponseDto.class);
        System.out.println(response);
		
	}

	@Override
	public void changeStatus(SellerStausDto sellerStatusDto) {
		
		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        target = target.path("seller/status");
        List<SellerStausDto> sellerStausDto=new ArrayList<SellerStausDto>();
        sellerStausDto.add(sellerStatusDto);
        Response response = target.request(MediaType.APPLICATION_JSON)
        		.header("token", "Bearer " + "... encoded token ...")
                .put(Entity.entity(sellerStausDto, MediaType.APPLICATION_JSON));
		
	}

}
