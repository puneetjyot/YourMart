package com.nagarro.yourmartapi.dao;

import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;

public interface AdminDao {

	ResponsesDto validUser(LoginDto admin);


}
