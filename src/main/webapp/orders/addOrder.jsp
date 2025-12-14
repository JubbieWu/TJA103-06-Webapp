<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orders.model.*"%>

<%
OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>訂單資料新增 - addOrder.jsp</title>

<style>
table#table-1 {
	background-color: #CCCCFF;
	border: 2px solid black;
	text-align: center;
}

table#table-1 h4 {
	color: red;
	display: block;
	margin-bottom: 1px;
}

h4 {
	color: blue;
	display: inline;
}
</style>

<style>
table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
}

table, th, td {
	border: 0px solid #CCCCFF;
}

th, td {
	padding: 1px;
}
</style>

</head>
<body bgcolor='white'>

	<table id="table-1">
		<tr>
			<td>
				<h3>訂單資料新增 - addOrder.jsp</h3>
			</td>
			<td>
				<h4>
					<a href="select_page.jsp"><img src="images/tomcat.png"
						width="100" height="100" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料新增:</h3>

	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font style="color: red">請修正以下錯誤:</font>
		<ul>
			<c:forEach var="message" items="${errorMsgs}">
				<li style="color: red">${message}</li>
			</c:forEach>
		</ul>
	</c:if>

	<FORM METHOD="post" ACTION="order.do" name="form1">
		<table>




			<tr>
				<td>會員ID:</td>
				<td><input type="TEXT" name="memberId"
					value="<%=(ordersVO == null) ? "105" : ordersVO.getMemberId()%>"
					size="10" /></td>
			</tr>
			<tr>
				<td>原始金額:</td>
				<td><input type="TEXT" name="amount"
					value="<%=(ordersVO == null) ? "1000.0" : ordersVO.getAmount()%>"
					size="10" /></td>
			</tr>
			<tr>
				<td>折扣金額:</td>
				<td><input type="TEXT" name="discount"
					value="<%=(ordersVO == null) ? "100.0" : ordersVO.getDiscount()%>"
					size="10" /></td>
			</tr>
			<tr>
				<td>付款金額:</td>
				<td><input type="TEXT" name="payment"
					value="<%=(ordersVO == null) ? "900.0" : ordersVO.getPayment()%>"
					size="10" /></td>
			</tr>
			<tr>
				<td>訂單狀態:</td>
				<td><select size="1" name="orderStatus">
						<c:forEach var="orderStatus" items="${OrderStatus.values()}">
							<option value="${orderStatus.label}">${orderStatus.label}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>收件人姓名:</td>
				<td><input type="text" name="receivingName"
					value="<%=(ordersVO == null) ? "汪曉明" : ordersVO.getReceivingName()%>"
					size="45" /></td>
			</tr>

			<tr>
				<td>收件人地址:</td>
				<td><input type="TEXT" name="receivingAddress"
					value="<%=(ordersVO == null) ? "臺北市中山區南京東路三段219號4F" : ordersVO.getReceivingAddress()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>收件人電話:</td>
				<td><input type="TEXT" name="receivingPhone"
					value="<%=(ordersVO == null) ? "0912-458-753" : ordersVO.getReceivingPhone()%>"
					size="10" /></td>
			</tr>
			<tr>
				<td>促銷活動ID:</td>
				<td><input type="TEXT" name="promotionId"
					value="<%=(ordersVO == null) ? "2" : ordersVO.getPromotionId()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>優惠券ID:</td>
				<td><input type="TEXT" name="couponId"
					value="<%=(ordersVO == null) ? "11" : ordersVO.getCouponId()%>"
					size="45" /></td>
			</tr>
			<tr>
				<td>付款方式:</td>
				<td><select size="1" name="paymentMethod">
						<c:forEach var="paymentMethod" items="${PaymentMethod.values()}">
							<option value="${paymentMethod.label}">${paymentMethod.label}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td>付款狀態:</td>
				<td><select size="1" name="paymentStatus">
						<c:forEach var="paymentStatus" items="${PaymentStatus.values()}">
							<option value="${paymentStatus.label}">${paymentStatus.label}</option>
						</c:forEach>
				</select></td>
			</tr>
<!-- 			<tr> -->
<!-- 				<td>退貨原因:</td> -->
<!-- 				<td><select size="1" name="returnReason"> -->
<%-- 						<c:forEach var="returnReason" items="${ReturnReason.values()}"> --%>
<%-- 							<option value="${returnReason.label}">${returnReason.label}</option> --%>
<%-- 						</c:forEach> --%>
<!-- 				</select></td> -->
<!-- 			</tr> -->
<!-- 			<tr> -->
<!-- 				<td>退貨描述:</td> -->
<!-- 				<td><input type="TEXT" name="returnText" -->
<%-- 					value="<%=(ordersVO == null) ? "有瑕疵" : ordersVO.getReturnText()%>" --%>
<!-- 					size="45" /></td> -->
<!-- 			</tr> -->

			<jsp:useBean id="deptSvc" scope="page"
				class="com.orders.model.OrdersService" />

		</table>
		<br> <input type="hidden" name="action" value="insert"> <input
			type="submit" value="送出新增">
	</FORM>

</body>

</html>