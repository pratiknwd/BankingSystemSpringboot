<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Branch Deleted Successfully</title>
</head>
<body>
	<h2>Branch Deleted Successfully</h2>
	<p>The branch has been deleted successfully.</p>

	<!-- Button to go to the admin home page -->
	<form action="${pageContext.request.contextPath}/admin/home"
		method="get">
		<button type="submit">Go to Admin Home</button>
	</form>
</body>
</html>
