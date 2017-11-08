<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
		<a href="backTo.htm?action=adminMenu"><button class="btn btn-primary btn-sm">Back To Menu</button></a>
		  <h1>Please add categories in below</h1>
		</div>
		<form:form action="addcategory.htm" commandName="category" method="post" class="form-inline">
			Category: <form:input type = "text"  class = "form-control" path="title" size="30" /><form:errors path="title"/>
			<input type="submit" value="Create Category" class="btn btn-primary"/>
	
	
			<%-- <table>
			<thead>Categories you have already</thead>
			<tr><td>Category</td><td>Operation</td></tr>
			<c:foreach var="category" items="${catrgoryList}">
				<tr>
					<td>${category.title}</td>
					<!--<td><a href="delete.htm?delTitle=${category.title}">Delete</a>-->
				</tr>
			</c:foreach>
			</table> --%>
		</form:form>
	</div>
</body>
</html>