<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>All Banks</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>All Banks</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Bank ID</th>
                    <th>Bank Name</th>
                    <th>Details</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="bank" items="${banks}">
                    <tr>
                        <td>${bank.bankId}</td>
                        <td>${bank.bankName}</td>
                        <td><a href="${pageContext.request.contextPath}/admin/banks/findByName/${bank.bankName}" class="btn btn-info">View Details</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-secondary mt-3">Back to Admin Home</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
