<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%!//JSP declaration block
	int visits;//private instance variable 
	//instance method
	int updateVisits() {
		return ++visits;
	}
//life cycle
	public void jspInit() {
		System.out.println("in init - " + Thread.currentThread());
	}%>
<body>
	<%
	System.out.println("in scriptlet - " + Thread.currentThread());
	%>
	<h5>
		Visits -
		<%=updateVisits()%></h5>
</body>
<%!public void jspDestroy() {
		System.out.println("in destroy - " + Thread.currentThread());
	}%>
</html>