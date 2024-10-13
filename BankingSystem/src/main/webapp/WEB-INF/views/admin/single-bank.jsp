<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Bank Details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h2>Bank Details</h2>
        <table class="table table-bordered">
            <tr>
                <th>Bank ID</th>
                <td>${bank.bankId}</td>
            </tr>
            <tr>
                <th>Bank Name</th>
                <td>${bank.bankName}</td>
            </tr>
        </table>
        
        <h3>Branches</h3>
        <c:if test="${not empty bank.branches}">
            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th>Branch IFSC</th>
                        <th>Branch Address</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="branch" items="${bank.branches}">
                        <tr>
                            <td>${branch.ifsc}</td>
                            <td>${branch.address}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <c:if test="${empty bank.branches}">
            <p>No branches available for this bank.</p>
        </c:if>
        
        <a href="${pageContext.request.contextPath}/admin/home" class="btn btn-primary">Back to Admin Home</a>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
