<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Included page ....</h5>
	<h5>Instance var - <%= message1 %></h5>
	<h5>Local var - <%= message2 %></h5>
	<h5>Page Scoped Attribute - ${pageScope.message3}</h5>
</body>
</html>