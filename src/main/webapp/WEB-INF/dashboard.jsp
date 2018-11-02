<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>DashBoard</title>

<link rel="stylesheet" type="text/css" href="/css/styles.css">
<link rel="stylesheet" type="text/css" href="/css/dashboardStyle.css">
</head>
<body>
	<div id = "wrapper">
	<div class = "tableBar">
		<div class = "titleIdea">
			<h1>Moderately Okay Ideas | </h1>
			<img src="/img/javaLogo.png" alt="Java Logo">
		</div>
		<h1><a href="/users/logout">Logout</a></h1>
	</div>
	
	<!-- <div class = "tableBar">
		<h2>Ideas</h2>
		<div>
			<h3><a href = "#">Low Likes</a></h3>
			<h3><a href = "/ideas/asc">High Likes</a></h3>
		</div>
	</div> -->
	
	<table>
		<tr>
			<th class = "nameColumn"><h3>Name: </h3></th>
			<th class = "creatorColumn"><h3>Created By:</h3></th>
			<th class = "likesColumn"><h3>Likes: </h3></th>
			<th class = "actionsColumn"><h3>Actions: </h3></th>
		</tr>
		
		<c:forEach items="${ ideas }" var = "idea">
			<tr>
				<td><h3><a href="/ideas/${ idea.id }">${ idea.idea }</a></h3></td>
				<td><h4>${ idea.user.email }</h4></td>
				<td><h4>${ idea.getUsers().size() }</h4></td>
				<td>
					<c:if test = "${ !idea.getUsers().contains(userObj) }">
						<form action = "/ideas/like/${ idea.id }" method = "POST">
							<input type = "submit" value = "Like">
						</form>
					</c:if>
					<c:if test = "${ idea.getUsers().contains(userObj) }">
						<form action = "/ideas/unlike/${ idea.id }" method = "POST">
							<input type = "submit" value = "Un-Like">
						</form>
					</c:if>
				</td>
			</tr>
		</c:forEach>
		
	</table>
	
	<form action = "/ideas/new" method = "get">
		<input type = "submit" value = "Create An Idea">
	</form>
	</div>
	

</body>
</html>