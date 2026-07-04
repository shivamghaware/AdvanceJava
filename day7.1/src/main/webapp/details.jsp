<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Details Page</title>
</head>
<body>
	<h4>Login Successful !</h4>
	<h5>User Details - ${sessionScope.user_details}</h5>
	
	<%
	String encodedURL = response.encodeURL("logout.jsp");
	%>
	<h5 align="center">
		<a href="<%=encodedURL%>">Log Out</a>
	</h5>
</body>
</html>