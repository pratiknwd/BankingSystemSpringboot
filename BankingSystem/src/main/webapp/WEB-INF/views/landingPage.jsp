<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bank</title>
<%-- <%@include file="./navbaar.jsp"%>  --%>
<link href="<c:url value='/resources/css/landingPage.css'/>"
	rel="stylesheet" />
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

</head>
<body class="landingPage">
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

			<div class="rightButton">

				<img src="<c:url value='/resources/icons/phonecall.png' />"
					alt="Telephone Icon" id="navContactNum"> 1800 1080
			</div>


		</nav>
		<div class="secondnavbaar">
			<input type="text" class="searchBar" placeholder="Search...">
			<%-- <c:if test="${empty userId}">
				<a href="login"><button class="Login">Login</button></a>
			</c:if>
			<c:if test="${not empty userId }">
				<a href="logout"><button class="Login">Logout</button></a>
			</c:if> --%>

			<div class="login-container" id="loginContainer">
				<!-- Buttons will be dynamically added here -->
			</div>
			<%-- <img src="<c:url value='/resources/icons/notification.png' />"
			alt="Notification Icon" class="notificationIcon"> --%>
			<button type="button" class="notification-button">
				<span class="material-icons">notifications</span> <span
					class="icon-button__badge">2</span>
			</button>
		</div>

		<nav class="thirdnavbaar">
			<div class="bankingservices">
				<a href="#Accounts"> Accounts </a> <a href="#Deposits">Deposits</a>
				<a href="#Payments"> Payments</a> <a href="cards"> Cards</a> <a
					href="#Loan"> Loan</a> <a href="#Investment"> Investment</a> <a
					href="FinancialTools"> Financial Tools</a> <a
					href="connect-with-us"> Connect with us</a>
			</div>
		</nav>

		<div class="slider">

			<div class="slides">
				<div class="slide">
					<img
						src="<c:url value='/resources/icons/landingpageimages/image6.webp' />"
						alt="Image 1">
				</div>
				<div class="slide">
					<img
						src="<c:url value='/resources/icons/landingpageimages/image2.webp' />"
						alt="Image 2">
				</div>
				<div class="slide">
					<img
						src="<c:url value='/resources/icons/landingpageimages/image3.webp' />"
						alt="Image 3">
				</div>
				<div class="slide">
					<img
						src="<c:url value='/resources/icons/landingpageimages/image4.jpeg' />"
						alt="Image 4">
				</div>
			</div>
		</div>

	</div>


	<script>
	document.addEventListener("DOMContentLoaded", () => {
	    console.log("DOM fully loaded. Initializing slider...");
	    
	    const slides = document.querySelector('.slides');
	    console.log("Slides element:", slides);
	    
	    const slide = document.querySelectorAll('.slide');
	    console.log("Number of slides:", slide.length);
	    
	    let currentIndex = 0;

	    const nextSlide = () => {
	        currentIndex = (currentIndex + 1) % slide.length;
	        console.log("Current index:", currentIndex);
	        slides.style.transform = `translateX(-${currentIndex * 100}%)`;
	    }

	    setInterval(nextSlide, 3000); 
	});
</script>
	<script>
	 const userId = '<%= session.getAttribute("userId") %>';

        const renderLoginLogoutButton = () => {
            const loginContainer = document.getElementById('loginContainer');

            if (!userId) { 
                loginContainer.innerHTML = `
                    <a href="login"><button class="Login">Login</button></a>
                `;
            } else { 
                loginContainer.innerHTML = `
                    <a href="logout"><button class="Login">Logout</button></a>
                `;
            }
        };

        renderLoginLogoutButton();
    </script>

</body>
</html>


