
package com.nagarro.yourmartapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	SellerService sellerService;
	
	@PostMapping("/register/seller")
	private ResponsesDto RegisterSeller(@RequestBody SellerRegistrationDto sellerRegistrationDto) {
		
		return sellerService.registerSeller(sellerRegistrationDto);
	}
	
}
