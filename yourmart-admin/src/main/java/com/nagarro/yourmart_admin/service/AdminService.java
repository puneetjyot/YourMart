package com.nagarro.yourmart_admin.service;

import com.nagarro.yourmart_admin.dto.AdminBeforeLogin;
import com.nagarro.yourmart_admin.dto.AdminResponseDto;

public interface AdminService {

	AdminResponseDto authenticateAdmin(AdminBeforeLogin adminBeforeLogin);

}
