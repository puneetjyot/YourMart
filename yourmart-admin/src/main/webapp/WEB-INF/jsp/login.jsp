

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
       
       </style>
</head>
<body class="bg-light">
       <div class="bg-info d-flex justify-content-between p-3 text-light">
             <span class="font-weight-light title">YourMart</span>
          <div>           
            <div>
             <form action="search" class="form-inline">
            <input type="text" class="form-control" name="searchtext"/> 
             <input type="submit" class="btn fas-fa-search" value="Search"/>
            </div>
            <div>
            
             <input type="radio" name="search" value="companyname"> Company Name
             <input type="radio" name="search" value="ownername"> Owner name
             <input type="radio" name="search" value="telephone"> Contact Number
             </div>
             </div>
             
              
           
             </form>
             
             <span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
       </div>
       <div class="container py-4">
             <div class="row">
                    <div class="col-sm-3 px-5 filters">
                          <div class="">
                                 <form action="filter">
                                       <div class="mt-4 mb-2 form-label text-info text-sm-center">Sort By</div>
                                       <input type="checkbox" name="sortBy" value="id"> Seller Id<br>
                                       <input type="checkbox" name="sortBy" value="createdAt"> Registration time<br>
                                       <div class="mt-4 mb-2 form-label text-info text-sm-center">Filter By</div>
                                       <input type="radio" name="status" value="NEED_APPROVAL"> Need_Approval<br>
                                       <input type="radio" name="status" value="APPROVED"> Approved<br>
                                       <input type="radio" name="status" value="REJECTED"> Rejected<br>
                                       <div class="">
                                              <input type="submit" value="Apply" class="mt-4 btn btn-info w-100">
                                       </div>
                                 </form>
                          </div>
                    </div>
                    <div class="col-sm-8">
                          <div class="list-title font-weight-light">Sellers</div>
                          
                          <div>
                          <form action="approve">
                                 <c:forEach var="seller" items="${sellerList}">
                                     <a href="sellerprofile/${seller.id}">
                                       <div class="seller-list-item my-3" >
                                              <div class="row">
                                              <div class="col-sm-1">
                                                           <c:if test="${seller.status.equals('NEED_APPROVAL')}">
											<input type="checkbox" name="check" value="${seller.id}">
										</c:if>
										<c:if test="${!seller.status.equals('NEED_APPROVAL')}">
													<input type="checkbox" name="check" value="" style="visibility: hidden" disabled>
										</c:if> 
                                                            </div>
                                                    <div class="col-sm-4 d-flex flex-column">
                                                           <span><c:out value="${seller.ownername}" /></span>
                                                           <span class="text-secondary small-label">Registered 5 days ago</span>
                                                    </div>
                                                    <div class="col-sm-2 text-sm-center">
                                                    <span class="text-secondary"><c:out value="${seller.id}" /></span>
                                                     </div>
                                                    <div class="col-sm-4">
                                                           <span class="float-right pr-4"><c:out value="${seller.status}" /></span>
                                                    </div>
                                              </div>
                                       </div>
                                       </a>
                                 </c:forEach>
                                 <input type="submit" value="Approve All">
                                 </form>
                          </div>
                    </div>
             </div> 
       </div>
</body>
</html>
