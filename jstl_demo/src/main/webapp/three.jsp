<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form>
		User Preferences <input type="text" name="prefs" /><br /> <input
			type="submit" value="Test JSTL" name="btn1" />
	</form>
	<c:if test="${param.btn1 eq 'Test JSTL'}">
	<h3>From 1st page.....</h3>
	<c:set var="abc" value="${param.prefs}" 
	scope="session"/>
	<%--add c:param to check --%>
	<c:redirect url="four.jsp"/>
	
	</c:if>
</body>
</html>