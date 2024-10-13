<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Update Branch</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f9;
	margin: 0;
	padding: 20px;
}

h2 {
	color: #333;
	text-align: center;
}

.form-container {
	max-width: 600px;
	margin: 0 auto;
	background: #fff;
	padding: 20px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 8px;
}

label, .error {
	display: block;
	margin: 10px 0 5px;
}

input[type="text"], select {
	width: calc(100% - 22px);
	padding: 10px;
	margin-bottom: 20px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

input[type="submit"], button {
	background-color: #4CAF50;
	color: white;
	border: none;
	padding: 10px 20px;
	text-align: center;
	text-decoration: none;
	display: inline-block;
	font-size: 16px;
	margin: 4px 2px;
	cursor: pointer;
	border-radius: 4px;
}

button {
	background-color: #007BFF;
}

.error {
	color: red;
}

.navigation-buttons {
	text-align: center;
	margin-top: 20px;
}
</style>
</head>
<body>
	<h2>Update Branch</h2>

	<div class="form-container">
		<!-- Check if there is an error message in the model and display it -->
		<c:if test="${not empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>

		<div>
			<label for="bankSelect">Select Bank:</label> 
			<select id="bankSelect" onchange="populateBranches()">
				<option value="">Select a Bank</option>
				<c:forEach items="${allBanks}" var="bank">
					<option value="${bank.bankId}" data-branches='${bank.branches}'>${bank.bankName}</option>
				</c:forEach>
			</select>
		</div>

		<!-- Form to update the branch details -->
		<form:form modelAttribute="updateBranchDto" action="updateBranch" method="post">
			<div>
				<form:label path="ifsc">Select Branch:</form:label>
				<form:select id="branchSelect" path="ifsc" disabled="true">
					<option value="">Select a Branch</option>
				</form:select>
				<form:errors path="ifsc" cssClass="error" />
			</div>
			<div>
				<form:label path="address">Branch Address:</form:label>
				<form:input path="address" />
				<form:errors path="address" cssClass="error" />
			</div>

			<div>
				<input type="submit" value="Update Branch" />
			</div>
		</form:form>

		<!-- Buttons for navigation and deletion -->
		<div class="navigation-buttons">
			<form action="${pageContext.request.contextPath}/admin/home" method="get" style="display: inline;" >
				<button type="submit">Go to Admin Home</button>
			</form>

			<form action="deleteBranchForm" method="get" style="display: inline;">
				<button type="submit">Delete Branch</button>
			</form>
		</div>
	</div>

	<script>
        function populateBranches() {
            const bankSelect = document.getElementById('bankSelect');
            const branchSelect = document.getElementById('branchSelect');
            const selectedOption = bankSelect.options[bankSelect.selectedIndex];

            // Clear previous branches
            branchSelect.innerHTML = '<option value="">Select a Branch</option>';

            if (selectedOption.value === "") {
                branchSelect.disabled = true;
                return;
            }

            branchSelect.disabled = false;
            const branches = JSON.parse(selectedOption.getAttribute("data-branches"));

            branches.forEach(branch => {
                const option = document.createElement('option');
                option.value = branch.ifsc; // Assuming branch ID is represented by IFSC
                option.textContent = branch.ifsc; // Display IFSC, can be changed as needed
                branchSelect.appendChild(option);
            });
        }
    </script>
</body>
</html>
