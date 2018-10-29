package com.nagarro.yourmartapi.seviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.service.SellerService;

@Component
public class SellerServiceImpl implements SellerService{

	@Autowired
	SellerDao sellerDao;
	
	@Override
	public ResponsesDto registerSeller(SellerRegistrationDto sellerRegistrationDto) {
		return sellerDao.registerUser(sellerRegistrationDto);
	}

	@Override
	public ResponsesDto loginSeller(LoginDto loginDto) {
		return sellerDao.loginSeller(loginDto);
	}

}
