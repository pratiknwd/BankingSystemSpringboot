<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Enter Bank ID</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script>
        function redirectToBankDetails() {
            var bankId = document.getElementById("bankId").value;
            window.location.href = "${pageContext.request.contextPath}/admin/bank/findByBankId/" + bankId;
        }
    </script>
</head>
<body>
    <div class="container mt-5">
        <h2>Enter Bank ID</h2>
        <div class="form-group">
            <label for="bankId">Bank ID:</label>
            <input type="number" class="form-control" id="bankId" name="bankId" required>
        </div>
        <button type="button" class="btn btn-primary" onclick="redirectToBankDetails()">Get Bank Details</button>
    </div>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
