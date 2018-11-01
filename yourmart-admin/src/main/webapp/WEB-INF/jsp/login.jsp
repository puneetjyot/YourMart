

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>
       <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

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
             <span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
       </div>
       <div class="container py-4">
             <div class="row">
                    <div class="col-sm-3 px-5 filters">
                          <div class="">
                                 <form action="search">
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
                                 <c:forEach var="seller" items="${sellerList}">
                                       <div class="seller-list-item my-3">
                                              <div class="row">
                                                    <div class="col-sm-4 d-flex flex-column">
                                                           <span><c:out value="${seller.ownername}" /></span>
                                                           <span class="text-secondary small-label">Registered 5 days ago</span>
                                                    </div>
                                                    <div class="col-sm-4 text-sm-center">
                                                    <span class="text-secondary"><c:out value="${seller.id}" /></span>
                                                     </div>
                                                    <div class="col-sm-4">
                                                           <span class="float-right pr-4"><c:out value="${seller.status}" /></span>
                                                    </div>
                                              </div>
                                       </div>
                                 </c:forEach>
                          </div>
                    </div>
             </div> 
       </div>
</body>
</html>
