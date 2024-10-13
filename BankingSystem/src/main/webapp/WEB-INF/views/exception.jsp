<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>ERROR!</title>
<link href="<c:url value='./resources/css/exception.css'/>"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<img src="<c:url value='./resources/images/error.png'/>"
			alt="Error Image">
		<div class="emoji">&#128517;</div>
		<h1>Oops! Something Went Wrong</h1>
		<p>We're sorry, but an unexpected error has occurred.</p>
		<div class="status-code">Status Code: ${status }</div>
		<p>Please try again later or contact support if the problem
			persists.</p>
	</div>
</body>
</html>
