<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
		<a href="index.jsp"><button class="btn btn-primary btn-sm">Back To Home Page</button></a>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>Title</th>
					<th>Price</th>
					<th>Details</th>
				</tr>
			</thead>
			<c:forEach var="item" items="${items}">
				<tr>
					<td><img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="80" height="80"><br>${item.title}</td>
					<td>${item.price}</td>
					<td>Size: ${item.size}<br>Color: ${item.color}</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>