<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<%@ include file="./bootstrap.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<style>
body{
margin: 0
}
.topnavbaar {
	display: flex;
	justify-content: space-between;
	align-items: center;
	background: white;
	position: fixed;
	top: 0;
	left: 0;
	width: 100%;
	padding: 10px 20px;
	font-size: 20px;
}

.bankingTypes a, .topnavbaarmenu a {
	margin: 0 10px;
	text-decoration: none;
	color: black;
}

.topnavbaar a {
	position: relative;
	margin: 0 10px;
	text-decoration: none;
	color: black;
}

.topnavbaar a:not(:last-child)::after {
	content: '|';
	position: absolute;
	right: -15px; /* Adjust the position as needed */
	color: black;
}

.rightButton {
	display: flex;
	justify-content: space-between;
	align-items: center;
	width: 9%;
	margin-right: 40px;
	border-radius: 50px;
	background: #fcd9c0;
	border: none;
	color: #b02a30;
	font-size: 16px;
	font-weight: 700;
	line-height: 15px;
	padding: 2px;
	padding-left: 0;
}

#navContactNum {
	width: 20px;
	padding: 0;
}

.secondnavbaar {
	display: flex;
	justify-content: flex-end;
	align-items: center;
	background: white;
	position: fixed;
	top: 40px;
	width: 100%;
}

.searchBar {
	padding: 10px;
	border-radius: 20px;
	border: 2px solid #f37e20;
	margin-top: 10px;
	margin-right: 20px;
	margin-bottom: 10px;
	width: 40%;
}

.Login {
	background-color: #f37e20;
	color: white;
	border: none;
	border-radius: 20px;
	padding: 10px 20px;
	margin-right: 20px;
	cursor: pointer;
	transition: background-color 0.3s;
}

.notificationIcon {
	cursor: pointer;
}

.notification-button {
	position: relative;
	display: flex;
	align-items: center;
	justify-content: center;
	width: 24px;
	height: 24px;
	margin-right: 2.5%;
	border: none;
	background: white;
	color: #f37e20;
}

.icon-button__badge {
	position: absolute;
	top: -9%;
	right: -9%;
	width: 15px;
	height: 15px;
	background: red;
	color: #ffffff;
	display: flex;
	justify-content: center;
	align-items: center;
	border-radius: 50%;
}
</style>
</head>
<body>
	<div>
		<nav class="topnavbaar">
			<!-- <div class="bankingTypes">
				<a href="#saving">Personal</a> <a href="#Investment">Investment</a>
				<a href="#checking">Checking</a>
			</div> -->

			<div class="topnavbaarmenu">
				<a href="#AboutUs">About</a> <a href="#customerService">Customer
					Service</a> <a href="#Locations">Locations</a>
			</div>

			<div class="rightButton">
				<img src="<c:url value='/resources/icons/phonecall.png' />"
					alt="Telephone Icon" id="navContactNum"> 1800 1080
			</div>
		</nav>

		<div class="secondnavbaar">
			<input type="text" class="searchBar" placeholder="Search...">
			<c:if test="${empty userId }"><a href="login"><button class="Login">Login</button></a></c:if>
			<c:if test="${not empty userId }"><a href="logout"><button class="Login">Logout</button></a></c:if>
		</div>
	</div>
</body>
</html>
