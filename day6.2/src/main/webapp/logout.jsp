<%@page import="java.time.LocalDate"%>
<%@page import="com.catalog.entities.User"%>
<%@page import="java.time.Period"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>logout</title>
</head>
<body>
	<h5>Hello, ${sessionScope.user_details.name}</h5>
	<%
		User user=(User)session.getAttribute("user_details");
		int ageinYear=Period.between(user.getDob(), LocalDate.now()).getYears();
	%>
	<h5>User's Age - <%=ageinYear%>></h5>
	<%
		session.invalidate();
	%>
	<h5>Logout SuccessFull</h5>
	<h5><a href="index.html">visit Again!</a></h5>
	
</body>
</html>