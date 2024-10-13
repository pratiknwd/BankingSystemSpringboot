<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Branch Added Successfully</title>
</head>
<body>
	<h2>Branch Added Successfully</h2>
	<p>Your branch has been added successfully.</p>

	<!-- Buttons to redirect to home page, add branch page, and update branch page -->
	<form action="${pageContext.request.contextPath}/admin/home" method="get">
		<button type="submit">Go to Home</button>
	</form>

	<form action="${pageContext.request.contextPath}/admin/bank/addBranch" method="get">
		<button type="submit">Add Another Branch</button>
	</form>

	<form action="${pageContext.request.contextPath}/admin/bank/updateBranch" method="get">
		<button type="submit">Update Branch</button>
	</form>
</body>
</html>
