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
        <link rel="stylesheet" type="text/css" href="login.css">
        <script src="login.js"></script>
        <style>
        	#signUp{
        		float:right;
        	}
        </style>
	</head>
	<body>
		<div class = "container-fluid">
			<div class = "row">
				<div class = "col-md-8"></div>
				<div class = "col-md-4" id="signUp">
					<a href="login.htm?action=signup"><button type="button" class="btn btn-primary">Sign Up</button></a>
				</div>
			</div>
			<div class = "row">
				<div class = "col-md-4"></div>
				<div class = "col-md-4" id="title">
					<h1>Online Shopping</h1>
				</div>
				<div class = "col-md-4"></div>
			</div>
			<form:form name="loginform" onsubmit="return validationForm();" action="login.htm" method="post">
			<div class = "row">
				<div class = "col-md-4"></div>
				<div class = "col-md-4" id="form">
					
						<table class = "table table-bordered">
							<tr><input type="text" class="form-control" name="userName" id="userName" placeholder="User Name"></tr>
							<tr><input type="password" class="form-control" name="password" id="exampleInputPassword1" placeholder="Password"></tr>
						</table>
						<div class = "row">
							<div class = "col-md-4"></div>
							<div class = "col-md-4" id="login">
								<button type="submit" class="btn btn-primary">Log in</button>
							</div>
							<div class = "col-md-4"></div>
						</div>
					
				</div>
				<div class = "col-md-4"></div>
			</div>
			
		</form:form>
	</body>
</html>