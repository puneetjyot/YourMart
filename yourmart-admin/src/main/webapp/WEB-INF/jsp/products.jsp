<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
      <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
      
<html>
<head>
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.4.2/css/all.css" integrity="sha384-/rXc/GQVaYpyDdyxK+ecHPVYJSN9bmVFBvjA/9eOB+pb3F2w2N6fc5qB9Ew5yIns" crossorigin="anonymous">

		
       <style>
       
             .title {
                    font-size: 1.8rem;
             }
             
             .welcome {
                    font-size: 1.3rem;
             }
             
             .list-title {
                    font-size: 2rem;
             }
             
             .form-label {
                    
             }
             
             .small-label {
                    font-size: 0.75rem;
             }
             
             .filters {
                    margin-top: 75px;
             }
             
             .seller-list-item {
                    border: 1px solid #EAECEE;
                    box-shadow: 1px 1px #EAECEE;
                    border-radius: 10px;
                    padding: 15px;
             }
             
             .dropbtn {
			    background-color: #4CAF50;
			    color: white;
			    padding: 16px;
			    font-size: 16px;
			    border: none;
			}
			
			.dropdown {
			    position: relative;
			    display: inline-block;
			}
			
			.dropdown-content {
			    display: none;
			    position: absolute;
			    background-color: #f1f1f1;
			    min-width: 160px;
			    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
			    z-index: 1;
			}
			
			.dropdown-content a {
			    color: black;
			    padding: 12px 16px;
			    text-decoration: none;
			    display: block;
			}
			
			.dropdown-content a:hover {background-color: #ddd;}
			
			.dropdown:hover .dropdown-content {display: block;}
			
			.dropdown:hover .dropbtn {background-color: #3e8e41;}
		     .card {
				 width:300px;
					}
					.checkbox{
					width:10px;}
					 .submit-btn {
			    background: none;
			    border: none;
			    color: #ffffff;
			    cursor: pointer;
			}
			
			.image {
				max-height: 200px;
			}
       
       
       </style>
</head>
<body class="bg-light">
	<div class="bg-info d-flex justify-content-between p-3 text-light">
    	<span class="font-weight-light title">YourMart</span>
        <div class="d-flex">           
        	<div>
            	<form action="searchproducts" class="form-inline">
        	 <div class="dropdown">
			  <button class="btn-info" style="height: 40px">
			  <i class="fas fa-search p-2"></i>
			  </button>
			  <div class="dropdown-content">
			    <div class="text-dark p-2">
	            	<input type="radio" name="search" value="sellerid"> Seller Id<br>
	            	<input type="radio" name="search" value="companyname"> Company Name<br>
	             	<input type="radio" name="search" value="productcode"> Product Code<br>
	             	<input type="radio" name="search" value="productname"> Product Name<br>
	             	<input type="radio" name="search" value="id"> product id
	            </div>
			  </div>
			</div>
        	
        	
            		<input type="text" class="form-control" name="searchtext"/> 
             		<input type="submit" class="btn fas-fa-search" value="Search"/>
             	</form>
            </div>
            
           
		</div>
		<div class="d-flex">
        <span class="mx-3">
        	<form action="products">
            	<input  class="submit-btn" type="submit" value="Product Page">
            </form>
        </span>
        <span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
	</div>
	</div>
    <div class="container py-4">
 		<div class="row">
        	<div class="col-sm-3 px-5 filters">
            	<div class="">
                	<form action="filterproduct">
                    	<div class="mt-4 mb-2 form-label text-info text-sm-center">Sort By</div>
                        <input type="radio" name="sortBy" value="mrp"> Mrp<br>
                        <input type="radio" name="sortBy" value="ssp"> Ssp<br>
                        <input type="radio" name="sortBy" value="ymp"> Ymp<br>
                        <input type="radio" name="sortBy" value="createdAt"> Registration time<br>
                        <div class="mt-4 mb-2 form-label text-info text-sm-center">Filter By</div>
                        <input type="checkbox" name="status" value="NEW"> New<br>
                        <input type="checkbox" name="status" value="APPROVED"> Approved<br>
                        <input type="checkbox" name="status" value="REJECTED"> Rejected<br>
                        <input type="checkbox" name="status" value="REVIEW"> Review<br>
                        <div class="">
                        	<input type="submit" value="Apply" class="mt-4 btn btn-info w-100">
                        </div>
                   	</form>
				</div>
			</div>
            <div class="col-sm-8">
            	<div class="list-title font-weight-light">Products</div>
                <div>
                	<form action="approveproduct">
                		<div class="row d-flex justify-content-center">
	                    	<c:forEach var="product" items="${productList}">
	                        	<div class="col-sm-5 m-2">
	                            	<div class="card">
	                            		<a href="productprofile/${product.id}">
								  			<img class="card-img-top image"  src="${product.primaryimage}" alt="Card image cap">
								  			<h4 class="px-3">"${product.shortdiscription }"</h4> 
								   		</a>
								   		<div class="card-body">
										    <h6 class="text-secondary"><c:out value="${product.sellerproductcode}" /></h6>
										    <h5 class="text-secondary">MRP rs.<c:out value="${product.mrp}" /></h5>
										    <h6 class="text-secondary"><c:out value="${product.shortdiscription}" /></h6>
											<div>
		                                        <c:if test="${product.status.equals('NEW')}">
													<input type="checkbox" name="check" value="${product.id}">
												</c:if>
												<c:if test="${!product.status.equals('NEW')}">
													<input type="checkbox" name="check" value="" style="visibility: hidden" disabled>
												</c:if> 
	                                        </div>
									  	</div>
									</div>
								</div>
							</c:forEach>
						</div>
						<div class="d-flex justify-content-center my-3">
                    		<input type="submit" class="btn-info btn" value="Approve All">
						</div>
					</form>
				</div>
			</div>
		</div> 
	</div>
</body>
</html>