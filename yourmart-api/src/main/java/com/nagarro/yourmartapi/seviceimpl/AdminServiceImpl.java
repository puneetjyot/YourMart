package com.nagarro.yourmartapi.seviceimpl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.AdminDao;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;
import com.nagarro.yourmartapi.service.AdminService;
import com.nagarro.yourmartapi.utils.HibernateUtil;

@Component
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	AdminDao adminDao;
	public AdminServiceImpl() {
		
		
	}
	
	@Override
	public ResponsesDto validUser(LoginDto admin) {


	return adminDao.validUser(admin);

}

	
}
