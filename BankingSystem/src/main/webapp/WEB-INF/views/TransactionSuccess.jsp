<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transaction Success</title>
<%@include file="bootstrap.jsp" %>
<style>
    body {
        background-color: #f8f9fa;
        font-family: 'Quicksand', sans-serif;
    }
    .success-container {
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .success-box {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        text-align: center;
    }
    .success-box h1 {
        color: #28a745;
        font-size: 2.5rem;
    }
    .success-box p {
        font-size: 1.2rem;
        color: #6c757d;
    }
    .success-box .btn {
        margin-top: 20px;
        padding: 10px 20px;
        font-size: 1rem;
        border-radius: 5px;
    }
    .success-icon {
        font-size: 4rem;
        color: #28a745;
        margin-bottom: 20px;
    }
</style>
</head>
<body>
<div class="container-fluid success-container">
    <div class="success-box">
        <div class="success-icon">
            <i class="fas fa-check-circle"></i>
        </div>
        <h1>Transaction Successful</h1>
        <p>Your transaction has been completed successfully.</p>
        <a href="${pageContext.request.contextPath}/home" class="btn btn-success">Go to Home</a>
        <a href="alltransactions" class="btn btn-secondary">View Transactions</a>
    </div>
</div>
</body>
</html>
