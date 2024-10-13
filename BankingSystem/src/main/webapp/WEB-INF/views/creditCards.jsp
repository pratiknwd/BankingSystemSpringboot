<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Card Display</title>
<link rel="preconnect" href="https://fonts.gstatic.com">
<link
	href="https://fonts.googleapis.com/css2?family=Josefin+Sans:wght@300;400&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css2?family=Nunito:wght@300;400&display=swap"
	rel="stylesheet">
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='./resources/css/creditCards.css'/>"
	rel="stylesheet" />
<style>
/* Additional styles for hover effect */
.card:hover::before, .card:hover::after {
	content: '';
	position: absolute;
	width: 50px;
	height: 100%;
	background-color: rgba(0, 0, 0, 0.1);
	z-index: 1;
}

.card:hover::before {
	left: -50px;
}

.card:hover::after {
	right: -50px;
}
</style>
</head>
<body>
	<h1 id="heading">Choose a Credit Card</h1>
	<div class="container">
		<div class="card" onclick="redirectToApply(1)">
			<div class="logo">
				<div class="type">PLATINUM</div>
				<img
					src="https://raw.githubusercontent.com/dasShounak/freeUseImages/main/Visa-Logo-PNG-Image.png"
					alt="Visa">
			</div>
			<div class="chip">
				<img
					src="https://raw.githubusercontent.com/dasShounak/freeUseImages/main/chip.png"
					alt="chip">
			</div>
			<div class="number">1234 5678 9012 3456</div>
			<div class="name">JHON DOE</div>
			<div class="from">10/19</div>
			<div class="to">06/21</div>
			<div class="ring"></div>
		</div>
		<div class="card bg-img" onclick="redirectToApply(2)">
			<div class="inner-card">
				<div class="flex justify-end relative py-6">
					<div class="secondCard-type">MasterCard</div>
					<span
						class="rounded-full absolute top-0 right-10 bg-amber-400/50 w-10 h-10"
						style="margin-right: 19px"></span> <span
						class="rounded-full absolute top-0 right-5 bg-red-500/50 w-10 h-10"></span>
				</div>
				<h1 class="font-bold text-2xl mb-2 text-center text-stone-600"
					style="padding: 37px; padding-bottom: 27px;">1098 7654 3210
					1091</h1>
				<div class="details">
					<div id="name">
						<span class="text-xs">Card Holder</span>
						<h3 class="text-lg text-slate-950 font-semibold">JHON DOE</h3>
					</div>
					<div class="card-valid">
						<span class="text-xs">Valid</span>
						<h3 class="text-lg text-slate-950 font-semibold">31/02</h3>
					</div>
					<div>
						<div class="text-blue-600 font-medium text-3xl visa-logo">VISA</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="back">
	<a href="javascript:history.back()">Back</a>
	</div>
	<script>
		function redirectToApply(cardId) {
			/* console.log("Redirecting to apply4creditcard with id:", cardId); // Debugging
			window.location.href = `apply4creditcard?id=${cardId}`;  */
		 const url = new URL(
					'${pageContext.request.contextPath}/apply4creditcard/',
					window.location.origin);
			url.searchParams.append('id', cardId);
			window.location.href = url.toString();
		}
	</script>
</body>
</html>
