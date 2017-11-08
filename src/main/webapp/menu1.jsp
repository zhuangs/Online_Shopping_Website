<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="<c:url value="/resources/css/menu.css" />" rel="stylesheet">
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="index.jsp">Shopping Site</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href = "myAccount.htm?action=myAccount">MY ACCOUNT</a></li>
	  <li><a href = "signup.htm">REGISTER</a></li>
	  <li><a href = "login.htm">SIGN IN</a></li>
	  <li style="float:right;"><a href = "addTo.htm?action=shoppingbag">SHOPPING BAG</a></li>
    </ul>
  </div>
</nav>