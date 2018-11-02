package com.nagarro.yourmart_admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.nagarro.yourmart_admin.dto.SellerDto;
import com.nagarro.yourmart_admin.dto.SellerStausDto;
import com.nagarro.yourmart_admin.dto.SingleSellerResponseDto;

public interface SellerService {

	SellerDto getSellerList();

	SingleSellerResponseDto getSeller(int id);

	SellerDto searchAndFilter(Map<String, String> mapQueries);

	SellerDto search(String search, String text);

	void approveSeller(List<Integer> sellers);

	void changeStatus(SellerStausDto sellerStatusDto);

	

}
