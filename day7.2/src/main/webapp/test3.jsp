<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
	String s = "-1234ghsgj5";
	pageContext.setAttribute("int_value", Integer.parseInt(s));
	%>
	<h5>Parsed Int value -  ${int_value}</h5>
</body>
</html>