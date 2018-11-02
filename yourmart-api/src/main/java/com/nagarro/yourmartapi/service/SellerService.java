package com.nagarro.yourmartapi.service;

import java.util.List;

import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;

public interface SellerService {

	ResponsesDto registerSeller(SellerRegistrationDto sellerRegistrationDto);

	ResponsesDto loginSeller(LoginDto loginDto);
	public Response updateSellerStatus(List<SellerStatusDto> sellerStatusDto);

	Response<List<SellerDetailsDto>> getSellerList();

	Response<SellerDetailsDto> getSeller(String id);

	Response<SellerDetailsDto> updateUser(String id, SellerDetailsDto sellerDetailsDto);

	Response<List<SellerDetailsDto>> filterSeller(String status, List<String> sortBy);

	Response<List<SellerDetailsDto>> searchSeller(String ownersearch, String companysearch, String mobilesearch);

}
