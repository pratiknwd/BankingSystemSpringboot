<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Branch Locations</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/brancLocation.css'/>"
	rel="stylesheet" />
</head>
<body>
	<div class="container">
		<h1>Branch Locations</h1>
		<table id="branch-table">
			<thead>
				<tr>
					<th height="80" width="64">Number</th>
					<th>IFSC</th>
					<th>Address</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="branch" items="${branchNames}" varStatus="loop">
					<tr class="${loop.index % 2 == 0 ? 'even' : 'odd'}">
						<td>${loop.index + 1}</td>
						<td>${branch.ifsc}</td>
						<td>${branch.address}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>
</body>
</html>
