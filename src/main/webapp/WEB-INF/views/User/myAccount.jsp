<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
body{
	margin-left:10%;
	margin-right:10%;
	margin-top:30px;
}
</style>
<script>
	$(this).click(function(){
	    $(this).parent().next().show();
	});
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
		
		/* alert(query); */
		xmlHttp.onreadystatechange = function stateChanged()
        {
            if (xmlHttp.readyState == 4)
            {
            	var status = document.getElementById(rowID);
            	/* alert(status.innerHTML); */
                status.innerHTML = "Canceled";
            }
        };
        xmlHttp.open("GET", "myAccount.htm?"+query, true);
        /* xmlHttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded"); */
        xmlHttp.send(null);
        return false; 
		/* var row = document.getElementById(rowID);
        row.parentNode.removeChild(row); */
	}
	$(document).ready(function(){
		$(".edit").click(function(){
			$(".info").hide();
			$(".editInfo").show();
		});
		$("#btnSave").click(function(){
			var name = $("#myForm").find('input[name="editName"]').val();
			
			var email = $("#myForm").find('input[name="editEmail"]').val();
			var password = $("#myForm").find('input[name="editPassword"]').val();
			var address = $("#myForm").find('input[name="editAddress"]').val();
			var phoneNumber = $("#myForm").find('input[name="editPhoneNumber"]').val();
			$.post("myAccount.htm",{
				"name": name,
				"password": password,
				"email": email,
				"address": address,
				"phoneNumber": phoneNumber
			},function(data){
				var json = JSON.parse(data);
				var pp = $(".info").find("p");
				pp[0].innerHTML = "Name: "+ name;
				pp[1].innerHTML = "Email: " + email;
				pp[2].innerHTML = "Password: " + password;
				pp[3].innerHTML = "Address " + address;
				pp[4].innerHTML = "Phone Number: " + phoneNumber;
				if(json.flag == 1) {
					$(".info").show();
					$(".editInfo").hide();
				}
			});
		}); 
	});
