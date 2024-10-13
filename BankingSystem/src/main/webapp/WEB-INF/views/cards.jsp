<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.virtusa.banking.utility.CardUtils"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Cards Carousel</title>
<%@ include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/cards.css'/>" rel="stylesheet" />
</head>
<body>
	<a href="home"><img id="cross" alt="no img"
		src="<c:url value='/resources/images/cross.svg'/>"></a>
	<div id="carouselExampleIndicators" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-indicators">
			<c:set var="slideIndex" value="0" />
			<c:forEach var="cardsList" items="${cards}" varStatus="outerStatus">
				<c:forEach var="card" items="${cardsList}" varStatus="innerStatus">
					<button type="button" data-bs-target="#carouselExampleIndicators"
						data-bs-slide-to="${slideIndex}"
						class="${slideIndex == 0 ? 'active' : ''}"
						aria-current="${slideIndex == 0 ? 'true' : ''}"
						aria-label="Slide ${slideIndex + 1}"></button>
					<c:set var="slideIndex" value="${slideIndex + 1}" />
				</c:forEach>
			</c:forEach>
			<button type="button" data-bs-target="#carouselExampleIndicators"
				data-bs-slide-to="${slideIndex}"
				aria-label="Slide ${slideIndex + 1}"></button>
		</div>
		<div class="carousel-inner">
			<c:set var="slideIndex" value="0" />
			<c:forEach var="cardsList" items="${cards}" varStatus="outerStatus">
				<c:forEach var="card" items="${cardsList}" varStatus="innerStatus">
					<div class="carousel-item ${slideIndex == 0 ? 'active' : ''}">
						<div class="wrapper">
							<div class="container">
								<c:choose>
									<c:when test="${card.type eq 'Default'}">
										<div class="card">
											<div class="top">
												<h2>${fn:toUpperCase(userName)}</h2>
												<img
													src="https://cdn-icons-png.flaticon.com/512/1436/1436392.png" />
											</div>
											<div class="infos">
												<section class="card-number">
													<p>Card Number</p>
													<h1>${CardUtils.formatCardNumber(card.cardNumber)}</h1>
												</section>
												<div class="bottom">
													<aside class="infos--bottom">
														<section>
															<p>Expiry date</p>
															<h3>${CardUtils.formatExpiryDate(card.expiry)}</h3>
														</section>
														<section>
															<p>CVV</p>
															<h3>${card.cvv}</h3>
														</section>
													</aside>
													<aside>
														<section>
															<img
																src="https://seeklogo.com/images/V/VISA-logo-DD37676279-seeklogo.com.png"
																class="brand" />
														</section>
														<h3 id="card-type">
															<c:choose>
																<c:when test="${card.cardType eq 'DebitCard'}">Debit</c:when>
																<c:otherwise>Credit</c:otherwise>
															</c:choose>
														</h3>
													</aside>
												</div>
											</div>
										</div>
									</c:when>
									<c:when test="${card.type eq 'PLATINUM'}">
										<div class="credit-card">
											<div class="credit-logo">
												<div class="credit-type">PLATINUM</div>
												<img
													src="https://raw.githubusercontent.com/dasShounak/freeUseImages/main/Visa-Logo-PNG-Image.png"
													alt="Visa">
											</div>
											<div class="credit-chip">
												<img
													src="https://raw.githubusercontent.com/dasShounak/freeUseImages/main/chip.png"
													alt="chip">
											</div>
											<div class="credit-number">${CardUtils.formatCardNumber(card.cardNumber)}</div>
											<div class="credit-name">${fn:toUpperCase(userName)}</div>
											<div class="credit-from">${CardUtils.formatExpiryDate(card.expiry)}</div>
											<div class="credit-to">${card.cvv}</div>
											<div class="credit-ring"></div>
											<h3 id="Creditcard1-type">Credit</h3>
										</div>
									</c:when>
									<c:otherwise>
										<div class="credit2-card credit2-bg-img">
											<div class="credit2-inner-card">
												<div class="credit2-flex justify-end relative py-6">
													<div class="credit2-secondCard-type">MasterCard</div>
													<span
														class="credit2-rounded-full absolute top-0 right-10 bg-amber-400/50 w-10 h-10"
														style="margin-right: 19px"></span> <span
														class="credit2-rounded-full absolute top-0 right-5 bg-red-500/50 w-10 h-10"></span>
												</div>
												<h1
													class="font-bold text-2xl mb-2 text-center text-stone-600"
													style="padding: 46px; padding-bottom: 45px;">${CardUtils.formatCardNumber(card.cardNumber)}</h1>
												<div class="credit2-details">
													<div id="credit2-name">
														<span class="text-xs">Card Holder</span>
														<h3 class="text-lg text-slate-950 font-semibold">${fn:toUpperCase(userName)}</h3>
													</div>
													<div class="credit2-card-valid">
														<span class="text-xs">Valid</span>
														<h3 class="text-lg text-slate-950 font-semibold">${CardUtils.formatExpiryDate(card.expiry)}</h3>
													</div>
													<div>
														<div
															class="text-blue-600 font-medium text-3xl credit2-visa-logo">VISA</div>
													</div>
												</div>
											</div>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>
					</div>
					<c:set var="slideIndex" value="${slideIndex + 1}" />
				</c:forEach>
			</c:forEach>
			<div class="carousel-item">
				<div class="wrapper">
					<div class="container">
						<div class="card" id="plus">
							<a href="creditCards" id="add">+</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<button class="carousel-control-prev" type="button"
		data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
		<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Previous</span>
	</button>
	<button class="carousel-control-next" type="button"
		data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
		<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
			class="visually-hidden">Next</span>
	</button>
</body>
</html>
