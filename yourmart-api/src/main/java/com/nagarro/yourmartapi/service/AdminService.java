package com.nagarro.yourmartapi.service;

import com.nagarro.yourmartapi.dto.AdminDto;
import com.nagarro.yourmartapi.dto.ResponseDto;

public interface AdminService {
public ResponseDto<AdminDto> 	validUser(AdminDto admin) ;
}
