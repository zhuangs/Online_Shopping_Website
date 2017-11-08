<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Login page</title>
		<!-- Latest compiled and minified CSS -->
	    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
        <link href="css/bootstrap-form-helpers.min.css" rel="stylesheet">
		<script src="js/bootstrap-formhelpers.min.js"></script>
		<script src="js/bootstrap-formhelpers-countries.js"></script>
		<script src="js/html5shiv.js"></script>
      	<script src="js/respond.min.js"></script>
        <link rel="stylesheet" type="text/css" href="signup.css">
	</head>
	<body>
		<div class = "container-fluid">
			<div class = "row">
				<div class = "col-md-8"></div>
				<div class = "col-md-4" id="login">
					<a href="signup.htm?action=login"><button type="button" class="btn btn-primary">Log in</button></a>
				</div>
			</div>
			<div class = "row">
				<div class = "col-md-4"></div>
				<div class = "col-md-4" id = "title">
					<h1>Online Shopping</h1>
					<!--<p>Millions of member profiles.Thousands more joining each day.</p>-->
				</div>
				<div class = "col-md-4"></div>
			</div>
			<div class = "row">
				<div class = "col-md-4"></div>
				<div class = "col-md-4" id="form">
					<form:form action = "signup.htm" commandName="user" method="post" class="form-horizontal">
						<div class="form-group">
						    <label for="inputEmail3" class="col-sm-4 control-label">Email</label>
						    <div class="col-sm-8">
						      <form:input path="email.emailId" type="email" class="form-control" id="inputEmail3" placeholder="Email" /><form:errors path="email.emailId" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputUserName" class="col-sm-4 control-label">User Name</label>
						    <div class="col-sm-8">
						      <form:input path = "name" type="text" class="form-control" id="inputUserName" placeholder="User Name"/><form:errors path="name" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputPassword" class="col-sm-4 control-label">Password</label>
						    <div class="col-sm-8">
						      <form:input path="password" type="password" class="form-control" id="inputPassword" placeholder="Password"/><form:errors path="password" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputRePassword" class="col-sm-4 control-label">Re-enter</label>
						    <div class="col-sm-8">
						      <input type="password" class="form-control" id="inputRePassword" placeholder="Re-enter your password">
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputBirthday" class="col-sm-4 control-label">Address</label>
						    <div class="col-sm-8">
						      <form:input type="text" path="address" class="form-control" id="inputAddress" placeholder="Address" /><form:errors path="address" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputBirthday" class="col-sm-4 control-label">Phone Number</label>
						    <div class="col-sm-8">
						      <form:input type="text" path="phoneNumber" class="form-control" id="inputPhoneNumber" placeholder="PhoneNumber"/><form:errors path="phoneNumber" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputBirthday" class="col-sm-4 control-label">Payment Card Number</label>
						    <div class="col-sm-8">
						      <form:input type="text" path="payment.cardNumber" class="form-control" placeholder="PaymentCardNumber"/><form:errors path="payment.cardNumber" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputBirthday" class="col-sm-4 control-label">Card Expire Date</label>
						    <div class="col-sm-8">
						      <form:input type="text" path="payment.expiredDate" class="form-control" placeholder="Card Expire Date"/><form:errors path="payment.expiredDate" />
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputBirthday" class="col-sm-4 control-label">Name On Card</label>
						    <div class="col-sm-8">
						      <form:input type="text" path="payment.nameOnCard" class="form-control" placeholder="Name On Card"/><form:errors path="payment.nameOnCard" />
						    </div>
						</div>
						<!-- <div class="form-group">
						    <label for="inputCountry" class="col-sm-3 control-label">Country</label>
						    <div class="col-sm-9">
						      <select id = "inputCountry" class="input-medium bfh-countries" data-country="US"></select>
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputReState" class="col-sm-3 control-label">State</label>
						    <div class="col-sm-9">
						      <input type="text" class="form-control" id="inputState" placeholder="State">
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputCity" class="col-sm-3 control-label">City</label>
						    <div class="col-sm-9">
						      <input type="text" class="form-control" id="inputCity" placeholder="City">
						    </div>
						</div>
						<div class="form-group">
						    <label for="inputZipCode" class="col-sm-3 control-label">ZipCode</label>
						    <div class="col-sm-9">
						      <input type="number" class="form-control" id="inputZipCode" placeholder="ZipCode">
						    </div>
						</div> -->
						<div class = "row">
							<div class = "col-md-4"></div>
							<div class = "col-md-4" id="signup">
								<input type="submit" class="btn btn-primary">
							</div>
							<div class = "col-md-4"></div>
						</div>
					</form:form>
				</div>
				<div class = "col-md-4"></div>
			</div>
	</body>
</html>