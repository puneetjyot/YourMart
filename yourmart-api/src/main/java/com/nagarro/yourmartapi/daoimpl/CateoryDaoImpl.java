package com.nagarro.yourmartapi.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.CategoryDao;
import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.utils.HibernateUtil;

@Component
public class CateoryDaoImpl implements CategoryDao {

	@Override
	public Response<List<String>> getCategories() {
		// TODO Auto-generated method stub

		Response<List<String>> response = new Response<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		try {

			Query query = session.createQuery(QueriesConstant.SELECT_DISTINCT_CATEGORY);

			List<String> categories = query.list();

			response.setData(categories);
			response.setMessage(null);
			response.setStatus(QueriesConstant.SUCCESS);
			return response;
		} catch (Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(QueriesConstant.SERVER_ERROR);
			return response;
		}
	}

	@Override
	public Response<List<NewProductDto>> getProductViaCategory(String category) {


		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Response<List<NewProductDto>> response = new Response<>();

		try {

			Query query = session.createQuery("from Product as p where p.id IN (select c.product.id from Category as c where c.categoryname =:category)");
			
			query.setParameter("category", category);
		
			
			
			

			List<NewProductDto> productViaCategories = query.list();

			response.setData(productViaCategories);
			response.setMessage(null);
			response.setStatus(QueriesConstant.SUCCESS);
			return response;
		} catch (Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(QueriesConstant.SERVER_ERROR);
			return response;
		}
		
		
		
	}

}
