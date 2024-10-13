<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Branch</title>
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h2>Add Branch</h2>
    
    <!-- Check if there is an error message in the model and display it -->
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">
            ${errorMessage}
        </div>
    </c:if>
    
    <!-- Form to add a new branch -->
    <form:form modelAttribute="addBranchDto" action="addBranch" method="post">

        <div>
            <form:label path="ifsc">Branch IFSC Code:</form:label>
            <form:input path="ifsc" />
            <form:errors path="ifsc" cssClass="error" />
        </div>
        
        <div>
            <form:label path="address">Branch Address:</form:label>
            <form:input path="address" />
            <form:errors path="address" cssClass="error" />
        </div>
        
         <div>
            <form:label path="bankName">Select Bank:</form:label>
            <form:select path="bankName">
                <form:options items="${allBanks}" itemValue="bankName" itemLabel="bankName" />
            </form:select>
            <form:errors path="bankName" cssClass="error" />
        </div>
        
        <div>
            <input type="submit" value="Add Branch" />
        </div>
    </form:form>
</body>
</html>