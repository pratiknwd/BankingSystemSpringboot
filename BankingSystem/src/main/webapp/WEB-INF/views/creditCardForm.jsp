<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/createAccount.css'/>"
	rel="stylesheet" />
</head>
<body>
	<div id="createForm">
		<div class="mainContainer">
			<div class="container">
				<div class="logiform-container py-4">
					<div
						class="flex-container d-flex flex-wrap justify-content-center bg-light p-0">
						<div class="column d-block p-3 p-md-4 p-lg-5 p getstarted-col">
							<div
								class="d-flex gap-4 content p-3 px-md-4 py-md-5 px-lg-5 child-w-100 flex-wrap position-relative h-100 align-items-center">
								<div class="text-content position-relative">
									<span class="text-secondary2">Hi, Welcome!</span>
									<h2 class="text-white">Unlock Your Financial Freedom</h2>
									<p class="text-secondary2 mt-4">Apply for our exclusive
										credit card today and enjoy unparalleled benefits and rewards.
										Don't miss out on this opportunity to elevate your financial
										lifestyle.</p>
								</div>
								<div class="content-icon position-relative">
									<img src="<c:url value='./resources/images/creditCard.png' />"
										alt="" class="w-100">
								</div>
							</div>
						</div>
						<div
							class="column d-block p-3 d-flex align-items-center justify-content-center h-100">
							<div class="content">
								<div class="form-wrapper py-4">
									<h2 class="mb-4">Credit Card Application</h2>
									<form:form action="${pageContext.request.contextPath}/apply4creditcard?id=${cardId}" method="post"
										modelAttribute="user">
										<div class="form-input mb-3 p-0">
											<label for="name" class="text-secondary">Name</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:input path="name" type="text"
													cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
													required="true" />
												<form:errors path="name" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="email" class="text-secondary">Email</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:input path="email" type="email"
													cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
													required="true" />
												<form:errors path="email" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="mobile" class="text-secondary">Mobile</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:input path="mobile" type="number"
													cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
													maxlength="13" required="true" />
												<form:errors path="mobile" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="address" class="text-secondary">Address</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:input path="address" type="text"
													cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
													maxlength="100" required="true" />
												<form:errors path="address" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="cardType" class="text-secondary">Card
												Type</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:input path="cardType" type="text"
													cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
													maxlength="100" required="true" value="${cardType}" readonly="true"/>
												<form:errors path="cardType" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="uidType" class="text-secondary">UID Type</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:select path="uIdType" id="uidType"
													cssClass="form-select rounded-pill">
													<option value="">--Select UID Type--</option>
													<option value="AADHAAR">Aadhar</option>
													<option value="PASSPORT">Passport</option>
													<option value="VOTER-ID">Voter ID</option>
												</form:select>
												<form:errors path="uIdType" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-input mb-3 p-0">
											<label for="uid" class="text-secondary">UID</label>
											<div class="input-relative position-relative mt-1 mt-lg-2">
												<form:input path="uId" type="text"
													cssClass="default-input rounded-pill py-1 ps-3 py-lg-2 input-required"
												 />
												<form:errors path="uId" cssClass="text-danger" />
											</div>
										</div>
										<div class="form-submit">
											<button id="btnCreateAccount" type="submit"
												class="btn btn-success w-100 rounded-pill py-lg-2 mt-1 mt-lg-2">Apply</button>
										</div>
										<div class="form-submit">
											<a href="javascript:history.back()"
												class="btn btn-dark w-100 rounded-pill py-lg-2 mt-1 mt-lg-2">Back</a>
										</div>
									</form:form>
									<%-- <c:if test="${empty userId}">
                                        <div class="have-account-option text-center mt-2">
                                            <p>
                                                Already have an account? <a href="login"> Login Here </a>
                                            </p>
                                        </div>
                                    </c:if> --%>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
