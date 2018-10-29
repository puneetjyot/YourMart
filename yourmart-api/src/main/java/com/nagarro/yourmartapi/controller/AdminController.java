package com.nagarro.yourmartapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;
import com.nagarro.yourmartapi.service.AdminService;

@RestController
public class AdminController 
{
	@Autowired
	private AdminService adminService;
	
	@PostMapping("/login/admin")
	public ResponseDto<LoginDto> verifyAdmin( @RequestBody LoginDto admin) {
		System.out.println(admin.getPassword());
		return adminService.validUser(admin);
		
	}
}
