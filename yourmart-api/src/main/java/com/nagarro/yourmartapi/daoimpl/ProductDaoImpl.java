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
	public Response<List<NewProductDto>> getProducts(int id) {
		Response<List<NewProductDto>> response=new Response<>();
		try {
		List<NewProductDto> newProductDto =new ArrayList<>();
		Query query = this.session.createQuery("FROM Product where sellerid=:id");
		query.setParameter("id",id);
		
		List<Product> productList=query.list();
		if(productList.size()>0) {
	
		for(Product product:productList) {
			query=this.session.createQuery("Select categories.categoryname From Category as categories where categories.product.id=:id");
			query.setParameter("id", product.getId());
			
			List<String> categoryName=query.list();
			String[] categoryarray=categoryName.toArray(new String[0]);
			
			query=this.session.createQuery("Select gallery.imageurl From Gallery as gallery where gallery.product.id=:id");
			query.setParameter("id", product.getId());
			
			List<String> imageUrl=query.list();
			String[] galleryarray=imageUrl.toArray(new String[0]);
			
			
			System.out.println(product.getCreatedat());
			NewProductDto productDto=new NewProductDto();
			 productDto.setCategories(categoryarray);
			 productDto.setGalleryImages(galleryarray);
			productDto.setComments(product.getComments());
			productDto.setCreatedat(product.getCreatedat());
			productDto.setDimensions(product.getDimensions());
			productDto.setId(product.getId());
			productDto.setLongdiscription(product.getLongdescription());
			productDto.setMrp(product.getMrp());
			productDto.setPrimaryimage(product.getPrimaryimage());
			productDto.setProductattributes(product.getProductattribute());
			productDto.setProductname(product.getProductname());
			productDto.setSellerId(product.getSeller().getId());
			productDto.setSellerproductcode(product.getProductcode());
			productDto.setShortdiscription(product.getShortdiscription());
			productDto.setSsp(product.getSsp());
			productDto.setStatus(product.getStatus());
			productDto.setUpdatedat(product.getUpdatedat());
			productDto.setUsageinstructins(product.getUsageinstructions());
			productDto.setYmp(product.getYmp());
			newProductDto.add(productDto);
		}
		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(newProductDto);
		response.setMessage(null);
		
		
		
		}
		else {
			response.setStatus(QueriesConstant.NOT_FOUND_CODE);
			response.setData(null);
			response.setMessage(QueriesConstant.NOPRODUCTFOUND);
		}
		}
		catch (Exception e) {

			response.setStatus(QueriesConstant.SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
		}

		
		
		return response;
	}


	@Override
	public Response<NewProductDto> getProduct(int id) {
		
		Response<NewProductDto> response=new Response<>();
		
		try {
		Query query=this.session.createQuery("from Product as product where product.id=:id");
		query.setParameter("id", id);
		
		
		List<Product> productList=query.list();
		if(!productList.isEmpty()) {
		Product product=productList.get(0);
		
		query=this.session.createQuery("Select categories.categoryname From Category as categories where categories.product.id=:id");
		query.setParameter("id", id);
		
		List<String> categoryName=query.list();
		String[] categoryarray=categoryName.toArray(new String[0]);
		
		query=this.session.createQuery("Select gallery.imageurl From Gallery as gallery where gallery.product.id=:id");
		query.setParameter("id", id);
		
		List<String> imageUrl=query.list();
		String[] galleryarray=imageUrl.toArray(new String[0]);
		
		NewProductDto productDto=new NewProductDto();
		 productDto.setCategories(categoryarray);
		 productDto.setGalleryImages(galleryarray);
		productDto.setComments(product.getComments());
		productDto.setCreatedat(product.getCreatedat());
		productDto.setDimensions(product.getDimensions());
		productDto.setId(product.getId());
		productDto.setLongdiscription(product.getLongdescription());
		productDto.setMrp(product.getMrp());
		productDto.setPrimaryimage(product.getPrimaryimage());
		productDto.setProductattributes(product.getProductattribute());
		productDto.setProductname(product.getProductname());
		productDto.setSellerId(product.getSeller().getId());
		productDto.setSellerproductcode(product.getProductcode());
		productDto.setShortdiscription(product.getShortdiscription());
		productDto.setSsp(product.getSsp());
		productDto.setStatus(product.getStatus());
		productDto.setUpdatedat(product.getUpdatedat());
		productDto.setUsageinstructins(product.getUsageinstructions());
		productDto.setYmp(product.getYmp());
		
		
		
		
		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(productDto);
		response.setMessage(null);
		
		}
		
		
		
		
	
		else {
			response.setStatus(QueriesConstant.NOT_FOUND_CODE);
			response.setData(null);
			response.setMessage(QueriesConstant.NOPRODUCTFOUND);
		}
		
		}
		
		
		catch (Exception e) {
			

			response.setStatus(QueriesConstant.SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
		}
		return response;
	}
	
		
		
	

}
