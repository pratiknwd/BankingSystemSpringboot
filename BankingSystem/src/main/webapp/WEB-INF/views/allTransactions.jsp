<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Statements</title>
<%@ include file="./bootstrap.jsp"%>
<style>
body {
	background-color: black;
	color: #343a40;
	font-family: 'Arial', sans-serif;
}

.container {
	margin-top: 30px;
	padding: 20px;
	background-color: #ffffff;
	border-radius: 8px;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

h1 {
	text-align: center;
	margin-bottom: 30px;
	color: #343a40;
	font-weight: bold;
}

table {
	width: 100%;
	border-radius: 8px;
	overflow: hidden;
	box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

tr {
	border: 2px solid black;
}

th {
	background-color: #343a40;
	color: #ffffff;
	font-weight: bold;
	padding: 12px;
	border: 2px solid black;
}

td {
	padding: 12px;
	background-color: #ffffff;
	color: #343a40;
	border: 2px solid black;
}

.table-hover tbody tr:hover {
	background-color: #e9ecef;
}

.center {
	text-align: center;
}

a {
	text-decoration: none;
	background: black;
	padding: 8px 25px;
	border-radius: 21px;
	color: white;
	margin-left: 45%;
}

h2 {
	margin: 2rem;
	text-align: center;
	color: red;
}
</style>
</head>
<body>
	<div class="container">
		<h1>All Transactions</h1>
		<c:if test="${empty transactions }">
			<h2>No Transactions Yet !</h2>
		</c:if>
		<c:if test="${not empty transactions }">
			<table class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Transaction ID</th>
						<th>From</th>
						<th>To</th>
						<th>Amount</th>
						<th>Transaction Type</th>
						<th>Mode of Transaction</th>
						<th>Date/Time</th>
						<th>Balance</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="transaction" items="${transactions}">
						<c:forEach var="item" items="${transaction}">
							<tr>
								<td class="center">${item.transactionId}</td>
								<c:choose>
									<c:when test="${item.transactionType eq 'CR'}">
										<td>${item.refAccountNumber}</td>
										<td>${item.accountNumber}</td>
									</c:when>
									<c:otherwise>
										<td>${item.accountNumber}</td>
										<td>${item.refAccountNumber}</td>
									</c:otherwise>
								</c:choose>
								<td>${item.amount}</td>
								<c:choose>
									<c:when test="${item.transactionType eq 'CR'}">
										<td style="color: green; font-weight: bold;">${item.transactionType}</td>
									</c:when>
									<c:otherwise>
										<td style="color: red; font-weight: bold;">${item.transactionType}</td>
									</c:otherwise>
								</c:choose>
								<td>${item.modeOfTransaction}</td>
								<td>${item.dateTime}</td>
								<td>&#8377;<fmt:formatNumber
										value="${item.balanceRemaining}" type="currency"
										currencySymbol="" /></td>
							</tr>
						</c:forEach>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<a href="home">Back</a>
	</div>
</body>
</html>
