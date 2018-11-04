package com.nagarro.yourmartapi.daoimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.SellerDao;
import com.nagarro.yourmartapi.dto.LoginDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.dto.ResponsesDto;
import com.nagarro.yourmartapi.dto.SellerDetailsDto;
import com.nagarro.yourmartapi.dto.SellerRegistrationDto;
import com.nagarro.yourmartapi.dto.SellerStatusDto;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.models.SellerDetails;
import com.nagarro.yourmartapi.utils.HibernateUtil;
import com.nagarro.yourmartapi.utils.ResponseData;
import com.nagarro.yourmartapi.utils.ValidStatus;

@Component
public class SellerDaoImpl implements SellerDao {

	ResponsesDto response;

	SellerDaoImpl() {
		
		response = new ResponsesDto();

	}

	@Override
	public ResponsesDto registerUser(SellerRegistrationDto sellerRegistrationDto) {
	
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Seller seller = new Seller();
		SellerDetails sellerDetails = new SellerDetails();
		seller.setSellername(sellerRegistrationDto.getUsername());
		seller.setSellerpassword(sellerRegistrationDto.getPassword());
		seller.setSellerstatus(QueriesConstant.NEED_APPROVAL);
		//int seller_id = (int) session.save(seller);
		//System.out.println(seller_id);
		sellerDetails.setCompanyname(sellerRegistrationDto.getCompanyname());
		sellerDetails.setEmail(sellerRegistrationDto.getEmail());
		sellerDetails.setGstnumber(sellerRegistrationDto.getGstnumber());
		sellerDetails.setOwnername(sellerRegistrationDto.getOwnername());
		sellerDetails.setTelephone(sellerRegistrationDto.getTelephone());
		sellerDetails.setAddress(sellerRegistrationDto.toStringAddress());
		sellerDetails.setSeller(seller);
		if (sellerRegistrationDto.getPassword().equals(sellerRegistrationDto.getConfirmpassword())) {

			try {
				session.save(sellerDetails);
				ResponseData data=new ResponseData(seller.getId(), sellerRegistrationDto.getUsername(), "ymart-"+seller.getId());
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
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		Query loginQuery =session.createQuery(QueriesConstant.LOGIN_SELLER);

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
	
	public Response<List<SellerStatusDto>> updateSellerStatus(List<SellerStatusDto> sellerStatusDto) {
		Response<List<SellerStatusDto>> response=new Response<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		try {
			List<SellerStatusDto> list=new ArrayList<>();
		for(SellerStatusDto sellerStatus:sellerStatusDto) {
		
		Object object=session.load(Seller.class,new Integer(sellerStatus.getId()));
		
		
		Seller seller=(Seller) object;
		seller.setSellerstatus(sellerStatus.getStatus());

			
			list.add(sellerStatus);
			
		}
		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(list);
		response.setMessage(null);
		session.getTransaction().commit();
		}
		catch(Exception e) 
		{
			response.setStatus(QueriesConstant.SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
		}
		
		
			return response;
		
		
	}

	@Override
	public Response<List<SellerDetailsDto>> getSellerList() {
		List<SellerDetailsDto> allSellers=new ArrayList<SellerDetailsDto>();
		Response<List<SellerDetailsDto>> response=new Response<>();

		//Query listQuery = this.session.createQuery(QueriesConstant.SELECT_LIST);
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		
		try {
		Query listQuery = session.createQuery("from SellerDetails as s ORDER BY FIELD(s.seller.sellerstatus, 'NEED_APPROVAL','APPROVED','REJECTED')");

		List<SellerDetails> list = listQuery.list();

		for(SellerDetails seller:list) {
			SellerDetailsDto sellerDetailsDto=new SellerDetailsDto();
			
			sellerDetailsDto.setAddress(seller.getAddress());
			sellerDetailsDto.setCompanyname(seller.getCompanyname());
			sellerDetailsDto.setEmail(seller.getEmail());
			sellerDetailsDto.setGstnumber(seller.getGstnumber());
			sellerDetailsDto.setOwnername(seller.getOwnername());
			sellerDetailsDto.setTelephone(seller.getTelephone());
			sellerDetailsDto.setStatus(seller.getSeller().getSellerstatus());
			sellerDetailsDto.setId(seller.getSeller().getId());
			sellerDetailsDto.setCreatedAt(seller.getCreatedat());
			
			allSellers.add(sellerDetailsDto);
		}
		
		response.setData(allSellers);
		response.setMessage(null);
		response.setStatus(QueriesConstant.SUCCESS);
		
		
		}
		
		
		catch(Exception e) {
			response.setData(null);
			response.setMessage(e.getMessage());
			response.setStatus(QueriesConstant.NOT_FOUND_CODE);
		}
		
		
		
		return response;
	}

	@Override
	public Response<SellerDetailsDto> getSeller(String id) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

		Response<SellerDetailsDto> response=new Response<>();
		
		try {
		Query listQuery = session.createQuery("from SellerDetails as s where s.seller.id=:id" );
		listQuery.setParameter("id",Integer.parseInt(id) );
		SellerDetailsDto sellerDetailsDto=new SellerDetailsDto();
		List<SellerDetails> seller =  listQuery.list();

		sellerDetailsDto.setAddress(seller.get(0).getAddress());
		sellerDetailsDto.setCompanyname(seller.get(0).getCompanyname());

		sellerDetailsDto.setEmail(seller.get(0).getEmail());

		sellerDetailsDto.setGstnumber(seller.get(0).getGstnumber());

		sellerDetailsDto.setOwnername(seller.get(0).getOwnername());

		sellerDetailsDto.setStatus(seller.get(0).getSeller().getSellerstatus());
		
		sellerDetailsDto.setTelephone(seller.get(0).getTelephone());
		
		sellerDetailsDto.setId(seller.get(0).getSeller().getId());


		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(sellerDetailsDto);
		response.setMessage(null);
		}
		catch(Exception e) {
			response.setStatus(QueriesConstant.NOT_FOUND_CODE);
			response.setData(null);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}

	@Override
	public Response<SellerDetailsDto> updateUser(String id, SellerDetailsDto sellerDetailsDto) {
//Object object=session.load(Seller.class,new Integer(sellerStatusDto.getId()));
//		
//		
//		Seller seller=(Seller) object;
//		seller.setSellerstatus(sellerStatusDto.getStatus());
		
		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		
		Response response=new Response();
		try {
			Query listQuery = session.createQuery("from SellerDetails as s where s.seller.id=:id" );
			listQuery.setParameter("id",Integer.parseInt(id) );
			List<SellerDetails> seller =  listQuery.list();

			System.out.println(seller.get(0).getId());
			
		Object object=session.load(SellerDetails.class,new Integer(seller.get(0).getId()));
		
		SellerDetails sellerDetails=(SellerDetails) object;
		
		sellerDetails.setAddress(sellerDetailsDto.getAddress());
		
		sellerDetails.setCompanyname(sellerDetailsDto.getCompanyname());

		sellerDetails.setOwnername(sellerDetailsDto.getOwnername());

		sellerDetails.setEmail(sellerDetailsDto.getEmail());

		sellerDetails.setGstnumber(sellerDetailsDto.getGstnumber());

		sellerDetails.setTelephone(sellerDetailsDto.getTelephone());
		System.out.println(sellerDetailsDto.getTelephone());
		
		session.getTransaction().commit();
		
		
		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(sellerDetailsDto);
		response.setMessage(null);

		}
		catch (Exception e) {

			response.setStatus(QueriesConstant.SUCCESS);
			response.setData(null);
			response.setMessage(QueriesConstant.DETAILS_ERROR_MESSAGE);
		
		}
		
		return response;
	}

	@Override
	public Response<List<SellerDetailsDto>> filterSeller(List<String> status,String sortBy)  {
		Response<List<SellerDetailsDto>> response=new Response<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		List<SellerDetailsDto> sellerDetailsList=new ArrayList<>();

		try {
			System.out.println(status);
	
			System.out.println("in here");
        String whereClause = "";
        String sortOrder = " ORDER BY FIELD(sellerDetails.seller.sellerstatus, 'NEED_APPROVAL','APPROVED','REJECTED')";

        
        if(!Objects.isNull(sortBy)) {
               //whereClause = " WHERE sellerDetails.seller.sellerstatus = '" + status + "'";
        	if(!sortBy.equals("createdat")) {
        	sortOrder = " ORDER BY sellerDetails.seller."+sortBy;
        	}
        	else {
            	sortOrder = " ORDER BY sellerDetails."+sortBy;

        	}
        } 
       
        if(!Objects.isNull(status)) {
        	whereClause = " WHERE sellerDetails.seller.sellerstatus IN(";
          
        	int count=0;
        	for(String eachStatus: status) {
        		if(count!=0) {
        			whereClause+=",";
        		}
                     whereClause += "'"+eachStatus+"'";
                     count++;
               }
        	whereClause += ")";
        }
        
        System.out.println(QueriesConstant.SELECT_SELLERDETAILS_FROM_TABLE+whereClause+sortOrder);
        
        Query query = session.createQuery(QueriesConstant.SELECT_SELLERDETAILS_FROM_TABLE + whereClause + sortOrder);
        
        
        
        
        System.out.println(query.getQueryString());
        
        List<SellerDetails> sellerList = query.list();
        
        System.out.println(sellerList.get(0).getSeller().getSellername());
        
        for(SellerDetails seller:sellerList) {
			SellerDetailsDto sellerDetailsDto=new SellerDetailsDto();
			
			sellerDetailsDto.setAddress(seller.getAddress());
			sellerDetailsDto.setCompanyname(seller.getCompanyname());
			sellerDetailsDto.setEmail(seller.getEmail());
			sellerDetailsDto.setGstnumber(seller.getGstnumber());
			sellerDetailsDto.setOwnername(seller.getOwnername());
			sellerDetailsDto.setStatus(seller.getSeller().getSellerstatus());
			sellerDetailsDto.setId(seller.getSeller().getId());
			sellerDetailsDto.setCreatedAt(seller.getCreatedat());

			sellerDetailsDto.setTelephone(seller.getTelephone());
			sellerDetailsList.add(sellerDetailsDto);
		}
		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(sellerDetailsList);
		response.setMessage(null);
		
		
		
		
		}
		catch (Exception e) {
			response.setStatus(QueriesConstant.NOT_FOUND_CODE);
			response.setData(null);
			response.setMessage(e.getMessage());
		}
	return response;
	}

	@Override
	public Response<List<SellerDetailsDto>> seachSeller(String ownersearch, String companysearch, String mobilenumber) {
		Response<List<SellerDetailsDto>> response=new Response<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();
		
		
		List<SellerDetailsDto> sellerDetailsList=new ArrayList<>();

		String query="";
		if(ownersearch!=null) {
			query=" where sellerDetails.ownername like :ownername";
		}
		else if(companysearch!=null) {
			query=" where sellerDetails.companyname like :companyname";
		}
		else if(mobilenumber!=null) {
			query=" where sellerDetails.telephone like :telephone";
		}
		
		
		 Query listquery = session.createQuery(QueriesConstant.SELECT_SELLERDETAILS_FROM_TABLE + query);
		if(ownersearch!=null){
		 listquery.setParameter("ownername", "%"+ownersearch+"%");
		}
		else if(companysearch!=null) {
			 listquery.setParameter("companyname", "%"+companysearch+"%");	
		}
		else if(mobilenumber!=null) {
			 listquery.setParameter("telephone", "%"+mobilenumber+"%");
		}
		
		
		 List<SellerDetails> sellerList = listquery.list();
	        System.out.println(listquery.getQueryString());
	        System.out.println(sellerList.size());
	        
	        for(SellerDetails seller:sellerList) {
				SellerDetailsDto sellerDetailsDto=new SellerDetailsDto();
				
				sellerDetailsDto.setAddress(seller.getAddress());
				sellerDetailsDto.setCompanyname(seller.getCompanyname());
				sellerDetailsDto.setEmail(seller.getEmail());
				sellerDetailsDto.setGstnumber(seller.getGstnumber());
				sellerDetailsDto.setOwnername(seller.getOwnername());
				sellerDetailsDto.setStatus(seller.getSeller().getSellerstatus());
				sellerDetailsDto.setId(seller.getSeller().getId());
				sellerDetailsDto.setCreatedAt(seller.getCreatedat());

				sellerDetailsDto.setTelephone(seller.getTelephone());
				sellerDetailsList.add(sellerDetailsDto);
			}
	        
	        
	        response.setStatus(QueriesConstant.SUCCESS);
			response.setData(sellerDetailsList);
			response.setMessage(null);
		
		return response;
	}

}
