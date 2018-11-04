package com.nagarro.yourmartapi.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.AdminDao;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.utils.HibernateUtil;
import com.nagarro.yourmartapi.utils.ResponseData;

@Component
public class AdminDaoImpl implements AdminDao {

	

	public AdminDaoImpl() {
	
	}

	ResponsesDto response = new ResponsesDto();

	public ResponsesDto validUser(LoginDto admin) {

		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		
		Query loginQuery = session.createQuery(QueriesConstant.SELECT_ALL_ADMINS);

		loginQuery.setParameter("username", admin.getUsername());
		loginQuery.setParameter("password", admin.getPassword());
		// List<Object> list=loginQuery.getResultList();
		List<Object[]> list = loginQuery.list();
		int seller_id=Integer.parseInt("" + list.get(0)[0]);
		ResponseData data=new ResponseData(seller_id,(String) list.get(0)[1] , "ym-"+seller_id);
		
		if (list.size() != 0) {
			
			response.setStatus(200);
			response.setData(data);
			response.setMessage(null);
		} else {
			response.setData(null);
			response.setStatus(401);
			response.setMessage(QueriesConstant.UNAUTHORISED_MESSAGE);
		}

		return response;
	}

	

}
