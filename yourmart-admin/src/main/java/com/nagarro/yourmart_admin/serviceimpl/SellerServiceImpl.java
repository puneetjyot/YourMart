package com.nagarro.yourmart_admin.serviceimpl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.yourmart_admin.dto.AdminResponseDto;
import com.nagarro.yourmart_admin.dto.SellerDetailsDto;
import com.nagarro.yourmart_admin.dto.SellerDto;
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
        Response response = target.path("list/seller"+id).request(MediaType.APPLICATION_JSON)
                     .get();
        
        SingleSellerResponseDto seller = response.readEntity(SingleSellerResponseDto.class);
        return seller;
	}

}
