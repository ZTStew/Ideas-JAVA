<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit</title>

<link rel="stylesheet" type="text/css" href="/css/styles.css">
<link rel="stylesheet" type="text/css" href="/css/editStyle.css">
</head>
<body>
	<div id = "wrapper">
	<div class = "tableBar">
		<h1>Edit: ${ idea.getIdea() }</h1>
		<div>
			<h3><a href="/ideas/${ idea.getId() }">Back</a> | <a href="/ideas">Back To DashBoard</a> | <a href="/users/logout">Logout</a></h3>
		</div>
	</div>
	<hr/>
	
	<h2>Creator: ${ idea.getUser().lastName }</h2>
	<h2>Number Of Likes: ${ idea.getUsers().size() }</h2>
	
	<hr/>
	
	
	<table>
		<tr>
			<form:form method = "POST" action = "/ideas/edit/${ idea.id }" modelAttribute = "idea">
				<td valign="middle" class = "word">Idea:</td>
				<td><form:input path="idea" id = "ideaEdited"></form:input></td>
				<c:if test = "errors">
					<td><form:errors path="idea" class = "error"></form:errors></td>
				</c:if>
				<td class = "minimize">
					<input type = "submit" value = "Edit Idea">
				</td>
			</form:form>
			<td class = "minimize">
				<form action = "/ideas/delete/${ idea.id }" method = "POST">
					<input type = "hidden" name = "_method" value = "delete">
					<input type = "submit" value = "Delete">
				</form>
			</td>
		</tr>
	</table>
	</div>
</body>
</html>