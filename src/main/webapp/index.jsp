<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/resources/css/index.css" />" rel="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<title>Welcome to Spring Web MVC project</title>
<style>
.image{
	margin-right:30px;
}
</style>
</head>
<body>
<div class="container">
	<c:choose>
        <c:when test="${!empty sessionScope.userName}">
             <jsp:include page="menu2.jsp"/>
        </c:when>
        <c:otherwise>
             <jsp:include page="menu1.jsp"/>
        </c:otherwise>
    </c:choose>
	<h1>Welcome to our online shopping website</h1>
	<ul class="categoryMenu nav nav-tabs">
		<li><a class = "active" href = "categoryContent.htm?action=newArrival">NEW ARRIVALS</a></li>
		<li><a href = "categoryContent.htm?action=denim">DENIM</a></li>
		<li><a href = "categoryContent.htm?action=tees">TEES</a></li>
		<li><a href = "categoryContent.htm?action=dresses">DRESSES</a></li>
		<li><a href = "categoryContent.htm?action=shoes">SHOES</a></li>
		<li><a href = "categoryContent.htm?action=bag">BAG</a></li>
		<li><a href = "categoryContent.htm?action=sale">SALE</a></li>
		
	</ul>
	<br>
	
	<div class="content">
		<table>
			<c:forEach var="item" items="${itemList}" varStatus="vs">
				<c:if test="${vs.index % 4 == 0}"><tr></c:if>
				
					<%-- <td>${item.title}</td>
					<td>${item.price}</td> --%>
					<td>
					<div style="margin-right:15px; margin-top:15px"><img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="260" height="260"><br>
					Title: ${item.title}  |  Price: ${item.price}<br>
					<button type="button" class="btn btn-info btn-xs" data-toggle="modal" data-target="#myModal${vs.index}" id="viewDetailButton${vs.index}">Quick Shop</button>
					  <!-- Modal -->
					  <div class="modal fade" id="myModal${vs.index}" role="dialog">
					    <div class="modal-dialog">
					    
					      <!-- Modal content-->
					      <div class="modal-content">
					        <div class="modal-header">
					          <button type="button" class="close" data-dismiss="modal">&times;</button>
					          <h4 class="modal-title">Item Detail</h4>
					        </div>
					        <div class="modal-body">
					          <div class="img">
					          	
					          	<img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="260" height="260">
					          </div>
					          <div class="detail">
						          <div class="row">
						          Title: ${item.title}
						          </div>
						          <hr>
						          <div class="row">
						          Color: ${item.color}
						          </div>
						          <div class="row">
						          Price: ${item.price}
						          </div>
						          <div class="row">
						          Size: ${item.size}
						          </div>
						          <%-- <div class="row">
						          Product Description: ${item.details}
						          </div> --%>
					          </div>
					        </div>
					        <div class="modal-footer">
					          <a href="addTo.htm?action=cart&itemId=${item.id}" class="btn btn-default">Add to bag</a>
					          <%-- <a href="addTo.htm?action=wishList&itemId=${item.id}" class="btn btn-default" data-dismiss="modal">Add To WishList</a> --%>
					        </div>
					      </div>
					      
					    </div>
					  </div></div>
					</td>
				<c:if test="${vs.count% 4 == 0 or vs.last}"></tr></c:if>
			</c:forEach>
		</table>
	</div>
</div>
</body>
</html>