<%@page import="com.cdac.pojos.Product"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>From 1st page</h5>
	<%
	// Create Product class instance & save it under suitable scope (for Request Dispatching)
	Product product = new Product(Integer.parseInt(request.getParameter("pid")), request.getParameter("name"),
			Double.parseDouble(request.getParameter("price")));

	// add the same under request scope
	request.setAttribute("product_details", product);
			pageContext.setAttribute("abc", 12345);
	%>
	<jsp:include page="test7.jsp">
		<jsp:param value="ELECTRONICS" name="category" />
	</jsp:include>
</body>
</html>