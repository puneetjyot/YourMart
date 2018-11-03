<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
<style>
 .title {
                    font-size: 1.8rem;
             }
             
             .welcome {
                    font-size: 1.3rem;
             }
</style>
</head>
<body>
<div class="bg-info d-flex justify-content-between p-3 text-light">
             
             <span class="font-weight-light title">YourMart</span>
        <span class="font-weight-light welcome">Welcome <c:out value="${seller.ownername}" /></span>
       </div>

	
	<div class="row">
	<div class="col-sm-6 text-sm-center p-x-5 pt-5 pb-2">
	<div class="card">
  <div class="card-header">
<h1 class="text-info">Personal Details</h1>
  </div>
  <div class="card-body">
     <div class="row">
  <div class="col-sm-6 text-sm-center">
    <h4 class="card-title">Owner Name</h4>
    <h4 class="card-title mt-4">Mobile Number</h4>
    <h4 class="card-title mt-4">Email</h4>
    <h4 class="card-title mt-4">Status</h4>
    
    
    
    </div>
  <div class="col-sm-6 text-sm-center">
    <h4 class="card-title">${seller.ownername}</h4>
     <h4 class="card-title mt-4">${seller.telephone}</h4>
      <h4 class="card-title mt-4">${seller.email}</h4>
       <h4 class="card-title mt-4">${seller.status}</h4>
       
    </div>
    </div>
  </div>
</div>
	
	</div>
	<div class="col-sm-6 text-sm-center p-x-5 pt-5 pb-2">
	
	<div class="card">
  <div class="card-header">
   <h1 class="text-info"> Company Details</h1>
  </div>
  <div class="card-body">
  <div class="row">
  <div class="col-sm-6 text-sm-center">
    <h4 class="card-title">Company Name</h4>
    <h4 class="card-title mt-4">GST Number</h4>
    <h4 class="card-title mt-4">Address</h4>
    <h4 class="card-title mt-4">Email</h4>
    
    
    
    </div>
  <div class="col-sm-6 text-sm-center">
    <h4 class="card-title">${seller.companyname}</h4>
     <h4 class="card-title mt-4">${seller.gstnumber}</h4>
      <h4 class="card-title mt-4">${seller.address}</h4>
       <h4 class="card-title mt-4">${seller.email}</h4>
       
    </div>
    </div>
  </div>
</div>
	

	</div>
	</div>
	<div class="d-flex justify-content-center">
		
		<c:if test="${seller.status.equals('NEED_APPROVAL') }">
		<form action="changeStatus" method="post">
		<input type="hidden" name="Status" value="APPROVED">
		<input type="hidden" name="id" value="${seller.id+''}">
		<input class="btn m-3 btn-danger" type="submit" value="Approve ${seller.ownername }">

		</form>
		<form action="changeStatus"  method="post">
		<input type="hidden" name="Status" value="REJECTED">
		<input type="hidden" name="id" value="${seller.id }">
		<input class="btn m-3 btn-danger" type="submit" value="Reject ${seller.ownername }">
	
		</form>
		</c:if>
		<c:if test="${seller.status.equals('APPROVED') }">
		<form action="changeStatus"  method="post">
		<input type="hidden" name="Status" value="REJECTED">
		<input type="hidden" name="id" value="${seller.id }">
		<input class="btn m-3 btn-danger" type="submit" value="Reject ${seller.ownername }">
		</form>
		</c:if>
		<c:if test="${seller.status.equals('REJECTED') }">
		<form action="changeStatus"  method="post">
		<input type="hidden" name="Status" value="APPROVED">
		<input type="hidden" name="id" value="${seller.id }">
		<input class="btn m-3 btn-danger" type="submit" value="Approve ${seller.ownername }">
		</form>
		</c:if>
	</div>
	
	




</body>
</html>