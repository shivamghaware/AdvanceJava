<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>Session ID Via EL Syntax - ${pageContext.session.id}</h5>
	<%--Render email n password using  scriptlet--%>
	
	<h4>Via Scriptlet</h4>
	<%
	//scriptlet -> _jspService
	out.print("<h5> Email " + request.getParameter("em") + "</h5>");
	out.print("<h5> Password  " + request.getParameter("pass") + "</h5>");
	%>
	<hr />
	<%--Render email n password using  Expression--%>
	<h4>Via JSP Expression</h4>
	<h5>Email - <%=request.getParameter("em")%></h5>
	<h5>Password - <%=request.getParameter("pass")%></h5>
	
	
	<hr />
	<h4>Via EL syntax</h4>
	<h5>Email - ${param.em}</h5>
	<h5>Password - ${param.pass}</h5>
	<h5>param - ${param}</h5>

</body>
</html>