package com.nagarro.yourmartapi.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.ProductSearchDao;
import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.utils.HibernateUtil;

@Component
public class ProductSearchDaoImpl implements ProductSearchDao{

	Session session;
	
	public ProductSearchDaoImpl() {

		session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

	
	}
	
	
	@Override
	public Response<List<Product>> sortProduct(List<String> sortBy ,String status) {
		Response<List<Product>> response=new Response<>();
		String querystatement="ORDER BY ";
		String wherequery="";
		if(!Objects.isNull(status)) {
			wherequery="where p.status = '"+status+"' ";
		}
		
		for(String sort:sortBy) {
			querystatement+="p."+sort+",";
		}
		
		querystatement=querystatement.substring(0, querystatement.length()-1);
 		Query query = this.session.createQuery("FROM Product as p "+wherequery+querystatement);
		
		List<Product> productList=query.list();
			
	response.setData(productList);
	response.setMessage(null);
	response.setStatus(QueriesConstant.SUCCESS);
		
		return response;
	}
	
	
	
	
}
