package com.nagarro.yourmartapi.daoimpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.models.SellerDetails;
import com.nagarro.yourmartapi.utils.HibernateUtil;
import com.nagarro.yourmartapi.utils.ResponseData;

@Component
public class SellerDaoImpl implements SellerDao {

	Session session;
	ResponsesDto response;

	SellerDaoImpl() {
		session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		response = new ResponsesDto();

	}

	@Override
	public ResponsesDto registerUser(SellerRegistrationDto sellerRegistrationDto) {
		// TODO Auto-generated method stub
		Seller seller = new Seller();
		SellerDetails sellerDetails = new SellerDetails();
		seller.setSellername(sellerRegistrationDto.getUsername());
		seller.setSellerpassword(sellerRegistrationDto.getPassword());
		seller.setSellerstatus(QueriesConstant.NEED_APPROVAL);
		int seller_id = (int) session.save(seller);
		System.out.println(seller_id);
		sellerDetails.setCompanyname(sellerRegistrationDto.getCompanyname());
		sellerDetails.setEmail(sellerRegistrationDto.getEmail());
		sellerDetails.setGstnumber(sellerRegistrationDto.getGstnumber());
		sellerDetails.setOwnername(sellerRegistrationDto.getOwnername());
		sellerDetails.setTelephone(sellerRegistrationDto.getTelephone());
		sellerDetails.setSellerid(seller_id);
		sellerDetails.setAddress(sellerRegistrationDto.toStringAddress());
		if (sellerRegistrationDto.getPassword().equals(sellerRegistrationDto.getConfirmpassword())) {
//		if( session.save(sellerDetails) != null) {
//			ResponseData data=new ResponseData(seller_id, sellerRegistrationDto.getUsername(), "ymart-"+seller_id);
//			response.setStatus(QueriesConstant.SUCCESS);
//			response.setData(data);
//			response.setMessage(null);
//			session.getTransaction().commit();
//
//		}
//		else {
//			response.setStatus(QueriesConstant.SERVER_ERROR);
//			response.setData(null);
//			response.setMessage(QueriesConstant.SERVER_ERROR_MESSAGE);
//		}
			try {
				session.save(sellerDetails);
				ResponseData data=new ResponseData(seller_id, sellerRegistrationDto.getUsername(), "ymart-"+seller_id);
				response.setStatus(QueriesConstant.SUCCESS);
				response.setData(data);
				response.setMessage(null);
				session.getTransaction().commit();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				response.setStatus(QueriesConstant.SERVER_ERROR);
				response.setData(null);
				response.setMessage(e.getMessage());
			}

		} else {
			response.setStatus(QueriesConstant.PASSWORD_MATCH_ERROR_CODE);
			response.setData(null);
			response.setMessage(QueriesConstant.PASSWORD_NOT_MATCH);

		}
		return response;
	}

	public ResponsesDto loginSeller(LoginDto loginDto) {
		Query loginQuery = this.session.createQuery(QueriesConstant.LOGIN_SELLER);

		loginQuery.setParameter("username", loginDto.getUsername());
		loginQuery.setParameter("password", loginDto.getPassword());
		try {
		List<Object[]> list = loginQuery.list();
		if (list.size() != 0) {
		int seller_id=(int) list.get(0)[0];
		String username=(String) list.get(0)[1];
		String status=(String) list.get(0)[2];
		
		ResponseData data=new ResponseData(seller_id, username,"ymart-"+seller_id );
		if(status.equals(QueriesConstant.APPROVED)) {
		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(data);
		response.setMessage(null);
		}
		else if(status.equals(QueriesConstant.NEED_APPROVAL)) {
			response.setStatus(QueriesConstant.UNAUTHORISED_CODE);
			response.setData(null);
			response.setMessage(QueriesConstant.UNAUTHORISED_MESSAGE);
		}
		else if(status.equals(QueriesConstant.REJECTED)) {
			response.setStatus(QueriesConstant.UNAUTHORISED_CODE);
			response.setData(null);
			response.setMessage(QueriesConstant.REJECTED_MESSAGE);
		}
		}
		else {
			response.setStatus(QueriesConstant.UNAUTHENTICATED_CODE);
			response.setData(null);
			response.setMessage(QueriesConstant.UNAUTHENTICATED_MESSAGE);
		}
		}
		catch(Exception e) {
			System.out.println(e);
			response.setStatus(QueriesConstant.SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}
	
	public ResponsesDto updateSellerStatus(SellerStatusDto sellerStatusDto) {

		try {
		Object object=session.load(Seller.class,new Integer(sellerStatusDto.getId()));
		
		
		Seller seller=(Seller) object;
		seller.setSellerstatus(sellerStatusDto.getStatus());
		ResponseData data=new ResponseData(sellerStatusDto.getId(),null ,sellerStatusDto.getToken());

			session.getTransaction().commit();
			response.setStatus(QueriesConstant.SUCCESS);
			response.setData(data);
			response.setMessage(null);
		}
		catch(Exception e) 
		{
			response.setStatus(QueriesConstant.SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
		}
		finally {
			return response;
		}
		
	}

	@Override
	public SellerDetailsDto getSellerList() {
		// TODO Auto-generated method stub
		return null;
	}

}
