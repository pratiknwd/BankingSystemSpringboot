<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/login.css'/>" rel="stylesheet" />
<style type="text/css">
.left-side {
	background-image:
		url("https://images.pexels.com/photos/4482900/pexels-photo-4482900.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2");
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
				<form:form action="internetBankingRegistration" method="post"
					modelAttribute="internetBankingRegistration">

					<div class="or">Internet Banking Registration</div>

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
					<form:input type="password"
						placeholder="*UserId is your default password" path="password" />
					<form:errors path="password"
						style="color:red;font-size: small;
    margin:0;" />
					<br>


					<button type="submit" class="login-btn">Proceed</button>
					<div class="links">
						<a href="login">Sign in</a> <a href="register">Don't have an
							account?</a>
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