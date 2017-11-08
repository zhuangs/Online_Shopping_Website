<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form method="POST" action="manageMessage.htm" commandName="message">
								Message Title <form:input path="title" type="text" class="form-control" placeholder="Text input"/>
								Content <form:textarea path="content" class="form-control" rows="3" />
								<input class="btn btn-default" type="submit" value="Submit">
							</form:form>
</body>
</html>