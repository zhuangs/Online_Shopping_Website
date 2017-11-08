<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	function removeRow(itemId, rowID){
		if (xmlHttp == null)
        {
            alert("Your browser does not support AJAX!");
            return;
        }
		var query = "action=remove&item=" + itemId;
		
		/* alert(query); */
		xmlHttp.onreadystatechange = function stateChanged()
        {
            if (xmlHttp.readyState == 4)
            {
            	var row = document.getElementById(rowID);
                row.parentNode.removeChild(row);
            }
        };
        xmlHttp.open("GET", "addTo.htm?"+query, true);
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
	<c:choose>
        <c:when test="${!empty sessionScope.userName}">
             <jsp:include page="../../../menu2.jsp"/>
        </c:when>
        <c:otherwise>
             <jsp:include page="../../../menu1.jsp"/>
        </c:otherwise>
    </c:choose>
	 <c:choose>
            <c:when test="${!empty sessionScope.cart}">
                <h3>Shopping Bag</h3>
                <hr>
                <h4>Item (${requestScope.count})</h4>
                <table class="table table-striped">
                	<thead>
                		<tr>
                			<th></th>
                			<th>Title</th>
                			<th>Size/Color</th>
                			<th>Price</th>
                			<th>Operation</th>
                		</tr>
                	</thead>
                    <c:forEach var="item" items="${sessionScope.cart}" varStatus="vs">
                        <tr id="${vs.index}">
                            <td><img src="<c:url value="/resources/image/${item.photoName}"/>" class="img-thumbnail" alt="Cinque Terre" width="150" height="150"></td>
                            <td>${item.title}</td>
                            <td>Size: ${item.size}<br>Color: ${item.color}</td>
                            <td>${item.price}</td>
                            <td><button onclick = "removeRow(${item.id}, ${vs.index})" class="btn btn-primary btn-sm">Remove</button></td>
                        </tr>
                    </c:forEach>
                    <tr><td></td><td></td></tr>
                    <tr>
                        <td>Total</td>
                        <td>${requestScope.total}</td>
                    </tr>
                </table>
                <br><br>
                <a href="index.jsp"> Shop More | </a>
                <a href="addTo.htm?action=checkout"> Checkout</a>
            </c:when>
            <c:otherwise>
                <h3>Oops.. your cart is empty</h3>
                <p><a href="index.jsp">Return to shopping</a></p>
            </c:otherwise>
        </c:choose>
</div>
</body>
</html>