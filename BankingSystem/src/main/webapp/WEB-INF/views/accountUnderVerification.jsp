<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Account Under Verification</title>
<%@include file="./bootstrap.jsp"%>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f8f9fa;
	display: flex;
	justify-content: center;
	align-items: center;
	height: 100vh;
	font-family: 'Arial', sans-serif;
	color: #333;
}

.container {
	background-color: #fff;
	padding: 2rem;
	border-radius: 8px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
	text-align: center;
	max-width: 600px;
}

.smiley {
	font-size: 50px;
	margin-bottom: 1rem;
}

h2 {
	margin-bottom: 1rem;
}

p {
	font-size: 1.1rem;
	margin-bottom: 1rem;
}

a.btn {
	margin-top: 1rem;
}

strong {
	color: red;
}
</style>
</head>
<body>
	<div class="container">

		<c:if test="${empty id}">
			<div class="smiley">&#128512;</div>
		</c:if>
		<c:if test="${not empty id}">
			<div class="smiley">&#128512;</div>
		</c:if>
		<h2>Hello, ${name}!</h2>
		<c:if test="${empty id}">
			<p>
				Your request for a new account has been received with application
				number <strong>${account}</strong>.
			</p>
			<p>We are currently verifying your account. We will get back to
				you once it's done. Thank you! Please wait until then.</p>
			<a href="${pageContext.request.contextPath}/" class="btn btn-primary">Back</a>
		</c:if>
		<c:if test="${not empty id}">
			<p>
				Congratulations! Your account has been successfully verified. You
				can now register for Internet Banking using this User ID: <strong>${id}</strong>.
			</p>
			<a href="internetBankingRegistration" class="btn btn-success">Register
				for Internet Banking</a>
		</c:if>
	</div>
	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
