<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>PassBook</title>
<link href="<c:url value='/resources/css/passbook.css' />" rel="stylesheet" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">

</head>
<body>
    <c:if test="${empty tPin}">
        <div id="alert" class="alert alert-info" role="alert">
            <i class="fas fa-info-circle"></i> Remember to use your user ID to register for Internet Banking!
        </div>
    </c:if>
    <main>
        <div class="book">
            <div class="book-cover">
                <h1><i class="fas fa-book"></i> PassBook</h1>
                <div class="separator"></div>
            </div>
            <div class="book-content">
                <h1><i class="fas fa-user-circle"></i> Account Details</h1>
                <div id="accDetails">
                    <div class="detail-item">
                        <span class="label"><i class="fas fa-user"></i> User ID:</span> <span class="value">${account.getUser().getUserId()}</span>
                    </div>

                    <c:forEach var="item" items="${accountList}">
                        <div class="detail-item">
                            <span class="label"><i class="fas fa-id-card"></i> Account Number:</span> <span class="value">${item.getAccountNumber()}</span>
                        </div>
                        <div class="detail-item">
                            <span class="label"><i class="fas fa-info-circle"></i> Account Type:</span> <span class="value">${item.getAccountType()}</span>
                        </div>
                    </c:forEach>
                    <div class="detail-item">
                        <span class="label"><i class="fas fa-university"></i> IFSC Code:</span> <span class="value">${account.getIfsc()}</span>
                    </div>
                    <div class="detail-item">
                        <span class="label"><i class="fas fa-user"></i> Name:</span> <span class="value">${account.getUser().getName()}</span>
                    </div>
                    <div class="detail-item">
                        <span class="label"><i class="fas fa-envelope"></i> Email:</span> <span class="value">${account.getUser().getEmail()}</span>
                    </div>
                    <div class="detail-item">
                        <span class="label"><i class="fas fa-phone"></i> Mobile:</span> <span class="value">${account.getUser().getPhoneNo()}</span>
                    </div>
                    <div class="detail-item">
                        <span class="label"><i class="fas fa-map-marker-alt"></i> Address:</span> <span class="value">${account.getUser().getAddress()}</span>
                    </div>
                    <c:if test="${not empty tPin}">
                        <div class="detail-item" style="display: flex; align-items: center; justify-content: space-between;">
                            <span class="label" style="color:blue"><i class="fas fa-key"></i> Tpin:</span>
                            <span class="value" id="tpinValue" style="display: none;">${tPin}</span>
                            <button class="toggle-button" onclick="toggleTpin()">Show Tpin</button>
                        </div>
                    </c:if>
                </div>
                <c:if test="${empty tPin}">
                    <div id="button">
                        <a href="internetBankingRegistration"><i class="fas fa-user-plus"></i> Register</a>
                    </div>
                </c:if>
                <c:if test="${not empty tPin}">
                    <div id="button" style="background: black;margin-top:1.2rem;">
                        <a href="home"><i class="fas fa-home"></i> Back</a>
                    </div>
                </c:if>
            </div>
        </div>
    </main>
    <script>
        function toggleTpin() {
            var tpinValue = document.getElementById('tpinValue');
            var toggleButton = document.querySelector('.toggle-button');

            if (tpinValue.style.display === 'none') {
                tpinValue.style.display = 'inline';
                toggleButton.textContent = 'Hide Tpin';
            } else {
                tpinValue.style.display = 'none';
                toggleButton.textContent = 'Show Tpin';
            }
        }

        document.addEventListener('DOMContentLoaded', (event) => {
            const alertBox = document.getElementById('alert');

            // Show the alert
            alertBox.classList.add('alert-show');

            // Hide the alert after 3 seconds
            setTimeout(() => {
                alertBox.classList.remove('alert-show');
                alertBox.classList.add('alert-hide');
            }, 4000);
        });
    </script>

</body>
</html>
