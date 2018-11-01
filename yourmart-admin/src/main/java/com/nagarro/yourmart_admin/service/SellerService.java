package com.nagarro.yourmart_admin.service;

import com.nagarro.yourmart_admin.dto.SellerDto;
import com.nagarro.yourmart_admin.dto.SingleSellerResponseDto;

public interface SellerService {

	SellerDto getSellerList();

	SingleSellerResponseDto getSeller(int id);

}
