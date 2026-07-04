<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%--import JSTL core tag lib --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%-- <h5>Department List - ${requestScope.dept_list}</h5> --%>
	<c:url var="url" value="/employees/list"/>
	<form action="${url}" method="post">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Choose Department</td>
				<td><select name="deptId">
						<c:forEach var="dept" items="${requestScope.dept_list}">
							<option value="${dept.id}">${dept.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td><input type="submit" value="Choose A Department" /></td>
			</tr>
		</table>
	</form>
</body>
</html>