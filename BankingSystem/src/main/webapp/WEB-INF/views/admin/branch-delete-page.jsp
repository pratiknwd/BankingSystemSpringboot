<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Delete Branch</title>
</head>
<body>
	<h2>Delete Branch</h2>

	<!-- Check if there is an error message in the model and display it -->
	<c:if test="${not empty errorMessage}">
		<div style="color: red;">${errorMessage}</div>
	</c:if>

	<!-- Form to confirm branch deletion -->
	<form action="${pageContext.request.contextPath}/admin/bank/deleteBranch" method="post">
		<input type="hidden" name="_method" value="delete"/>
		<div class="container mt-5">
			<div class="form-group">
				<label for="branchIfsc">Branch IFSC:</label>
				<input type="text" class="form-control" id="branchIfsc" name="branchIfsc" required>
			</div>
			<button type="submit" class="btn btn-primary">Yes, Delete Branch</button>
		</div>
	</form>

	<!-- Button to go back to admin home page -->
	<form action="${pageContext.request.contextPath}/admin/home" method="get">
		<button type="submit">No, Go to Admin Home</button>
	</form>
</body>
</html>
