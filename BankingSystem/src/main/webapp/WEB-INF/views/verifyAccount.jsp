<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>User Details</title>
<%@include file="./bootstrap.jsp"%>
<style>
body {
	background-color: #f8f9fa;
	padding: 20px;
	font-family: Arial, sans-serif;
}

.table-container {
	margin: auto;
	max-width: 80%;
}

h2 {
	margin: 2rem;
	text-align: center;
	color: red;
}

.back {
	width: 10%;
	margin: 2rem 47%;
}

.back a {
	text-decoration: none;
	background: black;
	color: white;
	padding: 7px 20px;
	border-radius: 9px;
	border: 1px solid white;
}

.back a:hover {
	background: rgba(0,0,0,0.7);
}
</style>
</head>
<body>
	<div class="table-container">
		<h1 class="text-center mb-4">Pending Account For Verification</h1>

		<c:if test="${not empty NoUser }">
			<h2>${NoUser}</h2>
		</c:if>
		<c:if test="${empty NoUser }">
			<table class="table table-bordered table-hover">
				<thead class="thead-dark">
					<tr>
						<th scope="col">ID</th>
						<th scope="col">Name</th>
						<th scope="col">Email</th>
						<th scope="col">Address</th>
						<th scope="col">UID-Type</th>
						<th scope="col">UID</th>
						<th scope="col">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="item" items="${users}">
						<tr>
							<td>${item.userId }</td>
							<td>${item.name }</td>
							<td>${item.email }</td>
							<td>${item.address }</td>
							<td>${item.uIdType }</td>
							<td>${item.uId }</td>
							<td>
								<button class="btn btn-success btn-sm"
									onclick="approveUser(${item.userId})">Approve</button> <%-- <button class="btn btn-danger btn-sm"
								onclick="rejectUser(${item.userId})">Reject</button> --%>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</c:if>
		<div class="back">
			<a href="javascript:history.back()">Back</a>
		</div>
	</div>

	<script>
        function approveUser(userId) {
            if (confirm('Are you sure you want to approve this user?')) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/admin/approveUser',
                    type: 'POST',
                    data: { userId: userId },
                    success: function(response) {
                        if (response === 'success') {
                            alert('User approved successfully.');
                            location.reload(); // Reload the page to reflect changes
                        } else {
                            alert('Failed to approve the user.');
                        }
                    },
                    error: function() {
                        alert('Error occurred while approving the user.');
                    }
                });
            }
        }

        /* function rejectUser(userId) {
            if (confirm('Are you sure you want to reject this user?')) {
                // Similar implementation for rejection
                alert('User ' + userId + ' rejected.');
            }
        } */
    </script>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.3/dist/umd/popper.min.js"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
