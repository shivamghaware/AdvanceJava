<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Error Handler Page</title>
</head>
<body>
	<h5 style="color: red;">Error Message -
		${pageContext.exception.message}</h5>
	<h5 style="color: red;">Error Details - ${pageContext.exception}</h5>
	<h5 style="color: red;"> Error Causing Page URI - 
	${pageContext.errorData.requestURI}
	</h5>	
	<h5 style="color: red;"> Error Code - 
	${pageContext.errorData.statusCode}
	</h5>
	

</body>
</html>