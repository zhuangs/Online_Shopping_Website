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
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="page-header">
		  <h1>Your search result shows as below</h1>
		</div>
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
										<c:forEach var="order" items="${user.orders}">
									   		<tr>
										   		<td>${order.id}</td>
										   		<td>${order.createDate}</td>
										   		<td>${order.status}</td>
										   		<td><a href="manageUserAccount.htm?action=viewDetails&orderId=${order.id}">View Details</a></td>
										   		<td><a href="manageUserAccount.htm?action=ship&orderId=${order.id}">Ship the order</a><br><a href="manageUserAccount.htm?action=cancel&orderId=${order.id}">Cancel the order</a></td>
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
		</table>
	</div>
</body>
</html>