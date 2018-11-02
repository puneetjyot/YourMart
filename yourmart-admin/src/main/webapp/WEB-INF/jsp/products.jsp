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
             
       
       </style>
</head>
<body class="bg-light">
       <div class="bg-info d-flex justify-content-between p-3 text-light">
             <span class="font-weight-light title">YourMart</span>
          <div>           
            <div>
             <form action="searchproducts" class="form-inline">
            <input type="text" class="form-control" name="searchtext"/> 
             <input type="submit" class="btn fas-fa-search" value="Search"/>
            </div>
            <div class="dropdown">
			  <button class="dropbtn">Dropdown</button>
			  <div class="dropdown-content">
			    <a href="#">Link 1</a>
			    <a href="#">Link 2</a>
			    <a href="#">Link 3</a>
			  </div>
			</div>
            <div>
            <input type="radio" name="search" value="sellerid"> Seller Id
            <input type="radio" name="search" value="companyname"> Company Name
             <input type="radio" name="search" value="productcode"> Product Code
             <input type="radio" name="search" value="productname"> Product Name
             <input type="radio" name="search" value="id"> product id
             
             </div>
             </div>
             
              
           
             </form>
             <span>
             <form action="products">
             <input class="btn-info" type="submit" value="Product Page">
             </form>
             </span>
             
             <span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
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
                                 <c:forEach var="product" items="${productList}">
                                     <a href="productprofile/${product.id}">
                                       <div class="seller-list-item my-3" >
                                              <div class="row">
                                              <div class="col-sm-1">
                                        <c:if test="${product.status.equals('NEW')}">
											<input type="checkbox" name="check" value="${product.id}">
										</c:if>
										<c:if test="${!product.status.equals('NEW')}">
													<input type="checkbox" name="check" value="" style="visibility: hidden" disabled>
										</c:if> 
                                                            </div>
                                                    <div class="col-sm-4 d-flex flex-column">
                                                           <span><c:out value="${product.productname}" /></span>
                                                           <span class="text-secondary small-label">Registered 5 days ago</span>
                                                    </div>
                                                    <div class="col-sm-2 text-sm-center">
                                                    <span class="text-secondary"><c:out value="${product.id}" /></span>
                                                     </div>
                                                    <div class="col-sm-4">
                                                           <span class="float-right pr-4"><c:out value="${product.status}" /></span>
                                                    </div>
                                              </div>
                                       </div>
                                       </a>
                                 </c:forEach>
                                 <input type="submit" class="btn-info btn" value="Approve All">
                                 </form>
                          </div>
                    </div>
             </div> 
       </div>
</body>
</html>