</script>
</head>
<body>
<div class="container">
	<c:choose>
        <c:when test="${!empty sessionScope.userName}">
             <jsp:include page="../../../menu2.jsp"/>
        </c:when>
        <c:otherwise>
             <jsp:include page="../../../menu1.jsp"/>
        </c:otherwise>
    </c:choose>
	<div class="row">
		<ul class="nav nav-tabs">
		  <li role="presentation" class="active"><a data-toggle="tab" href="#detail">My Details</a></li>
		  <!-- <li role="presentation"><a data-toggle="tab" href="#payment">Payment</a></li> -->
		  <li role="presentation"><a data-toggle="tab" href="#orderHistory">OrderHistory</a></li>
		  <li role="presentation"><a data-toggle="tab" href="#Message">Message</a></li>
		</ul>
		<div class="tab-content">
			<div id="detail" class="tab-pane fade in active">
				<div class="row">
					<div class="col-md-10">
						<h3 class="headings">My Details</h3>
					</div>
					<div class="col-md-2 edit">
						<span  class="glyphicon glyphicon-pencil icon" aria-hidden="true">Edit</span>
					</div>
				</div>
				<hr>
				<div class="info">
					<p>Name: ${user.name} </p>
					<p>Email: ${user.email.emailId}</p>
					<p>Password: ${user.password}</p>
					<p>Address: ${user.address}</p>
					<p>Phone Number: ${user.phoneNumber}</p>
					<%-- <p>Card Number: ${user.payment.cardNumber}</p>
					<p>Expired Date: ${user.payment.expiredDate}</p>
					<p>Name On Card: ${user.payment.nameOnCard}</p> --%>
				</div>
				<div class="editInfo" style="display: none">
					<form action="myAccount.htm" method="POST" id="myForm">
						Name: <input type="text" class="form-control" name="editName"/>
						Email: <input type="text" class="form-control" name="editEmail"/>
						Password: <input type="text" class="form-control" name="editPassword"/>
						Address: <input type="text" class="form-control" name="editAddress"/>
						Phone Number: <input type="text" class="form-control" name="editPhoneNumber"/>
						<input class = "save" id="btnSave"  type="button" class="btn btn-primary btn-sm" value="save"> 
					</form>
				</div>
				<%-- <p>Name: <%=session.getAttribute("user")%></p> --%>
			</div>
			<div id="payment" class="tab-pane fade">
				<div class="row">
					<div class="col-md-10">
						<h3 class="headings">Payment</h3>
					</div>
					<div class="col-md-2">
						<span  class="glyphicon glyphicon-pencil icon" aria-hidden="true">Edit</span>
					</div>
				</div>
				<hr>
				<p>Card Number: ${user.payment.cardNumber}</p>
				<p>Expired Date: ${user.payment.expiredDate}</p>
				<p>Name On Card: ${user.payment.nameOnCard}</p>
			</div>
			<div id="orderHistory" class="tab-pane fade">
				<div class="row">
					<div class="col-md-10">
						<h3 class="headings">Order History</h3>
					</div>
					<div class="col-md-2">
						<span  class="glyphicon glyphicon-pencil icon" aria-hidden="true">Edit</span>
					</div>
				</div>
				<hr>
				<table class="table table-striped">
					<tr>
						<td>Order Number</td>
						<td>Created Date</td>
						<td>Status</td>
						<td>Operation</td>
					</tr>
				   <c:forEach var="order" items="${orderList}" varStatus="vs">
				   		<tr>
					   		<td>${order.id}</td>
					   		<td>${order.createDate}</td>
					   		<td id="${vs.index}">${order.status}</td>
					   		<td><a onclick = "cancelOrder(${order.id}, ${vs.index})">Cancel Order</a><br><a href="myAccount.htm?action=viewDetail&orderId=${order.id}">View Details</a></td>
				   		</tr>
				   </c:forEach>
				</table>
			</div>
			<div id="Message" class="tab-pane fade">
				<div class="row">
					<div class="col-md-10">
						<h3 class="headings">Message</h3>
					</div>
					<div class="col-md-2">
						<span  class="glyphicon glyphicon-pencil icon" aria-hidden="true">Edit</span>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked">
						  <li role="presentation" class="active"><a data-toggle="pill" href="#write">Write Message</a></li>
						  <li role="presentation"><a data-toggle="pill" href="#sent">Sent Message</a></li>
						  <li role="presentation"><a data-toggle="pill" href="#received">Received Message</a></li>
						</ul>
					</div>
					<div class="col-md-9 tab-content">
						<div id="write" class="tab-pane fade in active">
							<%-- <form:form method="POST" action="signup.htm" commandName="user">
								Message Title <form:input path="name" type="text" class="form-control" placeholder="Text input"/>
								Content <form:textarea path="password" class="form-control" rows="3" />
								<input class="btn btn-default" type="submit" value="Submit">
							</form:form> --%>
							<form method="POST" action="message.htm">
								Message Title <input name="title" type="text" class="form-control" placeholder="Input your title here"/>
								Content <textarea name="content" class="form-control" rows="5"></textarea>
								<input class="btn btn-default" type="submit" value="Submit">
							</form>
						</div>
						<div id="sent" class="tab-pane fade">
							<table class="table table-striped">
								<thead>
									<th>Date</th>
									<th>Title</th>
									<th>Show content</th>
								</thead>
								<c:forEach var="message" items="${semessageList}">
									<tr>
										<td>${message.date}</td>
										<td>${message.title}</td>
										<td>${message.content}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div id="received" class="tab-pane fade">
						<table class="table table-striped">
								<thead>
									<th>Date</th>
									<th>Title</th>
									<th>Show content</th>
								</thead>
							<c:forEach var="message" items="${remessageList}">
								<tr>
									<td>${message.date}</td>
									<td>${message.title}</td>
									<td>${message.content}</td>
								</tr>
							</c:forEach>
						</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
</html>