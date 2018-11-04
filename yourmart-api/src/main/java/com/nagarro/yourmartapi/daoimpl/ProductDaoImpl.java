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
import com.nagarro.yourmartapi.dto.ProductStatusDto;
import com.nagarro.yourmartapi.dto.Response;
import com.nagarro.yourmartapi.models.Category;
import com.nagarro.yourmartapi.models.Gallery;
import com.nagarro.yourmartapi.models.Product;
import com.nagarro.yourmartapi.models.Seller;
import com.nagarro.yourmartapi.utils.HibernateUtil;

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
//		product.setCreatedat(currenttime);
		
			
		product.setComments(newProductDto.getComments());
		
		product.setProductattribute(newProductDto.getProductattributes());

		product.setSeller(seller);
		
		if(newProductDto.getCategories()!=null) {

		for(String category:newProductDto.getCategories()) {
			Category categories=new Category();
			categories.setCategoryname(category);
			categories.setProduct(product);
			session.save(categories);
		}
		}
		
		if(newProductDto.getGalleryImages()!=null) {
		for(String imageurl:newProductDto.getGalleryImages() ) {
			Gallery galleryImage=new Gallery();
			galleryImage.setImageurl(imageurl);
			galleryImage.setProduct(product);
			session.save(galleryImage);
		}
			
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
		Query query = this.session.createQuery(QueriesConstant.SELECT_PRODUCT_FROM_SELLERID);
		query.setParameter("id",id);
		
		List<Product> productList=query.list();
		if(productList.size()>0) {
	
		for(Product product:productList) {
			query=this.session.createQuery(QueriesConstant.SELECT_CATEGORY_FROM_PRODUCTID);
			query.setParameter("id", product.getId());
			
			List<String> categoryName=query.list();
			String[] categoryarray=categoryName.toArray(new String[0]);
			
			query=this.session.createQuery(QueriesConstant.SELECT_GALLERY_FROM_PRODUCTID);
			query.setParameter("id", product.getId());
			
			List<String> imageUrl=query.list();
			String[] galleryarray=imageUrl.toArray(new String[0]);
			
			
			//System.out.println(product.getCreatedat());
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
		System.out.println(QueriesConstant.SUCCESS);
		
		
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

	public Response<List<NewProductDto>> getAllProducts() {
		Response<List<NewProductDto>> response=new Response<>();
		try {
			
			
		List<NewProductDto> newProductDto =new ArrayList<>();
		Query query = this.session.createQuery(QueriesConstant.FROMPRODUCT);
		
		List<Product> productList=query.list();
		if(productList.size()>0) {
	
		for(Product product:productList) {
			query=this.session.createQuery(QueriesConstant.SELECT_CATEGORY_FROM_PRODUCTID);
			query.setParameter("id", product.getId());
			
			List<String> categoryName=query.list();
			String[] categoryarray=categoryName.toArray(new String[0]);
			
			query=this.session.createQuery(QueriesConstant.SELECT_GALLERY_FROM_PRODUCTID);
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
		Query query=this.session.createQuery(QueriesConstant.SELECT_PRODUCT_FROM_PRODUCTID);
		query.setParameter("id", id);
		
		
		List<Product> productList=query.list();
		if(!productList.isEmpty()) {
		Product product=productList.get(0);
		
		query=this.session.createQuery(QueriesConstant.SELECT_CATEGORY_FROM_PRODUCTID);
		query.setParameter("id", id);
		
		List<String> categoryName=query.list();
		String[] categoryarray=categoryName.toArray(new String[0]);
		
		query=this.session.createQuery(QueriesConstant.SELECT_GALLERY_FROM_PRODUCTID);
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


	@Override
	public Response<List<ProductStatusDto>> updateProductStatus(List<ProductStatusDto> productStatusDto) {

		Response<List<ProductStatusDto>> response=new Response<>();
		try {
			
			List<ProductStatusDto> list=new ArrayList<>();
		for(ProductStatusDto product:productStatusDto) 
		{
			Object object=session.load(Product.class,new Integer(product.getId()));
			Product newproduct=(Product) object;
			
			newproduct.setStatus(product.getStatus());
			
			if(product.getComment()!=null) {
			//	System.out.println("here are the comments"+product.getComment());
				newproduct.setComments(product.getComment());

			}
			list.add(product);
		}
		response.setStatus(QueriesConstant.SUCCESS);
		response.setData(list);
		response.setMessage(null);
		session.getTransaction().commit();

		}
		
		catch (Exception e) {

			response.setStatus(QueriesConstant.SERVER_ERROR);
			response.setData(null);
			response.setMessage(e.getMessage());
		
		}
		System.out.println(response.getMessage());
		
		return response;
	}


	public Response updateProduct(NewProductDto newProductDto) {
		
	
		Response<String> response=new Response();
		try {
		Object object = session.load(Product.class, new Integer(""+newProductDto.getId()));
		Product product = (Product) object;
		//product.setCategories(newProductDto.getCategories());
		//product.setComments(newProductDto.getComments());
		product.setDimensions(newProductDto.getDimensions());
		product.setLongdescription(newProductDto.getLongdiscription());
		product.setPrimaryimage(newProductDto.getPrimaryimage());
		//product.setGalleryImages(newProductDto.getGalleryImages());
		product.setMrp(newProductDto.getMrp());
		product.setProductattribute(newProductDto.getProductattributes());
		product.setProductname(newProductDto.getProductname());
		
		//product.setSeller(newProductDto.getSellerId());
		product.setProductcode(newProductDto.getSellerproductcode());
		product.setShortdiscription(newProductDto.getShortdiscription());
		product.setSsp(newProductDto.getSsp());
		product.setYmp(newProductDto.getYmp());
		product.setUsageinstructions(newProductDto.getUsageinstructins());
		//product.setStatus(newProductDto.getStatus());
		product.setId(newProductDto.getId());
		//user.setPassword(resetUser.getPassword());
		session.getTransaction().commit();
		response.setStatus(200);
		response.setData("Product updated");
		}catch(Exception e) {
			response.setStatus(400);
			response.setMessage(e.getMessage());
		}
		
		return response;
	}


	
	
		
		
	

}
