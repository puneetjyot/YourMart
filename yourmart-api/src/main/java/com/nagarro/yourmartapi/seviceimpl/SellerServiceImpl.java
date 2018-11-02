package com.nagarro.yourmartapi.seviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;
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
	public Response updateSellerStatus(List<SellerStatusDto> seller) {
		return sellerDao.updateSellerStatus(seller);		
	}

	@Override
	public Response<List<SellerDetailsDto>> getSellerList() {

		return sellerDao.getSellerList();
	}

	@Override
	public Response<SellerDetailsDto> getSeller(String id) {
		return sellerDao.getSeller(id);
	}

	@Override
	public Response<SellerDetailsDto> updateUser(String id, SellerDetailsDto sellerDetailsDto) {
		return sellerDao.updateUser(id,sellerDetailsDto);
	}

	@Override
	public Response<List<SellerDetailsDto>> filterSeller(String status,List<String> sortBy) {
		return sellerDao.filterSeller(status,sortBy);
	}

	@Override
	public Response<List<SellerDetailsDto>> searchSeller(String ownersearch, String companysearch,String mobilenumber) {
		return sellerDao.seachSeller(ownersearch,companysearch,mobilenumber);
	}
}
