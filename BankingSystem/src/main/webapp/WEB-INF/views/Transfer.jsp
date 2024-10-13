<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/Form.css'/>" rel="stylesheet" />
<style>
#alert {
    position: fixed;
    top: -100px; /* Start off-screen */
    left: 50%;
    transform: translateX(-50%);
    transition: top 0.5s, opacity 0.5s;
    opacity: 0;
    height: 3rem;
    display: flex;
    justify-content: center;
    align-items: center;
    width: 25rem;
    z-index: 10;
}

.alert-show {
    top: 1.4rem !important; /* Position it on screen */
    opacity: 1 !important;
}

.alert-hide {
    top: -100px !important; /* Move it back off-screen */
    opacity: 0 !important;
}
</style>
</head>
<body>
    <c:if test="${not empty alert }">
        <div id="alert" class="alert alert-${color}" role="alert">${alert}</div>
    </c:if>
    <div class="container my-5" id="body-div">
        <h2 class="mb-5 text-center">Money Transfer</h2>
        <div class="row justify-content-md-center">
            <form:form action="${pageContext.request.contextPath}/processtransaction"
                method="post" modelAttribute="transaction" onsubmit="return confirmTransaction()">
                <input type="hidden" name="userId" value="${userId}">
                <div class="form-floating mb-3">
                    <form:select path="fromAccountNumber" id="accountNum" class="form-select">
                        <option value="" disabled selected>---Select an account---</option>
                        <c:forEach var="item" items="${accountNumberList}">
                            <option value="${item}">${item}</option>
                        </c:forEach>
                    </form:select>
                    <label for="accountNum">Account Number :</label>
                    <form:errors path="fromAccountNumber" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input path="toAccountNumber" id="floatingToAccountNumber" class="form-control" placeholder="Receivers Account Number" />
                    <label for="floatingToAccountNumber">Receivers Account Number :</label>
                    <form:errors path="toAccountNumber" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input path="IFSC" id="floatingIFSC" class="form-control" placeholder="Receivers IFSC" />
                    <label for="floatingIFSC">Receivers IFSC :</label>
                    <form:errors path="IFSC" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input path="amount" id="amount" class="form-control" placeholder="Amount" required="true"/>
                    <label for="amount">Amount :</label>
                    <form:errors path="amount" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <form:input path="tPin" id="floatingTpin" class="form-control" placeholder="Tpin" />
                    <label for="floatingTpin">Tpin :</label>
                    <form:errors path="tPin" cssClass="text-danger" />
                </div>
                <div class="form-floating mb-3">
                    <input type="hidden" class="form-control" id="floatingModeOfTransaction"
                        name="modeOfTransaction" value="Online_Banking"
                        style="background: rgba(255, 255, 255, 0.1); color: rgba(255, 255, 255, 0.6)">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-success">Transfer</button>
                    <a href="${pageContext.request.contextPath}/home" class="btn btn-secondary">Back</a>
                </div>
            </form:form>
        </div>
    </div>
    <script>
        document.addEventListener('DOMContentLoaded', (event) => {
            const alertBox = document.getElementById('alert');

            // Show the alert
            alertBox.classList.add('alert-show');

            // Hide the alert after 3 seconds
            setTimeout(() => {
                alertBox.classList.remove('alert-show');
                alertBox.classList.add('alert-hide');
            }, 3000);
        });
        
        function confirmTransaction() {
            var amount = document.getElementById("amount").value;
            if (parseFloat(amount) > 100000) {
                return confirm("Amount > 100000. This transaction will be tracked. Do you want to proceed?");
            }
            return true; // Proceed with the form submission if the amount is less than or equal to 1 lakh
        }
    </script>
</body>
</html>
