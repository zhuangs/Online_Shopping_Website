<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Manage User Account</title>
<script>
	var xmlHttp;
	xmlHttp = GetXmlHttpObject();
	function GetXmlHttpObject()
	{
	    var xmlHttp = null;
	    try
	    {
	        // Firefox, Opera 8.0+, Safari
	        xmlHttp = new XMLHttpRequest();
	    } catch (e)
	    {
	        // Internet Explorer
	        try
	        {
	            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
	        } catch (e)
	        {
	            xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	        }
	    }
	    return xmlHttp;
	}
	function cancelOrder(orderId, rowID){
		if (xmlHttp == null)
	    {
	        alert("Your browser does not support AJAX!");
	        return;
	    }
		var query = "action=cancel&order=" + orderId;
		
		/* alert(orderId); */
		xmlHttp.onreadystatechange = function stateChanged()
	    {
	        if (xmlHttp.readyState == 4 && xmlHttp.status==200)
	        {
	        	var status = document.getElementById(rowID);
	        	/* alert(status.innerHTML); */
	            status.innerHTML = "Canceled";
	        }
	    };
	    xmlHttp.open("GET", "manageUserAccount.htm?"+query, true);
	    /* xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); */
	    xmlHttp.send(null);
	    return false; 
		/* var row = document.getElementById(rowID);
	    row.parentNode.removeChild(row); */
	}
	function shipOrder(orderId, rowID){
		if (xmlHttp == null)
	    {
	        alert("Your browser does not support AJAX!");
	        return;
	    }
		var query = "action=ship&order=" + orderId;
		
		/* alert(orderId); */
		xmlHttp.onreadystatechange = function stateChanged()
	    {
	        if (xmlHttp.readyState == 4 && xmlHttp.status==200)
	        {
	        	var status = document.getElementById(rowID);
	        	alert(status); 
	            status.innerHTML = "Shipped";
	        }
	    };
	    xmlHttp.open("GET", "manageUserAccount.htm?"+query, true);
	    /* xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); */
	    xmlHttp.send(null);
	    return false; 
		/* var row = document.getElementById(rowID);
	    row.parentNode.removeChild(row); */
	}
</script>
</head>
<body>
	<div class="container">
		<a href="backTo.htm?action=adminMenu"><button class="btn btn-primary btn-sm">Back To Menu</button></a>
		<div class="page-header">
		  <h1>User Account Information</h1>
		</div>
		<form class="form-inline" action="search.htm" method="POST">
			
				<input type="text" class="form-control" name="searchKeyWord" placeholder="">
				<input type="submit" class="btn btn-info" Value="Search">
				<label>Search By:</label>
	            <input type="radio" class="form-control" name="searchkey" value="name" checked="checked">Name
	            <input type="radio" class="form-control"name="searchkey" value="email"> Email <br><br>
	            <input type="hidden" name="action" value="searchuser"/>
		</form>
		<table class="table table-striped">
		<tr>
			<td>User Name</td>
			<td>Email</td>
			<td>Password</td>
			<td>Phone Number</td>
			<td>Address</td>
			<td>Payment Information</td>
			<td>Order History</td>
			<td>Operation</td>
		</tr>
		<c:forEach var = "user" items = "${userList}" varStatus="vs">
			<tr>
				<td>${user.name}</td>
				<td>${user.email.emailId}</td>
				<td>${user.password}</td>
				<td>${user.phoneNumber}</td>
				<td>${user.address}</td>
				<td>
					<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}">View Payment</button>
					<div class="modal fade" id="myModal${vs.index}" role="dialog">
					    <div class="modal-dialog">
					    	<div class="modal-content">
						        <div class="modal-header">
						          <button type="button" class="close" data-dismiss="modal">&times;</button>
						          <h4 class="modal-title">${user.name} Payment Information</h4>
						        </div>
						        <div class="modal-body">
						        	Card Number: ${user.payment.cardNumber}<br>
						        	Expired Date: ${user.payment.expiredDate}<br>
						        	Name On Card: ${user.payment.nameOnCard}<br>
						        </div>
						    </div>
						</div>
					</div>	    
				</td>
				<td>
					<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal${vs.index}a" id="viewDetailButton${vs.index}a">Show Order History</button>
					<div class="modal fade" id="myModal${vs.index}a" role="dialog">
					    <div class="modal-dialog">
					    	<div class="modal-content">
						        <div class="modal-header">
						          <button type="button" class="close" data-dismiss="modal">&times;</button>
						          <h4 class="modal-title">${user.name} Order History</h4>
						        </div>
						        <div class="modal-body">
						        	<table class="table table-striped">
						        		<thead>
						        			<tr>
												<th>Order Number</th>
												<th>Created Date</th>
												<th>Status</th>
												<th>Operation</th>
											</tr>
										</thead>
										<c:forEach var="order" items="${user.orders}" varStatus="vs">
									   		<tr id="${vs.index}">
										   		<td>${order.id}</td>
										   		<td>${order.createDate}</td>
										   		<td>${order.status}</td>
										   		<td><a href="manageUserAccount.htm?action=viewDetails&orderId=${order.id}">View Details</a></td>
										   		<td><%-- <a onclick = "shipOrder(${order.id}, ${vs.index})">Ship the order</a><br --%><a onclick = "cancelOrder(${order.id}, ${vs.index})">Cancel the order</a></td>
									   		</tr>
									   </c:forEach>
						        	</table>
						        </div>
						    </div>
						</div>
					</div>	 
				</td>
				<td><a href="manageUserAccount.htm?action=delete&username=${user.name}"><button type="button" class="btn btn-default btn-xs">Delete</button></a></td>
			</tr>
		</c:forEach>
		</table>
	</div>
</body>
</html>