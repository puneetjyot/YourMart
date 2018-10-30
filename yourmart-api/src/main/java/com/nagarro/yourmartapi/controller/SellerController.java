
package com.nagarro.yourmartapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;
import com.nagarro.yourmartapi.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	SellerService sellerService;
	
	@PostMapping("/register/seller")
	private ResponsesDto RegisterSeller(@RequestBody SellerRegistrationDto sellerRegistrationDto) {
		
		return sellerService.registerSeller(sellerRegistrationDto);
	}
	
	@PostMapping("/login/seller")
	private ResponsesDto Loginseller(@RequestBody LoginDto loginDto) {
		return sellerService.loginSeller(loginDto);
	}
	@PutMapping("/seller/{id}/status")
	public ResponsesDto updateSellerStatus(@PathVariable String id,@RequestHeader(value="token") String token,@RequestBody SellerStatusDto sellerStatusDto) 
	{
		
		System.out.println(id);
		System.out.println(token+"token");
		sellerStatusDto.setToken(token);
		sellerStatusDto.setId(Integer.parseInt(id));
		return sellerService.updateSellerStatus(sellerStatusDto);
	}
	
	@GetMapping("/list/seller")
	private Response<List<SellerDetailsDto>> getSellerList()
	{
		
		return sellerService.getSellerList();
	}
	
	@GetMapping("/list/seller/{id}")
	private Response<SellerDetailsDto> getSeller(@PathVariable String id)
	{
		
		return sellerService.getSeller(id);
	}
	
	@PutMapping("/seller/{id}")
	private Response<SellerDetailsDto> updateUser(@PathVariable String id,@RequestBody SellerDetailsDto sellerDetailsDto) {
		
		
		
		return sellerService.updateUser(id,sellerDetailsDto);
		
	}
	
	@GetMapping("/seller/type")
	private Response<List<SellerDetailsDto>> getUserByStatus(@RequestParam("status") String status){
		
		
		return sellerService.filterSeller(status);
	}

	
}
