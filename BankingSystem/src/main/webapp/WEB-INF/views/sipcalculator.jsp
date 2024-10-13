<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SIP Calculator</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<link href="resources/css/sipcalculator.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<h1 class="header-text">
			SIP <span class="sub-header-text">Calculator</span>
		</h1>
		<h3>My Investment Details</h3>

		<form id="sip-form">
			<div class="form-row" id="first">
				<div class="form-group col-md-3">
					<label for="age">*I am</label>
					<div class="years">
						<input type="number" class="form-control" id="age" required>
						<p>Years</p>
					</div>
				</div>
				<div class="form-group col-md-3">
					<label for="investment">*& Want to Invest</label>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">&#8377;</span>
						</div>
						<input type="number" class="form-control" id="investment" required>
					</div>
					<!-- Rahul -->
				</div>
				<div class="form-group col-md-3">
					<label for="frequency">Every</label> <select class="form-control"
						id="frequency">
						<option value="12">Monthly</option>
						<option value="1">Yearly</option>
					</select>
				</div>
			</div>
			<div class="form-row" id="second">
				<div class="form-group col-md-3">
					<label for="years">For</label>
					<div class="years">
						<input type="number" class="form-control" id="years" required>
						<p>Years</p>
					</div>
				</div>
				<div class="form-group col-md-3">
					<label for="return">*My Expected rate of Return</label>
					<div class="years">
						<input type="number" class="form-control" id="return" required>
						<p>%</p>
					</div>
				</div>
				<div class="form-group col-md-3"></div>
			</div>
			<button type="submit" class="btn btn-primary calculate-btn">Calculate</button>
		</form>
		<p id="disclaimer">
			Disclaimer:<br> The values shown here are only for illustration.<br>
			The results are generated based on the information provided. It is
			not intended to be and must not alone be taken as the basis for an
			investment decision.
		</p>

		<div id="result-container">
			<div id="table-container-more-10" class="result-table"
				style="display: none;">
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>Start Investing</th>
							<th id="more10-age-1"></th>
							<th id="more10-age-2"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Investment Amount</td>
							<td id="more10-investment-1"></td>
							<td id="more10-investment-2"></td>
						</tr>
						<tr>
							<td>Investment Period</td>
							<td id="more10-period-1"></td>
							<td id="more10-period-2"></td>
						</tr>
						<tr>
							<td>Total Investment</td>
							<td id="more10-total-1"></td>
							<td id="more10-total-2"></td>
						</tr>
						<tr>
							<td>Returns at the age <span id="more10-return-age"></span></td>
							<td id="more10-returns-1"></td>
							<td id="more10-returns-2"></td>
						</tr>
					</tbody>
				</table>
				<p>
					For the same <b>Total Investment Amount of Rs <span
						id="more10-total-amount" class="blue-text"></span></b>, you will
					receive <b><span id="more10-lower-returns" class="red-text"></span>%
						lower returns</b>, if you delay for more than 10 Years.
				</p>
			</div>

			<div id="table-container-less-10" class="result-table"
				style="display: none;">
				<table class="table table-bordered">
					<thead class="thead-dark">
						<tr>
							<th>Start Investing</th>
							<th id="less10-age"></th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>Investment Amount</td>
							<td id="less10-investment"></td>
						</tr>
						<tr>
							<td>Investment Period</td>
							<td id="less10-period"></td>
						</tr>
						<tr>
							<td>Total Investment</td>
							<td id="less10-total"></td>
						</tr>
						<tr>
							<td>Returns at the age <span id="less10-return-age"></span></td>
							<td id="less10-returns"></td>
						</tr>
					</tbody>
				</table>
				<p>
					In just <b><span id="less10-years"></span> years</b>, your invested
					amount grows by <b><span id="less10-growth" class="blue-text"></span>%</b>.
				</p>
			</div>
		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
	<script>
document.getElementById('sip-form').addEventListener('submit', function(event) {
    event.preventDefault();
    calculateSIP();
});

function calculateSIP() {
    var age = parseInt(document.getElementById('age').value);
    var investment = parseFloat(document.getElementById('investment').value);
    var frequency = parseInt(document.getElementById('frequency').value);
    var years = parseInt(document.getElementById('years').value);
    var rateOfReturn = parseFloat(document.getElementById('return').value) / 100;

    var totalPayments = years * frequency;
    var periodicRate = rateOfReturn / frequency;

    var sipValue = investment * (Math.pow(1 + periodicRate, totalPayments) - 1) / periodicRate * (1 + periodicRate);
    var totalInvestment = investment * totalPayments;

    function formatNumber(num) {
        if (num >= 10000000) {
            return (num / 10000000).toFixed(1) + ' Cr';
        } else if (num >= 100000) {
            return (num / 100000).toFixed(1) + ' Lakh';
        }
        return num.toLocaleString();
    }

    function calculateGrowth(initial, final) {
        return ((final - initial) / initial * 100).toFixed(1);
    }

    var message;
    var growth = calculateGrowth(totalInvestment, sipValue);

    // Validation
    if (age < 18 || age > 65) {
        alert("Age must be between 18 and 65.");
        return;
    }

    if (rateOfReturn < 0 || rateOfReturn > 30) {
        alert("Expected rate of return must be between 0% and 30%.");
        return;
    }

    if (years < 1 || years > 99) {
        alert("Investment period (years) must be between 1 and 99.");
        return;
    }

    
        document.getElementById('less10-age').innerText = 'Age ' + age;
        document.getElementById('less10-investment').innerText = formatNumber(investment);
        document.getElementById('less10-period').innerText = years + ' Yrs';
        document.getElementById('less10-total').innerText = formatNumber(totalInvestment);
        document.getElementById('less10-return-age').innerText = age + years;
        document.getElementById('less10-returns').innerText =  formatNumber(sipValue.toFixed(2));
        document.getElementById('less10-years').innerText = years;
        document.getElementById('less10-growth').innerText = growth;

        document.getElementById('table-container-more-10').style.display = 'none';
        document.getElementById('table-container-less-10').style.display = 'block';
}
</script>
</body>
</html>
