<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h4>Welcome to JSP , Server Date - <%= new Date() %></h4>
	<h5>
		<a href="comments.jsp">Test Comments</a>
	</h5>
	<h5>
		<a href="login.jsp">Test Scripting Elements</a>
	</h5>
	<h5>
		<a href="test1.jsp">Test JSP Implicit Objects</a>
	</h5>
	<h5>
		<a href="scopes.jsp">Test Scopes</a>
	</h5>
	<h5>
		<a href="test2.jsp">Test JSP Declaration Block</a>
	</h5>
	<h5>
		<a href="test3.jsp">Test Centralized Exception Handling in JSP</a>
	</h5>
	<h5>
		<a href="test4.jsp">JSP include directive</a>
	</h5>
	<h5>
		<a href="test6.jsp?pid=101&name=TV&price=123456">JSP forward or include and param</a>
	</h5>
	<h5>
		<a href="test8.jsp?stock=100">JSTL c:set and  c:redirect</a>
	</h5>
</body>
</html>