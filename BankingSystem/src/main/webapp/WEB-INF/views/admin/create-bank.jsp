<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Bank</title>
</head>
<body>
    <h2>Create Bank</h2>
    
    <!-- Check if there is an error message in the model and display it -->
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">
            ${errorMessage}
        </div>
    </c:if>
    
    <!-- Form to create a new bank -->
    <form:form modelAttribute="registerBankDto" action="createBank" method="post">
        <div>
            <form:label path="bankName">Bank Name:</form:label>
            <form:input path="bankName" />
            <form:errors path="bankName" cssClass="error" />
        </div>
        
        <div>
            <input type="submit" value="Create Bank" />
        </div>
    </form:form>
</body>
</html>
