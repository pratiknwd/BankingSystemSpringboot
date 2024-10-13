<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Connect With Us</title>
<%@include file="./bootstrap.jsp"%>
<link href="<c:url value='/resources/css/connectWithUs.css'/>"
	rel="stylesheet" />
</head>
<body>
	<div>
		<div class="mtitle">
			<h1
				class="title-text line-h-48 f-40 md-f-22 md-line-h-26 fw-900 letter-space-1 no-margin">
				Need Help? <br> Get in Touch with Our Helpdesk.
			</h1>
		</div>
		<div class="firstrowcards">
			<div class="card">
				<div class="card-body">
					<h3
						class="f-20 md-f-18 fw-900 line-h-23 md-line-h-21 grey-text pb-2"
						style="color: #333333; font-weight: 600;">Raise Service
						Requests</h3>

					<p class="card-text">Raise Service Request to avail various
						services offered by us.</p>

				</div>
			</div>

			<div class="card">
				<div class="card-body">
					<h3
						class="f-20 md-f-18 fw-900 line-h-23 md-line-h-21 grey-text pb-2"
						style="color: #333333; font-weight: 600;">Contact Customer
						Care</h3>
					<p class="card-text">contact Our Bank Customer Care, use the
						contact details updated here.</p>
					<p class="card-text">Please do not use the contact details
						updated on any other website as it can be fake and may risk your
						account security.</p>
					<h3
						style="color: #f37e20;; margin-top: 50px; margin-left: 80px; align-self: centre; font-weight: 800">
						+917903481023</h3>

				</div>
			</div>

			<div class="card">
				<div class="card-body">
					<h3
						class="f-20 md-f-18 fw-900 line-h-23 md-line-h-21 grey-text pb-2"
						style="color: #333333; font-weight: 600;">Visit Helpdesk</h3>
					<p class="card-text">Visit the helpdesk at any of our branches.
						Click the links to find the nearest Branch</p>
					<div class="bottom-link">
						<a href="getAllBranches" class="ic-more link-no-arrow fw-700">
							Branch / Bank@Home </a>
					</div>

				</div>
			</div>
		</div>
	</div>
</body>
</html>