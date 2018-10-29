package com.nagarro.yourmartapi.seviceimpl;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.dao.AdminDao;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;
import com.nagarro.yourmartapi.service.AdminService;
import com.nagarro.yourmartapi.utils.HibernateUtil;

@Component
public class AdminServiceImpl implements AdminService{

	
	@Autowired
	AdminDao adminDao;
	public AdminServiceImpl() {
		
		
	}
	
	@Override
	public ResponseDto<LoginDto> validUser(LoginDto admin) {
	

//		Criteria cr = session.createCriteria(Admin.class);
//		
//		Map<String,String> conditions = new HashMap();
//		System.out.println("user"+admin.getUsername());
//		System.out.println("pass"+admin.getPassword());
//		conditions.put("username",admin.getUsername());
//		conditions.put("password",admin.getPassword());
//
//		cr.add(Restrictions.allEq(conditions));
//		 if(!cr.list().isEmpty()) {
//			 return true;
//		 }
//		return false;
//	}

	return adminDao.validUser(admin);

}
}
