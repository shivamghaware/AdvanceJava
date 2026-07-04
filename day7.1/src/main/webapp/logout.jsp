<%@page import="java.time.LocalDate"%>
<%@page import="com.cdac.pojos.User"%>
<%@page import="java.time.Period"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Hello , ${sessionScope.user_details.name}</h5>
	<%
	User user = (User) session.getAttribute("user_details");
	int ageInYears = Period.between(user.getDob(), LocalDate.now()).getYears();
	%>
	<h5>
		User's Age -
		<%=ageInYears%></h5>
	<%
	session.invalidate();
	%>
	<h5>You have logged out...</h5>
	<h6>
		<a href="index.jsp">Visit Again</a>
	</h6>
</body>
</html>