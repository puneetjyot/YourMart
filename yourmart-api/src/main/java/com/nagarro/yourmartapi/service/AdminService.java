package com.nagarro.yourmartapi.service;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;

public interface AdminService {
public ResponseDto<LoginDto> 	validUser(LoginDto admin) ;
}
