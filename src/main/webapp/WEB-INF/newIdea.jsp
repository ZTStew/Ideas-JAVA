<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>New Ideas</title>
<link rel="stylesheet" type="text/css" href="/css/newStyle.css">
<link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>
	<div id = "wrapper">
	<div class = "tableBar">
		<h1>Create A New Idea</h1>
		<div>
			<h2><a href="/ideas">Back To DashBoard</a> | <a href="/users/logout">Logout</a></h2>
		</div>
	</div>
	<hr/>
	<form:form method = "POST" action = "/ideas/new" modelAttribute = "creIdea">
	<table>
		<tr>
			<td>
				<form:label path="idea" class = "word">Idea: </form:label>
			</td>
			<td>
				<form:input path="idea" id="inputIdea"></form:input>
			</td>
				
			<td class = "minimize">
				<input type = "submit" value = "Create Idea">
			</td>
		</tr>
	</table>
	<p><form:errors path="idea" class = "error"></form:errors></p>
	</form:form>
		
		
	</div>
</body>
</html>