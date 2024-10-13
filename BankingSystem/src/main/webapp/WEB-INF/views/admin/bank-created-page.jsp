<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Bank Created Successfully</title>
</head>
<body>
	<h2>Bank Created Successfully</h2>
	<p>Your bank has been created successfully.</p>

	<!-- Buttons to redirect to admin home page and add branch page -->
	<form action="${pageContext.request.contextPath}/admin/home" method="get">
		<button type="submit">Go to Admin Home</button>
	</form>

	<form action="${pageContext.request.contextPath}/admin/bank/addBranch" method="get">
		<button type="submit">Add Branch</button>
	</form>
</body>
</html>