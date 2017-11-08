<%@page import="com.myapp.finalproject.dao.CategoryDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	CategoryDAO categorydao = new CategoryDAO();
	java.util.List categoryList = categorydao.list();
	pageContext.setAttribute("categories", categoryList);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Add New Item</title>
</head>
<body>
<div class="container">
	<a href="backTo.htm?action=adminMenu"><button class="btn btn-primary btn-sm">Back To Menu</button></a>
	<div class="page-header">
		  <h1>Please add new item in below<h1>
	</div>
	<form:form action="addItems.htm" commandName="item" method="post" enctype="multipart/form-data">
		
		<table >

                <tr>
                    <td>Category:</td>
                    <td>
                        <form:select path="category_name">
                            <c:forEach var="categ" items="${categories}">
                                <form:option value="${categ.title}"/>
                            </c:forEach>
                        </form:select>
                    </td>
                </tr>
                <tr>
                    <td>Item Title:</td>
                    <td><form:input path="title" size="30" /> <font color="red"><form:errors path="title"/></font></td>
                </tr>
                <tr>
                    <td>Price:</td>
                    <td><form:input path="price" size="30" /> <font color="red"><form:errors path="price"/></font></td>
                </tr>
                <tr>
                    <td>Color:</td>
                    <td><form:input path="color" size="30" /> <font color="red"><form:errors path="color"/></font></td>
                </tr>
                <tr>
                    <td>Size:</td>
                    <td><form:input path="size" size="30" /> <font color="red"><form:errors path="size"/></font></td>
                </tr>
                <tr>
                    <td>Quantity:</td>
                    <td><form:input path="quantity" size="30" /> <font color="red"><form:errors path="quantity"/></font></td>
                </tr>
                <tr>
                    <td>Details:</td>
                    <td><form:input path="details" size="30" /> <font color="red"><form:errors path="details"/></font></td>
                </tr>
                <tr>
                    <td>Select Photo: </td>
                    <td><form:input path="photo" type = "file" size="30" /> <font color="red"><form:errors path="photo"/></font></td>
                </tr>
                <tr>
				    <td colspan="2"><input type="submit" value="Create New Item" /></td>
				</tr>
          </table>
          
	 </form:form>
</div>
</body>
</html>