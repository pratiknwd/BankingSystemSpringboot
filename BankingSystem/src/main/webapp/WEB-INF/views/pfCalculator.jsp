<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PPF Calculator</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/pfCalculator.css'/>"
	rel="stylesheet" />
</head>
<body>
	<div class="ppfcalc">
		<div>
			<div class="ppfcalcheadline">
				<h1>
					PPF<span class="highlight"> Calculator</span>
				</h1>
			</div>
			<div class="outer-box">
				<div class="first-row">
					<div class="input-group">
						<label for="investment-amount">Amount You Want to Invest:</label>
						<input id="investment-amount" type="number"
							placeholder="Enter amount Between 500 and 150000" min="0" />

					</div>
					<div class="dropdown-group">
						<label for="investment-frequency">Choose Annually or
							Monthly:</label> <select id="investment-frequency">
							<option value="monthly">Monthly</option>
							<option value="	quarterly">Quarterly</option>
							<option value="annually">Annually</option>
						</select>
					</div>
					<div class="input-group">
						<label for="total-years">Enter Total Years:</label> <input
							id="total-years" type="number"
							placeholder="Enter total Years Between 15 and 50" min="15" />
					</div>
				</div>
				<div class="warning-row">
					<div id="amount-warning" class="warning-message"></div>
					<div id="years-Warning" class="warning-message"></div>

				</div>
				<div class="second-row">
					<label for="rate-of-interest">Rate of Interest (in %):</label> <input
						id="rate-of-interest" class="investment-input" type="number"
						placeholder="7.1" disabled="disabled" />

				</div>

				<div class="third-row">
					<button id="capture-button" class="capture-button">CALCULATE</button>
				</div>
			</div>
		</div>
		<div id="pfTable" class="result-row" style="display: none;">
    <h3>Calculated PPF Value:</h3>
    <table id="ppf-result-table" class="result-table">
        <thead>
            <tr>
                <th>Result</th>
                <th>Amount</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Total Invested Amount</td>
                <td id="total-invested-amount">₹ 0</td>
            </tr>
            <tr>
                <td>Total Interest</td>
                <td id="total-interest">₹ 0</td>
            </tr>
            <tr>
                <td>Maturity Amount</td>
                <td id="maturity-amount">₹ 0</td>
            </tr>
        </tbody>
    </table>
    
</div>


		<script>
            document.addEventListener('DOMContentLoaded', () => {
            	const investmentAmountInput = document.getElementById('investment-amount');
                const investmentFrequencySelect = document.getElementById('investment-frequency');
                const totalYearsInput = document.getElementById('total-years');
                const captureButton = document.getElementById('capture-button');
                const amountWarning = document.getElementById('amount-warning');
                const yearsWarning = document.getElementById('years-Warning');
                const ppfResult = document.getElementById('ppf-result');

                captureButton.addEventListener('click', () => {
                    // Capture values
                    const investmentAmount = parseFloat(investmentAmountInput.value);
                    const investmentFrequency = investmentFrequencySelect.value;
                    const totalYears = parseInt(totalYearsInput.value, 10);
                    const rateOfInterest = 0.071; // Fixed interest rate of 7.1%

                    // Clear previous warnings
                    amountWarning.textContent = '';
                    yearsWarning.textContent = '';


                    // Print values to console
                    console.log('Investment Amount:', investmentAmount);
                    console.log('Investment Frequency:', investmentFrequency);
                    console.log('Total Months:', totalYears);
                    console.log('Rate of Interest:', rateOfInterest);
                    
                   

                    let isValid = true;

                    // Validate inputs
                    if (isNaN(investmentAmount) || investmentAmount < 500 || investmentAmount > 150000) {
                        amountWarning.textContent = 'Amount must be between 500 and 150,000.';
                        isValid = false;
                    }

                    if (isNaN(totalYears) || totalYears < 15 || totalYears > 50) {
                        yearsWarning.textContent = 'Total Years must be between 15 and 50.';
                        isValid = false;
                    }

                    if (!isValid) {
                        ppfResult.textContent = '';
                        document.querySelector('.result-row').style.display = 'none';
                        return;
                    }

                    
                    let multiplier;
                    if (investmentFrequency === 'monthly') {
                        multiplier = 12; // Monthly deposits
                    } else if (investmentFrequency === 'quarterly') {
                        multiplier = 4;  // Quarterly deposits
                    } else if (investmentFrequency === 'annually') {
                        multiplier = 1;  // Annual deposits
                    } else {
                        console.error('Invalid investment frequency selected.');
                        return;
                    }

                    
                    
                    const P = investmentAmount * multiplier;

                    // Detailed calculations
                    const r = rateOfInterest; // Annual rate
                    const n = totalYears;     // Total years
                    
                    // Calculate PPF future value
                    // M = P * [((1 + i)^n - 1) / i] * (1 + i)
                    const onePlusR = 1 + r;
                    const futureValue = P * (((Math.pow(onePlusR, n) - 1) / r) * onePlusR);
                    /* console.log('total amount', ppfValue);
                    console.log('total interest', interestValue); */
                    console.log('total amount', futureValue.toFixed(0));
                    
                    const totalInvestedAmount = investmentAmount * multiplier * totalYears;

                    // Calculate total interest
                    const totalInterest = futureValue - totalInvestedAmount;

                    // Display the result in the table
                    
                    // Display the result row and scroll into view
                   
                    document.getElementById('pfTable').style.display = 'block';
                    console.log (document.getElementById('total-invested-amount'))
                    document.getElementById('total-invested-amount').innerText = totalInvestedAmount.toFixed(2);
                    document.getElementById('total-interest').innerText = totalInterest.toFixed(2);
                    document.getElementById('maturity-amount').innerText = futureValue.toFixed(2);

                    document.getElementById('pfTable').scrollIntoView({ behavior: 'smooth' });
                    
                    
                });
            });
        </script>
	</div>
</body>
</html>