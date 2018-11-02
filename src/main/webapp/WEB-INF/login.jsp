<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>

<link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
	<div id = "wrapper">
	<h1>Login</h1>
	<div class = "body">
		<form action="/users/login" method="POST" >
		
		<p><input type="text"  name="email" placeholder="Email" /></p>
		<p><input type="password" name="password" placeholder="Password" /></p>
		
		<c:if test="${loginError != null}" >
			<p class="error">${loginError}</p>				
		</c:if>
		
		<input type = "submit" value = "Login"/>
	</form>
	<a href = "/users/registration">To Registration</a>
	</div>
	</div>
</body>
</html>