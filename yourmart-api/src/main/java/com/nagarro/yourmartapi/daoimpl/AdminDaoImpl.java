package com.nagarro.yourmartapi.daoimpl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.AdminDao;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponseDto;
import com.nagarro.yourmartapi.utils.HibernateUtil;

@Component
public class AdminDaoImpl implements AdminDao {

	Session session;

	public AdminDaoImpl() {
		session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
	}

	ResponseDto<LoginDto> response = new ResponseDto();

	public ResponseDto<LoginDto> validUser(LoginDto admin) {

		Query loginQuery = this.session.createQuery(QueriesConstant.SELECT_ALL_ADMINS);

		loginQuery.setParameter("username", admin.getUsername());
		loginQuery.setParameter("password", admin.getPassword());
		// List<Object> list=loginQuery.getResultList();
		List<Object[]> list = loginQuery.list();

		LoginDto verifiedAdmin = new LoginDto();
		if (list.size() != 0) {
			verifiedAdmin.setId(Integer.parseInt("" + list.get(0)[0]));
			verifiedAdmin.setUsername((String) list.get(0)[1]);
			response.setStatus("200");
			response.setData(verifiedAdmin);
			response.setMessage(null);
		} else {
			response.setData(null);
			response.setStatus("401");
			response.setMessage("Unauthorized user");
		}

		return response;
	}

}
