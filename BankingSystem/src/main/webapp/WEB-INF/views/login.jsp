<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/login.css'/>" rel="stylesheet" />
<style type="text/css">
#alert {
	position: fixed;
	top: -100px; /* Start off-screen */
	left: 50%;
	transform: translateX(-50%);
	transition: top 0.5s, opacity 0.5s;
	opacity: 0;
	height: 3rem;
	display: flex;
	justify-content: center;
	align-items: center;
	width: 25rem;
	z-index: 10;
}

.alert-show {
	top: 1.4rem !important; /* Position it on screen */
	opacity: 1 !important;
}

.alert-hide {
	top: -100px !important; /* Move it back off-screen */
	opacity: 0 !important;
}
</style>
</head>
<body>
	<c:if test="${not empty alert }">
		<div id="alert" class="alert alert-${color}" role="alert">${alert}</div>
	</c:if>
	<div id="login-page">
		<main>
			<div class="left-side"></div>

			<div class="right-side">
				<form:form action="loginSuccess" method="post"
					modelAttribute="login">

					<div class="or">Login</div>

					<form:label path="userId">UserId </form:label>
					<form:input type="text" placeholder="Enter userId" path="userId" />
					<form:errors path="userId"
						style="color:red;font-size: small;
    margin:0;" />
					<br>


					<form:label path="bank">Choose Your Bank</form:label>
					<form:select path="bank" id="bank-select">
						<option value="" disabled selected>Select Bank</option>
						<c:forEach var="item" items="${banks}">
							<option value="${item.getBankName()}">${item.getBankName()}</option>
						</c:forEach>
					</form:select>
					<form:errors path="bank"
						style="color:red;font-size: small;
    margin:0;" />
					<br>

					<form:label path="password">Password </form:label>
					<form:input type="password" placeholder="Enter Password"
						path="password" />
					<form:errors path="password"
						style="color:red;font-size: small;
    margin:0;" />
					<br>

					<button type="submit" class="login-btn">Sign in</button>
					<div class="links">
						<a href="checkaccountStatus">Check Account Status</a> <a href="register">New User?</a>
					</div>
				</form:form>
			</div>

		</main>
	</div>
	<script>
        document.addEventListener('DOMContentLoaded', (event) => {
            const alertBox = document.getElementById('alert');

            // Show the alert
            alertBox.classList.add('alert-show');

            // Hide the alert after 3 seconds
            setTimeout(() => {
                alertBox.classList.remove('alert-show');
                alertBox.classList.add('alert-hide');
            }, 3000);
        });
    </script>
</body>
</html>