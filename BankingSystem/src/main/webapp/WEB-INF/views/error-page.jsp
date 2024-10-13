<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Error</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
	<div class="container mt-5">
		<div class="alert alert-danger" role="alert">
			<h4 class="alert-heading">Error</h4>
			<p>${errorMessage}</p>
			<hr>
			<c:choose>
				<c:when test="${empty userId}">
					<a href="${pageContext.request.contextPath}/admin/adminlogin"
						class="btn btn-secondary">Back to Login Page</a>
				</c:when>
				<c:otherwise>
					<a href="${pageContext.request.contextPath}/admin/home"
						class="btn btn-secondary">Back to Admin Home Page</a>
				</c:otherwise>
			</c:choose>

		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
