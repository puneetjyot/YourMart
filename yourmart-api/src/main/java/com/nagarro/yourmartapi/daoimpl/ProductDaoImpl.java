package com.nagarro.yourmartapi.daoimpl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import com.nagarro.yourmartapi.constant.QueriesConstant;
import com.nagarro.yourmartapi.dao.ProductDao;
import com.nagarro.yourmartapi.dto.NewProductDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Category;
import com.nagarro.yourmartapi.models.Gallery;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.utils.HibernateUtil;
import com.nagarro.yourmartapi.utils.ResponseData;

@Component
public class ProductDaoImpl implements ProductDao {

	
	Session session;
	
	public ProductDaoImpl() {

		session = HibernateUtil.getSessionFactory().openSession();

		session.beginTransaction();

	
	}
	
	
	@Override
	public Response<String> addProduct(NewProductDto newProductDto)
	{
		
		
		Response<String> response=new Response();
		try {
		Seller seller = new Seller();
		seller.setId(newProductDto.getSellerId());
		Product product=new Product();
		
		product.setProductcode(newProductDto.getSellerproductcode());
		
		
		product.setProductname(newProductDto.getProductname());
		product.setShortdiscription(newProductDto.getShortdiscription());
		product.setLongdescription(newProductDto.getLongdiscription());	
		product.setDimensions(newProductDto.getDimensions());
		product.setMrp(newProductDto.getMrp());
		product.setSsp(newProductDto.getSsp());
		product.setYmp(newProductDto.getYmp()); 
		product.setPrimaryimage(newProductDto.getPrimaryimage());
		product.setUsageinstructions(newProductDto.getUsageinstructins());
		product.setStatus(QueriesConstant.NEED_APPROVAL);
		LocalDateTime currenttime=LocalDateTime.now();
		
			
		product.setComments(newProductDto.getComments());
		
		product.setProductattribute(newProductDto.getProductattributes());

		product.setSeller(seller);
		
		for(String category:newProductDto.getCategories()) {
			Category categories=new Category();
			categories.setCategoryname(category);
			categories.setProduct(product);
			session.save(categories);
		}
		
		for(String imageurl:newProductDto.getGalleryImages() ) {
			Gallery galleryImage=new Gallery();
			galleryImage.setImageurl(imageurl);
			galleryImage.setProduct(product);
			session.save(galleryImage);
			
			
		}
		
		if (session.save(product) != null) {
			Response responseData = new Response();
			System.out.println("seller id:"+seller.getId());
			response.setStatus(200);

			session.getTransaction().commit();

	
	} else {
		
		response.setStatus(400);
		response.setMessage("Unable to add Data");

	}
		}
		catch(Exception e) {
			System.out.println(e);
			response.setStatus(400);
			response.setMessage(e.getMessage());
	}
		return response;
	}


	@Override
	public Response getProducts(int id) {
		System.out.println(id);
		Response<List<NewProductDto>> response=new Response();
		List<NewProductDto> newProductDto =new ArrayList<>();
		Query query = this.session.createQuery("FROM Product where sellerid=:id");
		query.setParameter("id",id);
		
		List<Product> productList=query.list();
	
		for(Product product:productList) {
			System.out.println(product.getLongdescription());
		}
		
		
//		for(Category product:productList) {
//			System.out.println(product);
//		}
//		

		
		
		return null;
	}
		
		
	

}
