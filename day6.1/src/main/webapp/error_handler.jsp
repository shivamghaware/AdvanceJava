<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Handler Page</title>
</head>
<body>
	<h5 style="color: red;">
		Error Message -
		<%=exception.getMessage()%></h5>
		<h5 style="color: red;">
		Error Details -
		<%=exception %></h5>
</body>
</html>