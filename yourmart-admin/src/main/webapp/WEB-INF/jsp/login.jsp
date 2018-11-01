<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Welcome ${user}</h1>
 <h1>Sellers List </h1>
 <table>
 <tr>
	<th>Ownername</th>
	<th>Status</th>
	<th>Email</th>
	<th>Company Name</th>
	<th>Telephone</th>
	<th>Gst No</th>
	<th>Address</th>
	</tr>
	 <c:forEach var="seller" items="${sellerList}">
	
	 <tr>
	 	<td><c:out value="${seller.ownername}"></c:out></td>
		<td><c:out value="${seller.status}"></c:out></td>
		<td><c:out value="${seller.email}"></c:out></td>
		<td><c:out value="${seller.companyname}"></c:out></td>
	 	<td> <c:out value="${seller.telephone}"></c:out></td>
		<td><c:out value="${seller.gstnumber}"></c:out></td>
		<td><c:out value="${seller.address}"></c:out></td>
	 	
		</tr>
	 </c:forEach>
</table>
</body>
</html>