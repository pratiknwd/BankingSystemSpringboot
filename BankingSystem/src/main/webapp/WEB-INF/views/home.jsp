<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home</title>
<%@include file="./bootstrap.jsp"%>
<!-- Font Awesome for icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link href="<c:url value='/resources/css/userHome.css'/>"
	rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark">
		<a class="navbar-brand" href="#">Bank</a>
		<div class="collapse navbar-collapse" id="navbarNav">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="passbook">Passbook</a></li>
				<li class="nav-item"><a class="nav-link"
					href="transfermoney/${userId}">Transfer Money</a></li>
				<li class="nav-item"><a class="nav-link" href="logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container">
		<div class="row">
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-book"></i>
						<h5 class="card-title">Passbook</h5>
						<p class="card-text">View and manage your passbook entries.</p>
						<a href="passbook" class="btn btn-primary">Go to Passbook</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-exchange-alt"></i>
						<h5 class="card-title">Transfer Money</h5>
						<p class="card-text">Transfer money to other accounts.</p>
						<a href="transfermoney/${userId}" class="btn btn-primary">Transfer
							Money</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-balance-scale"></i>
						<h5 class="card-title">Check Balance</h5>
						<p class="card-text">Check Balance</p>
						<a href="accountBalance/${userId}" class="btn btn-primary">Check
							Balance</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-credit-card"></i>
						<h5 class="card-title">Cards</h5>
						<p class="card-text">Credit/Debit cards</p>
						<a href="cards" class="btn btn-primary">Cards</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-plus-square"></i>
						<h5 class="card-title">Credit Card</h5>
						<p class="card-text">Apply for Credit card</p>
						<a href="creditCards" class="btn btn-primary">Credit card</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-file-alt"></i>
						<h5 class="card-title">Statements</h5>
						<p class="card-text">Transactions Statement</p>
						<a href="alltransactions" class="btn btn-primary">Statement</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-toolbox"></i>
						<h5 class="card-title">Financial Tools</h5>
						<p class="card-text">Financial Tools</p>
						<a href="FinancialTools" class="btn btn-primary">Financial
							Tools</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-user-plus"></i>
						<h5 class="card-title">Create new Account</h5>
						<p class="card-text">New Account Type Creation</p>
						<a href="register" class="btn btn-primary">Create Account</a>
					</div>
				</div>
			</div>
			<div class="col-md-4 mb-4">
				<div class="card">
					<div class="card-body">
						<i class="fas fa-sign-out-alt"></i>
						<h5 class="card-title">Logout</h5>
						<p class="card-text">Securely log out of your account.</p>
						<a href="logout" class="btn btn-primary">Logout</a>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
