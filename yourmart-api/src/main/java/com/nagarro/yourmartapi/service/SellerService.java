package com.nagarro.yourmartapi.service;

import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;

public interface SellerService {

	ResponsesDto registerSeller(SellerRegistrationDto sellerRegistrationDto);

}
