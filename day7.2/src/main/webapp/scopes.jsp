<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5> from 1st page ....</h5>
	<%
	pageContext.setAttribute("attr1", 1000);
	request.setAttribute("attr2", 2000);
	session.setAttribute("attr3", 3000);
	application.setAttribute("attr4", 4000);
	//client pull
	//response.sendRedirect("next.jsp");
	//server pull - RequestDispatcher - forward
	RequestDispatcher rd=request.getRequestDispatcher("next.jsp");
	rd.forward(request, response);	
	%>
</body>
</html>