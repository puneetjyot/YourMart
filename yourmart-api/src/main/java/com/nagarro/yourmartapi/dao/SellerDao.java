package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;

public interface SellerDao {

	ResponsesDto registerUser(SellerRegistrationDto sellerRegistrationDto);

	ResponsesDto loginSeller(LoginDto loginDto);
	
	ResponsesDto updateSellerStatus(SellerStatusDto seller);

	SellerDetailsDto getSellerList();


}
