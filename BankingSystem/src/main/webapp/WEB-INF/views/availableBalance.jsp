<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./bootstrap.jsp" %>
<link href="<c:url value='/resources/css/availableBalance.css'/>"
	rel="stylesheet" />
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function() {
    $('#searchButton').click(function() {
        var selectedAccount = $('#inputGroupSelect04').val(); // Get selected account number

        // Ensure an account number is selected
        if (!selectedAccount || selectedAccount === "Choose Account Numbers From Below") {
            alert("Please select an account number.");
            return;
        }

        // AJAX request
        $.ajax({
            type: 'POST', // Method type
            url: '${pageContext.request.contextPath}/balance', // Server endpoint URL
            data: { selectedAccount: selectedAccount }, // Data to send to the server
            dataType: 'text', // Expect plain text response
            success: function(response) {
                console.log('Success response:', response);
                $('#resultContainer').html('<div class="alert alert-success" role="alert">' +
                        '<h4 class="alert-heading">Balance Details</h4>' +
                        '<p>Account Balance: &#8377;' + response + '</p>' +
                        '<hr>' +
                        '<p class="mb-0">This is your current balance.</p>' +
                        '</div>');
            },
            error: function(xhr, status, error) {
                console.error('Error fetching data:', error);
                console.error('Status:', status);
                console.error('Response:', xhr.responseText);
                $('#resultContainer').html('<div class="alert alert-danger" role="alert">' +
                        'Error fetching data: ' + error +
                        '</div>');
            }
        });
    });
});

</script>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center">
			<div class="col-md-6">
				<div class="card">
					<div class="card-header text-center bg-dark text-white">
						<h4>Check Account Balance</h4>
					</div>
					<div class="card-body">
						<div class="input-group">
							<select class="form-select" id="inputGroupSelect04" aria-label="Example select with button addon">
								<option selected>Choose Account Numbers From Below</option>
								<c:forEach var="item" items="${AccountNumbers}">
									<option value="${item}">${item}</option>
								</c:forEach>
							</select>
							<button id="searchButton" class="btn" type="button">Search</button>
						</div>
						<div id="resultContainer" class="mt-3"></div>
						<!-- Container to display the results -->
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
