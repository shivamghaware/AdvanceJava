<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Employee Details Form Using Spring FormTag Library</title>
</head>
<body>

	<form:form method="post" modelAttribute="emp_details">
		<table style="background-color: lightgrey; margin: auto">
			<tr>
				<td>Email</td>
				<td><form:input type="email" path="email" readonly="true" /></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><form:input path="firstName" readonly="true" /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><form:input path="lastName" readonly="true" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><form:input type="password" path="password" /></td>
			</tr>
			<tr>
				<td>Join Date</td>
				<td><form:input type="datetime-local" path="joinDate" /></td>
			</tr>
			<tr>
				<td>Employment Type</td>
				<td><form:input path="empType" /></td>
			</tr>
			<tr>
				<td>Salary</td>
				<td><form:input type="number" path="salary"  /></td>
			</tr>
			<tr>
				<td><input type="submit" value="Update Employee Details" /></td>
			</tr>
		</table>
	</form:form>
</body>
</html>