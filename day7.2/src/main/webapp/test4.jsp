<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!String message1 = "hello";//var - private instance var%>
<body>
	<h5>main page ...</h5>
	<%
	String message2 = "hi ....";//var - local variable
	pageContext.setAttribute("message3", "Good morning !!!");
	%>
	<%@ include file="test5.jsp"%>
</body>
</html>