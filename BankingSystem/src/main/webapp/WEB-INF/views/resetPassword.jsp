<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Reset Password</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/login.css'/>" rel="stylesheet" />
<style type="text/css">
.left-side {
	background-image:
		url("https://images.pexels.com/photos/3943723/pexels-photo-3943723.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
}

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
	width: 20rem;
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

.or::before, .or::after {
	content: "";
	position: absolute;
	top: 50%;
	width: 12%;
	height: 1px;
	background: #000;
}
</style>
</head>
<body>
	<c:if test="${not empty alert }">
		<div id="alert" class="alert alert-danger" role="alert">${alert}</div>
	</c:if>
	<div id="login-page">
		<main>
			<div class="left-side"></div>

			<div class="right-side">
				<form:form action="resetPassword" method="post"
					modelAttribute="resetPassword">

					<div class="or">Reset-Password</div>

					<form:label path="oldPassword">Old Password </form:label>
					<form:input type="password" placeholder="Enter old Password"
						path="oldPassword" />
					<form:errors path="oldPassword"
						style="color:red;font-size: small;
    margin:0;" />
					<br>

					<form:label path="newPassword">New Password </form:label>
					<form:input type="password" placeholder="Enter new Password"
						path="newPassword" />
					<form:errors path="newPassword"
						style="color:red;font-size: small;
    margin:0;" />
					<br>
					<form:label path="confirmPassword">Confirm Password </form:label>
					<form:input type="password" placeholder="Confirm Password"
						path="confirmPassword" />
					<form:errors path="confirmPassword"
						style="color:red;font-size: small;
    margin:0;" />
					<br>

					<button type="submit" class="login-btn">Register</button>
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