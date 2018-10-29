package com.nagarro.yourmartapi.service;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;

public interface AdminService {
public ResponsesDto 	validUser(LoginDto admin) ;

}
