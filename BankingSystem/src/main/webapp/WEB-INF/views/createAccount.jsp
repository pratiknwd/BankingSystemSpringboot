<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/createAccount.css'/>"
	rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/createAccount.js' />"></script>
</head>
<body>
	<input type="hidden" id="contextPath"
		value="${pageContext.request.contextPath}" />
	<div id="createForm">
		<div class="mainContainer">
			<div class="container">
				<div class="logiform-container py-4">
					<div
						class="flex-container d-flex flex-wrap justify-content-center bg-light p-0">
						<div class="column d-block p-3 p-md-4 p-lg-5 p getstarted-col">
							<div
								class="d-flex gap-4 content p-3 px-md-4 py-md-5 px-lg-5 child-w-100 flex-wrap position-relative h-100 align-items-center">
								<div class="text-content position-relative">
									<span class="text-secondary2">Hi Welcome!</span>
									<h2 class="text-white">Let's Get Started</h2>
									<p class="text-secondary2 mt-4">Create free account and get
										free access of full features for 7 days. We invite you to join
										us and get better experience.</p>
								</div>
								<div class="content-icon position-relative">
									<img
										src="https://cdn.pixabay.com/photo/2016/06/15/16/16/man-1459246_1280.png"
										alt="" class="w-100">
								</div>
							</div>
						</div>
						<div
							class="column d-block p-3 d-flex align-items-center justify-content-center h-100">
							<div class="content">
								<div class="form-wrapper py-4">
									<h2 class="mb-4">Account Registration Form</h2>
									<form:form action="registrationSuccess" method="post"
										modelAttribute="register">
										<c:if test="${not empty userId}">
											<div class="form-input mb-3 p-0">
												<!-- <label for="userId" class="text-secondary">UserId</label> -->
												<div class="input-relative position-relative mt-1 mt-lg-2">
													<form:input path="userId" type="hidden"
														cssClass="default-input rounded-pill py-1 ps-3 py-lg-2"
														value="${userId}" />
												</div>
											</div>
										</c:if>
										<c:if test="${empty userId}">
											<div class="form-input mb-3 p-0">
												<label for="name" class="text-secondary">Name</label>
												<div class="input-relative position-relative mt-1 mt-lg-2">
													<form:input path="name" type="text"
														cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
														required="true" />
													<form:errors path="name" cssClass="text-danger" />
												</div>
											</div>
											<div class="form-input mb-3 p-0">
												<label for="email" class="text-secondary">Email</label>
												<div class="input-relative position-relative mt-1 mt-lg-2">
													<form:input path="email" type="email"
														cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
														required="true" />
													<form:errors path="email" cssClass="text-danger" />
												</div>
											</div>
											<div class="form-input mb-3 p-0">
												<label for="mobile" class="text-secondary">Mobile</label>
												<div class="input-relative position-relative mt-1 mt-lg-2">
													<form:input path="mobile" type="number"
														cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
														maxlength="13" required="true" />
													<form:errors path="mobile" cssClass="text-danger" />
												</div>
											</div>
											<div class="form-input mb-3 p-0">
												<label for="address" class="text-secondary">Address</label>
												<div class="input-relative position-relative mt-1 mt-lg-2">
													<form:input path="address" type="text"
														cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
														maxlength="100" required="true" />
													<form:errors path="address" cssClass="text-danger" />
												</div>
											</div>
										</c:if>
										<div class="form-input mb-3 p-0">
											<label for="bank" class="text-secondary">Bank</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:select path="bank" id="banks"
													cssClass="form-select rounded-pill">
													<option value="">--Select Bank--</option>
													<c:forEach var="item" items="${banks}">
														<option value="${item.getBankName()}">${item.getBankName()}</option>
													</c:forEach>
												</form:select>
												<form:errors path="bank" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="ifsc" class="text-secondary">IFSC</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:select path="ifsc" id="IFSC"
													cssClass="form-select rounded-pill">
													<option value="">--Select IFSC code--</option>
												</form:select>
												<form:errors path="ifsc" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="accountType" class="text-secondary">Account
												Type</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:select path="accountType" id="accountType"
													cssClass="form-select rounded-pill">
													<option value="">--Select Account Type--</option>
													<option value="SAVINGS">Savings Account</option>
													<option value="INVESTMENT">Investment Account</option>
													<option value="CHECKING">Checking Account</option>
												</form:select>
												<form:errors path="accountType" cssClass="text-danger" />
											</div>
										</div>
										<c:if test="${empty userId}">
											<div class="form-input mb-3 p-0">
												<label for="uIdType" class="text-secondary">UId Type</label>
												<div class="input-relative position-relative mt-1 mt-lg-2">
													<form:select path="uIdType" id="uIdType"
														cssClass="form-select rounded-pill">
														<option value="">--Select UId Type--</option>
														<option value="AADHAR">Aadhaar Card</option>
														<option value="PASSPORT">Passport</option>
														<option value="VOTER-ID">Voter ID</option>
													</form:select>
													<form:errors path="uIdType" cssClass="text-danger" />
												</div>
											</div>
											<div class="form-input mb-3 p-0">
												<label for="uId" class="text-secondary">UID</label>
												<div class="input-relative position-relative mt-1 mt-lg-2">
													<form:input path="uId" type="number"
														cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
														maxlength="16" required="true" />
													<form:errors path="uId" cssClass="text-danger" />
												</div>
											</div>
										</c:if>
										<div class="form-input mb-3 p-0">
											<label for="initialAmount" class="text-secondary">Initial
												Amount</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:input path="initialAmount" type="number"
													cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
													maxlength="20" />
												<form:errors path="initialAmount" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-submit">
											<button id="btnCreateAccount" type="submit"
												class="btn btn-success w-100 rounded-pill py-lg-2 mt-1 mt-lg-2">Create
												Account</button>
										</div>

										<c:if test="${not empty userId}">
											<div class="form-submit">
												<a href="javascript:history.back()"
													class="btn btn-dark w-100 rounded-pill py-lg-2 mt-1 mt-lg-2">Back</a>
											</div>
										</c:if>
									</form:form>
									<c:if test="${empty userId}">
										<div class="have-account-option text-center mt-2">
											<p>
												Already have an account? <a href="login"> Login Here </a>
											</p>
										</div>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>
		//for removing the account type the user hold in the bank 
		var accountTypesToRemove = [];
		<c:forEach var="accountType" items="${accType}">
		accountTypesToRemove.push("${accountType}");
		</c:forEach>;

		console.log("Account Types to Remove:", accountTypesToRemove);

		function removeAccountTypeOptions(accountTypes) {
			var selectElement = document.getElementById("accountType");
			var options = selectElement.options;

			accountTypes.forEach(function(accountType) {
				for (var i = 0; i < options.length; i++) {
					if (options[i].value === accountType) {
						selectElement.remove(i);
						break;
					}
				}
			});
		}

		// Remove the specified account types from the dropdown
		removeAccountTypeOptions(accountTypesToRemove);
	</script>
</body>
</html>
