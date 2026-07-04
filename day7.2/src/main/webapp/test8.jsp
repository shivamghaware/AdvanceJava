<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Testing JSTL Actions</title>
</head>
<body>
	<c:set var="product_stock" value="${param.stock}" scope="session" />
	<c:redirect url="test9.jsp">
		<c:param name="price">50000</c:param>
	</c:redirect>
</body>
</html>