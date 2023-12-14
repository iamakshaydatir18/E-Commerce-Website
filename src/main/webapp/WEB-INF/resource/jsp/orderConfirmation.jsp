<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false"%>
<jsp:useBean id="now" class="java.util.Date" />

<%@ include file="/WEB-INF/resource/jsp/navbar.jsp"%>
<!DOCTYPE jsp:useBean PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<c:url value="resource/bootstrap/css/bootstrap.min.css"/>">
<script src="<c:url value="resource/js/jquery.js"/>"></script>
<script src="<c:url value="resource/bootstrap/js/bootstrap.min.js"/>"></script>
<link rel="stylesheet" type="text/css"
	href="<c:url value="resource/css/register.css"/>">
		
	</head>
	<body style="padding: 0px;">
		<div class="container-wrapper">
	<div class="container" style="margin-bottom: 19px">
	 <div class="row" style="margin-top: 20px">
     <form:form commandName="customerOrder" class="form-horizontal" action="/order/submit/${cartId}/${customerOrder.customerOrderId}">
		<div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3" style="width: 100%; margin-left: 0%">
		<div style="text-align: center;"><h1>Receipt</h1></div>
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-6">
				<address>
				<strong>Shipping Address</strong><br />
				${customerOrder.customer.shippingAddress.address} <br />
				${customerOrder.customer.shippingAddress.city},
				${customerOrder.customer.shippingAddress.state} <br />
				${customerOrder.customer.shippingAddress.country},
				${customerOrder.customer.shippingAddress.zipcode}
				</address>
			</div>
			<div class="col-xs-6 col-sm-6 col-md-6 text-right">
				<p>Shipping Date:<fmt:formatDate type="date" value="${now}" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-6 col-sm-6 col-md-6">
				<address>
				<strong>Billing Address</strong><br />
				${customerOrder.customer.billingAddress.address} <br />
				${customerOrder.customer.billingAddress.city},
				${customerOrder.customer.billingAddress.state} <br />
				${customerOrder.customer.billingAddress.country},
				${customerOrder.customer.billingAddress.zipcode}
				</address>
			</div>
		</div>
		<div class="row">
		<div class="container">
			<table class="table table-hover">
				<thead>
    				<tr>
					<th class="text-center">Product</th>
					<th class="text-center">Quantity</th>
					<th class="text-center">Price</th>
					<th class="text-center">Total</th>
					</tr>
				</thead>
				<tbody>
				<c:forEach var="cartItem" items="${customerOrder.cart.cartItem}">
					<tr>
					<td style="text-align: center"><em>${cartItem.product.productName}</em></td>
					<td style="text-align: center">${cartItem.quality}</td>
					<td style="text-align: center">${cartItem.product.productPrice}</td>
					<td style="text-align: center">${cartItem.price}</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
			</div>
					<div>
					<h4><strong>Grand Total: </strong> <strong class="text-danger">$ ${customerOrder.cart.totalPrice}</strong></h4>
					</div>
					
		</div>			
						<input style="margin-left: 200px" type="submit" value="Submit Order" class="btn btn-lg btn-info"
							name="orderConfirmed" />

					</div>
				</form:form>
			</div>
		</div>
	</div>
		
	</body>
</html>


<%@ include file="/WEB-INF/resource/jsp/footer.jsp"%>