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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<title>Insert title here</title>
</head>
<body>
	<div class="container">
	<div class="page-header">
	<a href="backTo.htm?action=adminMenu"><button class="btn btn-primary btn-sm">Back To Menu</button></a>
		  <h1>Please manage your message here</h1>
	</div>
	<div class="row">
					<div class="col-md-3">
						<ul class="nav nav-pills nav-stacked">
						  <li role="presentation"><a data-toggle="pill" href="#received">Received Message</a></li>
						  <li role="presentation"><a data-toggle="pill" href="#sent">Sent Message</a></li>
						  
						</ul>
					</div>
					<div class="col-md-9 tab-content">
						<div id="received" class="tab-pane fade in active">
							<table class="table table-striped">
								<thead>
								<tr>
									<th>Date</th>
									<th>Title</th>
									<th>Content</th>
									<th>Sender</th>
									<th>Reply</th>
								</tr>
								</thead>
								<c:forEach var="message" items="${messageList}" varStatus="vs">
									<tr>
										<td>${message.date}</td>
										<td>${message.title}</td>
										<td>${message.content}</td>
										<td>${message.sender}</td>
										<td>
										<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}">Reply</button>
											<div class="modal fade" id="myModal${vs.index}" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Reply Message</h4>
					        </div>
					        <div class="modal-body">
					          <form action="" method="POST">
					          	Send To: <input name="username" type="text" class="form-control" value="${message.user.name}" readonly/>
					          	Message Title <input name="title" type="text" class="form-control" placeholder="Input your title here"/>
								Content <textarea name="content" class="form-control" rows="5"></textarea>
								<input class="btn btn-default" type="submit" value="Submit">
					          </form>
					        </div>
					       <%--  <div class="modal-footer">
					          <a href="addTo.htm?action=cart&itemId=${item.id}" class="btn btn-default">Add to bag</a>
					          <a href="addTo.htm?action=wishList&itemId=${item.id}" class="btn btn-default" data-dismiss="modal">Add To WishList</a>
					        </div> --%>
					      </div>
										</td>
									</tr>
								</c:forEach>
							</table>
						</div>
						<div id="sent" class="tab-pane fade">
							<table class="table table-striped">
								<thead>
								<tr>
									<th>Date</th>
									<th>Title</th>
									<th>Show content</th>
									<th>Receiver</th>
								</tr>
								</thead>
								<c:forEach var="message" items="${sessionScope.adminMessage}">
									<tr>
										<td>${message.date}</td>
										<td>${message.title}</td>
										<td>${message.content}</td>
										<td>${message.receiver}</td>
									</tr>
								</c:forEach>
							</table>
						</div>
					</div>
			</div>
		</div>
</body>
</html>