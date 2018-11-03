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
             .card{
             width:600px;
             }
              .submit-btn {
			    background: none;
			    border: none;
			    color: #ffffff;
			    cursor: pointer;
			}
</style>
</head>
<body>
<div class="bg-info d-flex justify-content-between p-3 text-light">
    	<span class="font-weight-light title">YourMart</span>
                  
        	
        <div class="d-flex">
        <span class="mx-3">
        	<form action="/yourmart-admin/products">
            	<input  class="submit-btn" type="submit" value="Product Page">
            </form>
        </span>
        <span class="mx-3">
             		<form action="/yourmart-admin/home">
             			<input class="submit-btn" type="submit" value="Seller Page">
             		</form>
             	</span>
        <span class="font-weight-light welcome">Welcome <c:out value="${user}" /></span>
	</div>
	</div>
	<div class="text-sm-center">
	<h3>${product.productname}</h3>
	</div>
	
	<div class="card">
  <img class="card-img-top" src="${product.primaryimage}" alt="Card image cap">
  <div class="card-body"> 
   
   </div>
</div>
  
  <div class="d-flex justify-content-center">
		
		<c:if test="${product.status.equals('NEW') }">
		<form action="changeStatusProduct" method="post">
		<input type="hidden" name="Status" value="APPROVED">
		<input type="hidden" name="id" value="${product.id+''}">
		<input class="btn m-3 btn-success" type="submit" value="Approve ${product.productname }">

		</form>
		<form action="changeStatusProduct"  method="post">
		<input type="hidden" name="Status" value="REJECTED">
		<input type="hidden" name="id" value="${product.id }">
		<input class="btn m-3 btn-danger" type="submit" value="Reject ${product.productname }">
	
		</form>
		</c:if>
		<c:if test="${product.status.equals('APPROVED') }">
		<form action="changeStatusProduct"  method="post">
		<input type="hidden" name="Status" value="REJECTED">
		<input type="hidden" name="id" value="${product.id }">
		<input type="hidden" name="comment" id="commentid" value="">
		<input class="btn m-3 btn-danger" type="submit" value="Reject ${product.productname }" onclick="myFunction()">
		</form>
		</c:if>
		<c:if test="${product.status.equals('REJECTED') }">
		<form action="changeStatusProduct"  method="post">
		<input type="hidden" name="Status" value="APPROVED">
		<input type="hidden" name="id" value="${product.id }">
		<input class="btn m-3 btn-success" type="submit" value="Approve ${product.productname }">
		</form>
		</c:if>
	</div>
  
  
  
 <script>
 

    
	 function myFunction() {
		    var txt;
		    var comment = prompt("Please enter comments for your rejection:", "Costly");
		    if (comment == null || comment == "") {
		        txt = "User cancelled the prompt.";
		    } else {
		        txt = comment
		    }
		    document.getElementById("commentid").value = txt;
		}
 
 </script>
	
	
	
	
	
</body>
</html>