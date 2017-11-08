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
	function deleteItem(itemId, rowID){
		if (xmlHttp == null)
	    {
	        alert("Your browser does not support AJAX!");
	        return;
	    }
		var query = "action=delete&itemId="+itemId;
		/* alert(2); */
		/*  alert(query);  */
		xmlHttp.onreadystatechange = function stateChanged()
	    {
	        if (xmlHttp.readyState == 4)
	        {
	        	var row = document.getElementById(rowID);
	            row.parentNode.removeChild(row);
	        }
	    };
	    xmlHttp.open("GET", "manageItem.htm?"+query, true);
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
		<div class="page-header">
		<a href="backTo.htm?action=adminMenu"><button class="btn btn-primary btn-sm">Back To Menu</button></a>
		  <h1>Your search result shows as below</h1>
		</div>
		<c:choose>
	        <c:when test="${!empty titleItem}">
	             <table class="table table-striped">
	             	<thead>
						<tr>
							<th>Item Number</th>
							<th>Title</th>
							<th>Category</th>
							<th>Price</th>
							<th>Size</th>
							<th>Color</th>
							<th>Quantity</th>
							<th>Details</th>
							<td>Operation</td>
						</tr>
					</thead>
					<tr>
						<td>${titleItem.id}</td>
						<td><img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="80" height="80"><br>${titleItem.title}</td>
						<td>${titleItem.category}</td>
						<td>${titleItem.price}</td>
						<td>${titleItem.size}</td>
						<td>${titleItem.color}</td>
						<td>${titleItem.quantity}</td>
						<td>${titleItem.details}</td>
						<td>
							<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal1" id="viewDetailButton1">Edit</button>
							<div class="modal fade" id="myModal1" role="dialog">
							    <div class="modal-dialog">
							    	<div class="modal-content">
								        <div class="modal-header">
								          <button type="button" class="close" data-dismiss="modal">&times;</button>
								          <h4 class="modal-title">${titleItem.title} Information</h4>
								        </div>
								        <div class="modal-body">
								        	<form method="POST" action="edit.htm">
									        	Title: <input type="text" class="form-control" name="title" value="${titleItem.title}"><br>
									        	Price: <input type="text" class="form-control" name="price" value="${titleItem.price}"><br>
									        	Size: <input type="text" class="form-control" name="size" value="${titleItem.size}"><br>
									        	Color: <input type="text" class="form-control" name="color" value="${titleItem.color}"><br>
									        	Quantity: <input type="text" class="form-control" name="quantity" value="${titleItem.quantity}"><br>
									        	Details: <input type="text" class="form-control" name="details" value="${titleItem.details}"><br>
									        	<input type="submit" class="btn btn-primary btn-sm" value="Save">
									        	<input type="hidden" name="action" value="editItem">
									        	<input type="hidden" name="itemId" value='${titleItem.id}'>
								        	</form>
								        </div>
								    </div>
								</div>
							</div>
							<button onclick = "deleteItem(${item.id}, ${vs.index})" type="button" class="btn btn-info btn-xs">Delete</button>
						</td>
					</tr>
	             </table>
	        </c:when>
	        <c:when test="${!empty item}">
	             <table class="table table-striped">
	             	<thead>
						<tr>
							<th>Item Number</th>
							<th>Title</th>
							<th>Category</th>
							<th>Price</th>
							<th>Size</th>
							<th>Color</th>
							<th>Quantity</th>
							<th>Details</th>
							<td>Operation</td>
						</tr>
					</thead>
					<tr>
						<td>${item.id}</td>
						<td><img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="80" height="80"><br>${item.title}</td>
						<td>${item.category}</td>
						<td>${item.price}</td>
						<td>${item.size}</td>
						<td>${item.color}</td>
						<td>${item.quantity}</td>
						<td>${item.details}</td>
						<td>
							<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}">Edit</button>
							<div class="modal fade" id="myModal${vs.index}" role="dialog">
							    <div class="modal-dialog">
							    	<div class="modal-content">
								        <div class="modal-header">
								          <button type="button" class="close" data-dismiss="modal">&times;</button>
								          <h4 class="modal-title">${item.title} Information</h4>
								        </div>
								        <div class="modal-body">
								        	<form method="POST" action="edit.htm">
									        	Title: <input type="text" class="form-control" name="title" value="${item.title}"><br>
									        	Price: <input type="text" class="form-control" name="price" value="${item.price}"><br>
									        	Size: <input type="text" class="form-control" name="size" value="${item.size}"><br>
									        	Color: <input type="text" class="form-control" name="color" value="${item.color}"><br>
									        	Quantity: <input type="text" class="form-control" name="quantity" value="${item.quantity}"><br>
									        	Details: <input type="text" class="form-control" name="details" value="${item.details}"><br>
									        	<input type="submit" class="btn btn-primary btn-sm" value="Save">
									        	<input type="hidden" name="action" value="editItem">
									        	<input type="hidden" name="itemId" value='${item.id}'>
								        	</form>
								        </div>
								    </div>
								</div>
							</div>
							<a href="manageItem.htm?action=delete&itemId=${item.id}"><button type="button" class="btn btn-info btn-xs">Delete</button></a>
						</td>
					</tr>
	             </table>
	        </c:when>
	        <c:when test="${!empty catelist}">
	             <table class="table table-striped">
	       
	             	<thead>
						<tr>
							<th>Item Number</th>
							<th>Title</th>
							<th>Category</th>
							<th>Price</th>
							<th>Size</th>
							<th>Color</th>
							<th>Quantity</th>
							<th>Details</th>
							<td>Operation</td>
						</tr>
					</thead>
					<c:forEach var="item" items="${catelist}" varStatus="vs">
						<tr>
							<td>${item.id}</td>
							<td><img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="80" height="80"><br>${item.title}</td>
							<td>${item.category}</td>
							<td>${item.price}</td>
							<td>${item.size}</td>
							<td>${item.color}</td>
							<td>${item.quantity}</td>
							<td>${item.details}</td>
							<td>
								<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}">Edit</button>
								<div class="modal fade" id="myModal${vs.index}" role="dialog">
								    <div class="modal-dialog">
								    	<div class="modal-content">
									        <div class="modal-header">
									          <button type="button" class="close" data-dismiss="modal">&times;</button>
									          <h4 class="modal-title">${item.title} Information</h4>
									        </div>
									        <div class="modal-body">
									        	<form method="POST" action="edit.htm">
										        	Title: <input type="text" class="form-control" name="title" value="${item.title}"><br>
										        	Price: <input type="text" class="form-control" name="price" value="${item.price}"><br>
										        	Size: <input type="text" class="form-control" name="size" value="${item.size}"><br>
										        	Color: <input type="text" class="form-control" name="color" value="${item.color}"><br>
										        	Quantity: <input type="text" class="form-control" name="quantity" value="${item.quantity}"><br>
										        	Details: <input type="text" class="form-control" name="details" value="${item.details}"><br>
										        	<input type="submit" class="btn btn-primary btn-sm" value="Save">
										        	<input type="hidden" name="action" value="editItem">
										        	<input type="hidden" name="itemId" value='${item.id}'>
									        	</form>
									        </div>
									    </div>
									</div>
								</div>
								<a href="manageItem.htm?action=delete&itemId=${item.id}"><button type="button" class="btn btn-info btn-xs">Delete</button></a>
							</td>
						</tr>
					</c:forEach>
	             </table>
	        </c:when>
	        <c:otherwise>
	             <table class="table table-striped">
	             	<thead>
						<tr>
							<th>Item Number</th>
							<th>Title</th>
							<th>Category</th>
							<th>Price</th>
							<th>Size</th>
							<th>Color</th>
							<th>Quantity</th>
							<th>Details</th>
							<td>Operation</td>
						</tr>
					</thead>
					<c:forEach var="item" items="${colorlist}" varStatus="vs">
						<tr>
							<td>${item.id}</td>
							<td><img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="80" height="80"><br>${item.title}</td>
							<td>${item.category}</td>
							<td>${item.price}</td>
							<td>${item.size}</td>
							<td>${item.color}</td>
							<td>${item.quantity}</td>
							<td>${item.details}</td>
							<td>
								<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}">Edit</button>
								<div class="modal fade" id="myModal${vs.index}" role="dialog">
								    <div class="modal-dialog">
								    	<div class="modal-content">
									        <div class="modal-header">
									          <button type="button" class="close" data-dismiss="modal">&times;</button>
									          <h4 class="modal-title">${item.title} Information</h4>
									        </div>
									        <div class="modal-body">
									        	<form method="POST" action="edit.htm">
										        	Title: <input type="text" class="form-control" name="title" value="${item.title}"><br>
										        	Price: <input type="text" class="form-control" name="price" value="${item.price}"><br>
										        	Size: <input type="text" class="form-control" name="size" value="${item.size}"><br>
										        	Color: <input type="text" class="form-control" name="color" value="${item.color}"><br>
										        	Quantity: <input type="text" class="form-control" name="quantity" value="${item.quantity}"><br>
										        	Details: <input type="text" class="form-control" name="details" value="${item.details}"><br>
										        	<input type="submit" class="btn btn-primary btn-sm" value="Save">
										        	<input type="hidden" name="action" value="editItem">
										        	<input type="hidden" name="itemId" value='${item.id}'>
									        	</form>
									        </div>
									    </div>
									</div>
								</div>
								<a href="manageItem.htm?action=delete&itemId=${item.id}"><button type="button" class="btn btn-info btn-xs">Delete</button></a>
							</td>
						</tr>
					</c:forEach>
	             </table>
	        </c:otherwise>
	    </c:choose>
	</div>
</body>
</html>