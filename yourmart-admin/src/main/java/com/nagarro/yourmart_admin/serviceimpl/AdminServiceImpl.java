package com.nagarro.yourmart_admin.serviceimpl;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.nagarro.yourmart_admin.dto.AdminBeforeLogin;
import com.nagarro.yourmart_admin.dto.AdminResponseDto;
import com.nagarro.yourmart_admin.service.AdminService;

public class AdminServiceImpl implements AdminService{

	@Override
	public AdminResponseDto authenticateAdmin(AdminBeforeLogin adminBeforeLogin) {
		

		Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8090/");
        Response response = target.path("login/admin").request(MediaType.APPLICATION_JSON)
                     .post(Entity.entity(adminBeforeLogin, MediaType.APPLICATION_JSON));
        
        AdminResponseDto loggedInUser = response.readEntity(AdminResponseDto.class);
  //System.out.println(response.readEntity(AdminResponseDto.class));
        return loggedInUser;

		
	}

}
