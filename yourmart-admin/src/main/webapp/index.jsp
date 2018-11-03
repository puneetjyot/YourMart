<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
       pageEncoding="ISO-8859-1"%>
<%@ taglib uri='http://java.sun.com/jsp/jstl/core' prefix='c'%>
<html>
<head>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
 <script src="https://www.google.com/recaptcha/api.js" async defer></script> 
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<style>

body {
       background-image: url("https://assets.wordpress.envato-static.com/uploads/2016/03/Modern-Design-Ideas-Trends-for-eCommerce-Websites.jpg");
}

.form-control {
       background: none;
       border: 1px solid #ffffff;
       color: #ffffff;
}

</style>
</head>
<body class="h-100 w-100 d-flex align-items-center">
       <div class="d-flex flex-column w-100">
             <div class="d-flex justify-content-center w-100">
<%
String isValid=""+session.getAttribute("isValid");
if(!isValid.equalsIgnoreCase("null") && isValid.equalsIgnoreCase("false")){
	session.setAttribute("isValid","true");
	out.print("<font color=red>Username or password is Incorrect</font><br/>");
}
%> 
 
             
                    <form action="authenticate" method="post" class="form-inline" id="form">
                    <input type="text" placeholder="Username" name="username" class="form-control mx-2">
                    <input type="password" placeholder="Password" name="password" class="form-control mx-2">
                    <div class="g-recaptcha" data-sitekey="6Lf-KngUAAAAAOsJAmh9pT7R3o3cz4z10fQTARzu"></div> 
                    <input type="submit" value="login" class="form-control mx-2">
              </form>
             </div>
            
       </div>
       

<script>
$('#form').on('submit', function(e) {
	  if(grecaptcha.getResponse() == "") {
	    e.preventDefault();
	    return false
	  } else {
	    return true
	  }
	});
</script> 
 
       
</body>
</html>
