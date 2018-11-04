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

	
	
	public ProductSearchDaoImpl() {

	

	
	}
	
	
	@Override
	public Response<List<NewProductDto>> sortProduct(List<String> status ,String sortBy) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Response<List<NewProductDto>> response=new Response<>();
		String querystatement="";
		String wherequery="";
		if(!Objects.isNull(status)) {
			 wherequery="where p.status in (";
			for(String liststatus:status ) {
				System.out.println(status);
			wherequery+="'"+liststatus+"', ";
		}
			wherequery=wherequery.substring(0, wherequery.length()-2);
			wherequery+=")";
		}		
			
		if(!Objects.isNull(sortBy)) {
			querystatement=" ORDER BY  p."+sortBy;
		}
		
		
 		Query query = session.createQuery("FROM Product as p "+wherequery+querystatement);
		
		List<NewProductDto> productList=query.list();
			
	response.setData(productList);
	response.setMessage(null);
	response.setStatus(QueriesConstant.SUCCESS);
		
		return response;
	}


	@Override
	public Response searchProduct(String companysearch, String codesearch, String productnamesearch, String productid) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Response<List<NewProductDto>> response=new Response<>();
		try {
		if(!Objects.isNull(companysearch)) {
 		Query query = session.createQuery("Select s.seller.id from SellerDetails as s where s.companyname Like :companyname");
 		query.setParameter("companyname", "%"+companysearch+"%");	
		List<Integer> sellerid=query.list();
		int id=sellerid.get(0);
 		Query query1 = session.createQuery("from Product as p where p.seller.id =:sellerid");
 		query1.setParameter("sellerid", id);
		List<NewProductDto> productList=query1.list();
		
		if(productList.size()==0) {
			response.setData(productList);
			response.setMessage("No Products found");
			response.setStatus(QueriesConstant.SUCCESS);
		}
		else {
		response.setData(productList);
		response.setMessage(null);
		response.setStatus(QueriesConstant.SUCCESS);
		}
		}
		else if(!Objects.isNull(codesearch)) {
				//int productCode=Integer.parseInt(codesearch);
			Query query = session.createQuery("from Product as p where p.productcode Like :productcode");
	 		query.setParameter("productcode", "%"+codesearch+"%");	
	 		List<NewProductDto> productList=query.list();
	 		if(productList.size()==0) {
				response.setData(productList);
				response.setMessage("No Products found");
				response.setStatus(QueriesConstant.SUCCESS);
			}
	 		else {
			
			response.setData(productList);
			response.setMessage(null);
			response.setStatus(QueriesConstant.SUCCESS);
			System.out.println("------------"+QueriesConstant.SUCCESS);
	 		}
		}
		
		else if(!Objects.isNull(productnamesearch))
		{
		Query query = session.createQuery("from Product as p where p.productname Like :productname");
 		query.setParameter("productname", "%"+productnamesearch+"%");	
 		List<NewProductDto> productList=query.list();
 		if(productList.size()==0) {
			response.setData(productList);
			response.setMessage("No Products found");
			response.setStatus(QueriesConstant.SUCCESS);
		}
 		else {
		
		response.setData(productList);
		response.setMessage(null);
		response.setStatus(QueriesConstant.SUCCESS);
		System.out.println("------------"+QueriesConstant.SUCCESS);
 		}
 		}
		
		else if(!Objects.isNull(productid))
		{
			int productid1=Integer.parseInt(productid);
		Query query = session.createQuery("from Product as p where p.id =:productid");
 		query.setParameter("productid", productid1);	
 		List<NewProductDto> productList=query.list();
 		if(productList.size()==0) {
			response.setData(productList);
			response.setMessage("No Products found");
			response.setStatus(QueriesConstant.SUCCESS);
		}
 		else {
		response.setData(productList);
		response.setMessage(null);
		response.setStatus(QueriesConstant.SUCCESS);
		System.out.println("------------"+QueriesConstant.SUCCESS);
 			}
		
		}
		}
		catch (Exception e) {

			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(QueriesConstant.NOT_FOUND_CODE);
			
		}
		
		
		
		
		
		
		
		return response;
	}
	
	
	
	
}
