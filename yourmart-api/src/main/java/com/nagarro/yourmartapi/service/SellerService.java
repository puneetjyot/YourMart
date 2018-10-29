package com.nagarro.yourmartapi.service;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;

public interface SellerService {

	ResponsesDto registerSeller(SellerRegistrationDto sellerRegistrationDto);

	ResponsesDto loginSeller(LoginDto loginDto);
	public ResponsesDto updateSellerStatus(SellerStatusDto sellerStatusDto);

	SellerDetailsDto getSellerList();

}
