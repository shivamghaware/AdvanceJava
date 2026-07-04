<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table style="background-color: lightgrey; margin: auto">
		<caption>Employee List from Department ID ${param.deptId}</caption>
		<thead>
			<tr>
				<th>Emp ID</th>
				<th>Name</th>
				<th>Salary</th>
				<th>Join Date</th>
				<th>Employment Type</th>
				<th>Action</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="emp" items="${requestScope.emp_list}">
				<tr>
					<td>${emp.id}</td>
					<td>${emp.firstName}${emp.lastName}</td>
					<td>${emp.salary}</td>
					<td>${emp.joinDate}</td>
					<td>${emp.empType}</td>
					<c:url var="url" value="/employees/update?empId=${emp.id}" />
					<td><a href="${url}">Update</a></td>
					<c:url var="url" value="/employees/delete?empId=${emp.id}" />
					<td><a href="${url}">Delete</a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>