<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Exception Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .container {
            max-width: 600px;
            margin: 50px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        h1 {
            color: #d9534f;
        }
        p {
            color: #333;
        }
        .details {
            margin-top: 20px;
            padding: 10px;
            background-color: #f7f7f9;
            border: 1px solid #e1e1e8;
            border-radius: 4px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Something went wrong!</h1>
        <p>We are sorry, but an unexpected error occurred.</p>
        <div class="details">
            <h3>Error Details</h3>
            <p><strong>URL:</strong> ${url}</p>
            <p><strong>Exception:</strong> ${exception.message}</p>
        </div>
    </div>
</body>
</html>