<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GST-CALCULATOR</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/gstCalculator.css'/>"
	rel="stylesheet" />
</head>
</head>
<body>
	<div class="gstcalac">
		<div>
			<div class="gstcalacheadline">
				<h1>
					GST <span class="highlight">Calculator</span>
				</h1>
			</div>
			<div class="outer-box">
				<div class="first-row">
					<div class="input-group">
						<label for="investment-amount">Amount you can invest today</label>
						<input id="investment-amount" type="number"
							placeholder="Enter amount Between 500 and 150000" min="0" />
					</div>
					<div class="dropdown-group">
						<label for="gst-rate">Choose GST Rate:</label> <select
							id="gst-rate" class="investment-input">
							<option value="5">5%</option>
							<option value="12">12%</option>
							<option value="18">18%</option>
							<option value="28">28%</option>
						</select>
					</div>
					<div class="dropdown-group">
						<label for="gst-inclusiveness">Inclusive or Exclusive:</label> <select
							id="gst-inclusiveness" class="investment-input">
							<option value="inclusive">Inclusive</option>
							<option value="exclusive">Exclusive</option>
						</select>
					</div>
				</div>
				<div class="warning-row">
					<div id="amount-warning" class="warning-message"></div>
					<div id="years-Warning" class="warning-message"></div>
				</div>
				<div class="third-row">
					<button id="capture-button" class="capture-button">CALCULATE</button>
				</div>
			</div>
		</div>
		<div id="results-table" class="result-table">
			<h2>GST Calculation Results</h2>
			<table id="gst-results">
				<tbody>
					<tr>
						<th>Item</th>
						<th>Amount</th>
					</tr>
					<tr>
						<td>Actual Amount</td>
						<td id="actual-amount"></td>
					</tr>
					<tr>
						<td>GST Amount</td>
						<td id="gst-amount"></td>
					</tr>
					<tr>
						<td>Total Amount</td>
						<td id="total-amount"></td>
					</tr>
				</tbody>
			</table>
		</div>
	</div>
	<script>
		document.getElementById('capture-button').addEventListener('click',
				function() {
					calculateGST();
				});

		function calculateGST() {
			// Get input values
			var amount = parseFloat(document
					.getElementById('investment-amount').value);
			var gstRate = parseFloat(document.getElementById('gst-rate').value);
			var inclusiveness = document.getElementById('gst-inclusiveness').value;

			// Validate amount (ensure it's not negative)
			if (amount < 0) {
				document.getElementById('amount-warning').innerText = 'Amount cannot be negative.';
				return;
			} else {
				document.getElementById('amount-warning').innerText = '';
			}

			// Calculate GST based on inclusiveness
			var gstAmount = 0;
			var totalAmount = 0;

			if (inclusiveness === 'exclusive') {
				gstAmount = (amount * gstRate) / 100;
				totalAmount = amount + gstAmount;
			} else if (inclusiveness === 'inclusive') {
				gstAmount = (amount * gstRate) / (100 + gstRate);
				totalAmount = amount;
				amount = amount - gstAmount;
			}

			// Display results in the table
			document.getElementById('actual-amount').innerText = 
					+ amount.toFixed(2);
			document.getElementById('gst-amount').innerText = 
					+ gstAmount.toFixed(2);
			document.getElementById('total-amount').innerText = 
					+ totalAmount.toFixed(2);

			// Show the results table
			var resultsTable = document.getElementById('results-table');
			resultsTable.style.display = 'block';
		}
	</script>
</body>
</html>