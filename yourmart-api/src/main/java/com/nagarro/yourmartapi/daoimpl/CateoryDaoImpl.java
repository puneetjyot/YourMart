package com.nagarro.yourmartapi.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.CategoryDao;
import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Category;
import com.nagarro.yourmartapi.models.Product;
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

			Query query = session.createQuery(QueriesConstant.SELECT_PRODUCT_FROM_CATEGORY);
			
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

	@Override
	public Response<String> addCategory(String categoryname) {


		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Response<String> response=new Response();
		try {
		Category category=new Category();
		category.setCategoryname(categoryname);
		session.save(category);
		session.getTransaction().commit();
		response.setData(categoryname);
		response.setMessage(null);
		response.setStatus(QueriesConstant.SUCCESS);
		
		}
		catch(Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(QueriesConstant.SERVER_ERROR);
		}
		
		
		return response;
	}

	@Override
	public Response<String> deleteCategory(String name) {


		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Response<String> response=new Response();
		try {
		Query query=session.createQuery("Select c.id from Category as c where c.categoryname= :name");
		query.setParameter("name", name);
		List<Integer> id=query.list();
		if(id.size()>0) {
			System.out.println(id.get(0));
			
//		Object object=session.load(Category.class,33);
//		
//		Category category=(Category) object;
//		System.out.println(category.getCategoryname());
//		session.delete(category);
			
			Query delquery=session.createQuery("delete from Category as c where c.id= :name");
					delquery.setParameter("name", id.get(0));

					delquery.list();
		response.setData(name+"deleted");
		response.setMessage(null);
		response.setStatus(QueriesConstant.SUCCESS);
		session.getTransaction().commit();
		}
		}
		catch (Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(QueriesConstant.SERVER_ERROR);
		}
		return response;
	}

}
