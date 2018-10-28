
package com.nagarro.yourmartapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	SellerService sellerService;
	
}
