<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Admin Login</title>
<%@include file="../bootstrap.jsp"%>
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

.or {
  position: relative;
  text-align: center;
  margin-bottom: 24px;
  font-size: 1.5rem;
  font-weight: 600;
}

.or::before,
.or::after {
  content: "";
  position: absolute;
  top: 50%;
  width: 20%;
  height: 1px;
  background: #000;
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
				<form:form action="adminlogin" method="post"
					modelAttribute="adminlogin">

					<div class="or">Admin Login</div>

					<form:label path="adminId">Admin Id </form:label>
					<form:input type="text" placeholder="Enter adminId" path="adminId" />
					<form:errors path="adminId"
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