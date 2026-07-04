<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>products Page</title>
</head>
<body>
	<div style="text-align: center;">

		<c:if test="${not empty product_details}">
			<h2>Products</h2>

			<table border="1" style="margin: auto;">
				<thead>
					<tr>
						<th>Name</th>
						<th>Price</th>
						<th>Stock</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="prod" items="${product_details}">
						<tr>
							<td>${prod.productName}</td>
							<td>${prod.price}</td>
							<td>${prod.stock}</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>

		<c:if test="${empty product_details}">
			<h1>No products found for this category</h1>
		</c:if>
	</div>
</body>
</html>