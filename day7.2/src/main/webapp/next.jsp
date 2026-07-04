<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h5> from 2nd page ....</h5>
	<h5>Guess the o/p</h5>
	<h5>page scoped attribute - ${pageScope.attr1}</h5>
	<h5>request scoped attribute - ${requestScope.attr2}</h5>
	<h5>session scoped attribute - ${sessionScope.attr3}</h5>
	<h5>application(ctx) scoped attribute - ${applicationScope.attr4}</h5>
</body>
</html>