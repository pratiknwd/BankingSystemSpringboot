<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Admin Home</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<!-- Font Awesome for icons -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
<link href="<c:url value='/resources/css/admin-home.css'/>"
	rel="stylesheet" />
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark">
		<a class="navbar-brand" href="#">Admin Dashboard</a>
		<div class="collapse navbar-collapse">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link" href="#">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#">Profile</a></li>
				<li class="nav-item"><a class="nav-link"
					href="${pageContext.request.contextPath}/admin/logout">Logout</a></li>
			</ul>
		</div>
	</nav>
	<div class="container mt-5">
		<div class="row">
			<!-- Card for Viewing All Banks -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-university"></i> View All Banks
						</h5>
						<p class="card-text">See a list of all registered banks.</p>
						<a href="${pageContext.request.contextPath}/admin/banks"
							class="btn btn-primary">View Banks</a>
					</div>
				</div>
			</div>

			<!-- Card for Adding a New Bank -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-plus-circle"></i> Add New Bank
						</h5>
						<p class="card-text">Register a new bank in the system.</p>
						<a href="${pageContext.request.contextPath}/admin/registerBank"
							class="btn btn-primary">Add Bank</a>
					</div>
				</div>
			</div>

			<!-- Card for Verifying Pending Accounts -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-user-check"></i> Pending Account
						</h5>
						<p class="card-text">Account Pending for Verification.</p>
						<a href="${pageContext.request.contextPath}/admin/verifyAccount"
							class="btn btn-primary">Verify Account</a>
					</div>
				</div>
			</div>

			<!-- Card for Adding a New Branch -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-code-branch"></i> Add New Branch
						</h5>
						<p class="card-text">Add a new branch to an existing bank.</p>
						<a href="${pageContext.request.contextPath}/admin/bank/addBranch"
							class="btn btn-primary">Add Branch</a>
					</div>
				</div>
			</div>

			<!-- Card for Updating a Branch -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-edit"></i> Update Branch
						</h5>
						<p class="card-text">Update details of an existing branch.</p>
						<a
							href="${pageContext.request.contextPath}/admin/bank/updateBranch"
							class="btn btn-primary">Update Branch</a>
					</div>
				</div>
			</div>

			<!-- Card for Deleting a Branch -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-trash-alt"></i> Delete Branch
						</h5>
						<p class="card-text">Delete a branch from the system.</p>
						<a
							href="${pageContext.request.contextPath}/admin/bank/deleteBranchForm"
							class="btn btn-primary">Delete Branch</a>
					</div>
				</div>
			</div>


			<!-- Card for Searching a Bank by ID -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-search"></i> Search Bank by ID
						</h5>
						<p class="card-text">Find a bank by its ID.</p>
						<a
							href="${pageContext.request.contextPath}/admin/bank/enterBankId"
							class="btn btn-primary">Search by ID</a>
					</div>
				</div>
			</div>

			<!-- Card for Searching a Bank by Name -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-search"></i> Search Bank by Name
						</h5>
						<p class="card-text">Find a bank by its name.</p>
						<a
							href="${pageContext.request.contextPath}/admin/banks/findByName"
							class="btn btn-primary">Search by Name</a>
					</div>
				</div>
			</div>

			<!-- Logout -->
			<div class="col-md-4">
				<div class="card text-center">
					<div class="card-body">
						<h5 class="card-title">
							<i class="fas fa-sign-out-alt"></i> Logout
						</h5>
						<p class="card-text">Logout</p>
						<a href="${pageContext.request.contextPath}/admin/logout"
							class="btn btn-primary">Logout</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
