<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
	#signout{
	float:right;
	}
</style>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%-- <link href="<c:url value="/resources/css/adminIndex.css" />" rel="stylesheet"> --%>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<a href="index.jsp"><button type="button" class="btn btn-primary" id="signout">Sign Out</button></a>
		<div class="page-header">
		  <h1>Welcome Back Admin! <small>Please select the operation from the menu</small></h1>
		</div>
		<div class="list-group">
		  <a href = "manageUserAccount.htm?action=enter" class="list-group-item ">Manage User Account</a>
		  <a href = "addcategory.htm" class="list-group-item">Add New Category</a>
		  <a href = "addItems.htm" class="list-group-item">Add New Items</a>
		  <a href = "manageItem.htm" class="list-group-item">Manage all the Item</a>
		  <a href = "adminManageMessage.htm" class="list-group-item">Manage Messages</a>
		</div>
	</div>
</body>
</html>