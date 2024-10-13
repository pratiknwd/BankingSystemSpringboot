<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Account Registration Form</title>
<%@ include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/Form.css'/>" rel="stylesheet" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script src="<c:url value='/resources/js/createAccount.js' />"></script>
</head>
<body>
	<input type="hidden" id="contextPath"
		value="${pageContext.request.contextPath}" />
	<div class="container my-5" id="body-div">
		<h2 class="mb-5 text-center">Account Registration Form</h2>
		<div class="row justify-content-md-center">
			<form:form
				action="${pageContext.request.contextPath}/registrationSuccess"
				method="post" modelAttribute="register">
				<c:if test="${not empty userId}">
					<div class="form-floating  mb-3">
						<form:input path="userId" type="text" id="userId"
							cssClass="form-control" disabled="true"
							style="background: rgba(255, 255, 255, 0.1); color: rgba(255, 255, 255, 0.6)"
							value="${userId}" />
						<form:label path="userId">UserId </form:label>
					</div>
				</c:if>
				<c:if test="${empty userId}">
					<form:hidden path="userId" />
					<div class="form-floating mb-3">
						<form:input path="name" type="text" id="name"
							cssClass="form-control" placeholder="Enter Your Name" />
						<form:label path="name">Name :</form:label>
						<form:errors path="name" cssClass="text-danger" />
					</div>
					<div class="form-floating mb-3">
						<form:input path="email" type="email" id="email"
							cssClass="form-control" placeholder="Enter Your Email" />
						<form:label path="email">Email :</form:label>
						<form:errors path="email" cssClass="text-danger" />
					</div>
					<div class="form-floating mb-3">
						<form:input path="mobile" type="number" id="mobile"
							cssClass="form-control" placeholder="Enter Mobile Number" />
						<form:label path="mobile">Mobile :</form:label>
						<form:errors path="mobile" cssClass="text-danger" />
					</div>
					<div class="form-floating mb-3">
						<form:input path="address" type="text" id="address"
							cssClass="form-control" placeholder="Enter Your Address" />
						<form:label path="address">Address :</form:label>
						<form:errors path="address" cssClass="text-danger" />
					</div>
				</c:if>
				<div class="form-floating mb-3">
					<form:select path="bank" id="banks" cssClass="form-select">
						<option value="">--Select Bank--</option>
						<c:forEach var="item" items="${banks}">
							<option value="${item.getBankName()}">${item.getBankName()}</option>
						</c:forEach>
					</form:select>
					<form:label path="bank">Bank</form:label>
					<form:errors path="bank" cssClass="text-danger" />
				</div>
				<div class="form-floating mb-3">
					<form:select path="ifsc" id="IFSC" cssClass="form-select">
						<option value="">--Select IFSC code--</option>
					</form:select>
					<form:label path="ifsc">IFSC Code</form:label>
					<form:errors path="ifsc" cssClass="text-danger" />
				</div>
				<div class="form-floating mb-3">
					<form:select path="accountType" id="accountType"
						cssClass="form-select">
						<option value="">--Select Account Type--</option>
						<option value="SAVINGS">Savings Account</option>
						<option value="INVESTMENT">Investment Account</option>
						<option value="CHECKING">Checking Account</option>
					</form:select>
					<form:label path="accountType">Account Type</form:label>
					<form:errors path="accountType" cssClass="text-danger" />
				</div>
				<div class="form-floating mb-3">
					<form:input path="initialAmount" type="number" id="initial-amount"
						cssClass="form-control" placeholder="Enter initial amount" />
					<form:label path="initialAmount">Initial Amount</form:label>
					<form:errors path="initialAmount" cssClass="text-danger" />
				</div>
				<div class="text-center">
					<button type="submit" class="btn btn-success">Submit</button>
					<a href="javascript:history.back()" class="btn btn-secondary">Back</a>
				</div>
			</form:form>
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
