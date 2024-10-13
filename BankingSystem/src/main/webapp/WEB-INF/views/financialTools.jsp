<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FinancialTool</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/financialTool.css'/>"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>
	<div>
		<div>
			<nav class="topnavbaar">

				<div class="bankingTypes">
					<a href="#saving"> Personal </a> <a href="#Investment">Investement</a>
					<a href="#checking"> Checking</a>
				</div>

				<div class="topnavbaarmenu">
					<a href="#AboutUs"> About</a> <a href="#customerService">Customer
						Service</a> <a href="#Locations"> Locations</a>

				</div>
				<div></div>

				<div class="rightButton">

					<img src="<c:url value='/resources/icons/phonecall.png' />"
						alt="Telephone Icon" id="navContactNum"> 1800 1080
				</div>

			</nav>
		</div>

		<div class="secondnavbaar">
			<input type="text" class="searchBar" placeholder="Search...">
			<a href="login"><button class="Login">Login</button></a>
			<%-- <img src="<c:url value='/resources/icons/notification.png' />"
			alt="Notification Icon" class="notificationIcon"> --%>
			<button type="button" class="notification-button">
				<span class="material-icons">notifications</span> <span
					class="icon-button__badge">2</span>
			</button>
		</div>
		<div class="row custom-margin">
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"
							style="font-weight: 500; font-size: 22px; line-height: 28px; color: #005e9e; margin-bottom: 15px;">Compound
							Interest Calculator</h5>
						<p class="card-text">Know how much your investments can grow
							over the time with power of compounding calculator.</p>
						<a href="compound-interest-calculator" class="btn btn-primary">START CALCULATING	</a>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"
							style="font-weight: 500; font-size: 22px; line-height: 28px; color: #005e9e; margin-bottom: 15px;">
							SIP Calculator</h5>
						<p class="card-text">Ready to grow your wealth? Discover your
							SIP's future value with our easy-to-use calculator.</p>
						<a href="sipcalculator" class="btn btn-primary">START CALCULATING</a>
					</div>
				</div>
			</div>
		</div>

		<div class="row custom-margin-2">
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"
							style="font-weight: 500; font-size: 22px; line-height: 28px; color: #005e9e; margin-bottom: 15px;">
							PPF Calculator</h5>
						<p class="card-text">Forecast your PPF maturity and total earnings with our simple calculator.</p>
						<a href="ppfcalculator" class="btn btn-primary">START CALCULATING</a>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title"
							style="font-weight: 500; font-size: 22px; line-height: 28px; color: #005e9e; margin-bottom: 15px;">
							GST Calculator</h5>
						<p class="card-text">Calculates the Goods and Services Tax (GST) amount for a given price and rate.</p>
						<a href="gst-calculator" class="btn btn-primary">START CALCULATING</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>