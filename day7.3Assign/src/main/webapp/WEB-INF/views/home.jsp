<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home Page</title>
</head>
<body>
	<div style="text-align: center;">
		<h2>Product Categories-</h2>

		<form action="${pageContext.request.contextPath}/products" method="post">
			<label>Category</label> 
			<select name="categoryId" required="required">
				<c:forEach var="category" items="${category_details}">
					<option value="${category.categoryId}">${category.categoryName}</option>
				</c:forEach>
			</select>
			<button type="submit">Submit</button>
		</form>
	</div>
</body>
</html>