package com.nagarro.yourmartapi.dao;

import java.util.List;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;

public interface SellerDao {

	ResponsesDto registerUser(SellerRegistrationDto sellerRegistrationDto);

	ResponsesDto loginSeller(LoginDto loginDto);
	
	Response updateSellerStatus(List<SellerStatusDto> seller);

	Response<List<SellerDetailsDto>> getSellerList();

	Response<SellerDetailsDto> getSeller(String id);

	Response<SellerDetailsDto> updateUser(String id, SellerDetailsDto sellerDetailsDto);

	Response<List<SellerDetailsDto>> filterSeller(List<String> sortBy,String status);

	Response<List<SellerDetailsDto>> seachSeller(String ownersearch, String companysearch, String mobilenumber);


}
