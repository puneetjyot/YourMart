package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;

public interface SellerDao {

	ResponsesDto registerUser(SellerRegistrationDto sellerRegistrationDto);

	ResponsesDto loginSeller(LoginDto loginDto);

}
