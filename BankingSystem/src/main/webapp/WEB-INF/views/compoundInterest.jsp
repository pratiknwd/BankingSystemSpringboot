<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Compound Interest</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/compoundInterest.css'/>"
	rel="stylesheet" />
</head>
<body>
	<div class="compoundcalc">
		<div>
			<div class="ppfcalcheadline">
				<h1>
					Compound <span class="highlight"> Interest Calculator</span>
				</h1>
			</div>
			<div class="outer-box">
				<div class="first-row">
					<div class="input-group">
						<label for="investment-amount">Amount you can invest today</label>
						<input id="investment-amount" type="number"
							placeholder="Enter amount Between 500 and 150000" min="0" />

					</div>
					<div class="input-group">
						<label for="investment-amount">Amount you want to invest Repeatedly</label>
						<input id="investment-amount-repeat" type="number"
							placeholder="Enter amount Between 500 and 150000" min="0" />

					</div>
					<div class="input-group">
						<label for="total-years">Number of years you want to stay invest for</label> <input
							id="total-years" type="number"
							placeholder="Enter total Years Between 15 and 50" min="15" />
					</div>
				</div>
				<div class="warning-row">
					<div id="amount-warning" class="warning-message"></div>
					<div id="years-Warning" class="warning-message"></div>

				</div>
				<div class="second-row">
					<div class="dropdown-group">
						<label for="investment-frequency">Choose Annually or
							Monthly:</label> <select id="investment-frequency" class="investment-input">
							<option value="monthly">Monthly</option>
							<option value="quarterly">Quarterly</option>
							<option value="annually">Annually</option>
						</select>
					</div>
					<div>
						<label for="You expect the Annual Rate of Returns to be">Rate of Interest expected(in %):</label> <input
							id="rate-of-interest" class="investment-input" type="number"
							placeholder=""  />
					</div>
				</div>

				<div class="third-row">
					<button id="capture-button" class="capture-button">CALCULATE</button>
				</div>
			</div>
		</div>
		 <!-- Results Table -->
        <div id="results-table" class="result-table">
            <h2>Compound Interest Calculation Results</h2>
            <table id="interest-table">
                <thead>
                    <tr>
                        <th>Year</th>
                        <th>Principal Amount</th>
                        <th>Interest Earned</th>
                        <th>Amount After Year</th>
                    </tr>
                </thead>
                <tbody id="table-body">
                    <!-- Results will be appended here -->
                </tbody>
            </table>
        </div>
    </div>

    <!-- JavaScript for calculation -->
    <script>
    document.getElementById('capture-button').addEventListener('click', function() {
        calculateCompoundInterest();
    });

    function calculateCompoundInterest() {
        // Get input values
        var principal = parseFloat(document.getElementById('investment-amount').value);
        var investmentAmountRepeat = parseFloat(document.getElementById('investment-amount-repeat').value);
        var years = parseInt(document.getElementById('total-years').value);
        var frequency = document.getElementById('investment-frequency').value;
        var annualInterestRate = parseFloat(document.getElementById('rate-of-interest').value) / 100;

        // Validate inputs (you may add more validation as needed)

        // Clear previous results if any
        var tableBody = document.getElementById('table-body');
        tableBody.innerHTML = '';

        // Calculate total investment amount for each year
        var results = [];

        var totalAmountForYear = principal; // Initialize total amount for the first year

        for (var year = 1; year <= years; year++) {
            // Append results to table
            var row = '<tr>';
            row += '<td>' + year + '</td>';
            row += '<td>' + totalAmountForYear.toFixed(2) + '</td>';
            
            // Calculate interest earned for the current year
            var interest = totalAmountForYear * annualInterestRate;
            row += '<td>' + interest.toFixed(2) + '</td>';

            // Calculate amount after the year
            totalAmountForYear += interest; // Add interest to the total amount
            row += '<td>' + totalAmountForYear.toFixed(2) + '</td>';

            row += '</tr>';

            results.push(row);

            // Add additional investments based on frequency for the next year
            if (frequency === 'monthly') {
                totalAmountForYear += investmentAmountRepeat * 12;
            } else if (frequency === 'quarterly') {
                totalAmountForYear += investmentAmountRepeat * 4;
            } else if (frequency === 'annually') {
                totalAmountForYear += investmentAmountRepeat;
            }
        }

        // Display results table
        var resultsTable = document.getElementById('results-table');
        resultsTable.style.display = 'block';

        // Append rows to table body
        tableBody.innerHTML = results.join('');
    }


    </script>
	
</body>
</html>