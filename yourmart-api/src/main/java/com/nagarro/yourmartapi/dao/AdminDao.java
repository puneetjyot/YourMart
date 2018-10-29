package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;

public interface AdminDao {

	ResponseDto<LoginDto> validUser(LoginDto admin);

}
