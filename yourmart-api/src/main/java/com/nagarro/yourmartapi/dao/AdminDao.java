package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.AdminDto;
import com.nagarro.yourmartapi.dto.ResponseDto;

public interface AdminDao {

	ResponseDto<AdminDto> validUser(AdminDto admin);

}
