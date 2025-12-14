<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.orders.model.*"%>

<%
//見com.emp.controller.EmpServlet.java第163行存入req的ordersVO物件 (此為從資料庫取出的ordersVO, 也可以是輸入格式有錯誤時的ordersVO物件)
OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>
--<%=ordersVO == null%>--
<!-- line 100 -->
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>訂單資料修改 - update_order_input.jsp</title>

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
				<h3>訂單資料修改 - update_emp_input.jsp</h3>
				<h4>
					<a href="select_page.jsp"><img src="images/back1.gif"
						width="100" height="32" border="0">回首頁</a>
				</h4>
			</td>
		</tr>
	</table>

	<h3>資料修改:</h3>

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
				<td>訂單編號:<font color=red><b>*</b></font></td>
				<td><%=ordersVO.getOrderId()%></td>
			</tr>
			<tr>
				<td>訂單狀態:</td>
				<td><%=ordersVO.getOrderStatus()%></td>
			</tr>
			<tr>
				<td>會員編號:</td>
				<td><%=ordersVO.getMemberId()%></td>
			</tr>

			<tr>
				<td>原始金額:</td>
				<td><%=ordersVO.getAmount()%></td>
			</tr>

			<tr>
				<td>折扣金額:</td>
				<td><%=ordersVO.getDiscount()%></td>
			</tr>

			<tr>
				<td>付款金額:</td>
				<td><%=ordersVO.getPayment()%></td>
			</tr>

			<tr>
				<td>訂單狀態:</td>
				<td><%=ordersVO.getOrderStatus()%></td>
			</tr>

			<tr>
				<td>收件人姓名:</td>
				<td><%=ordersVO.getReceivingName()%></td>
			</tr>

			<tr>
				<td>收件地址:</td>
				<td><%=ordersVO.getReceivingAddress()%></td>
			</tr>

			<tr>
				<td>收件人電話:</td>
				<td><%=ordersVO.getReceivingPhone()%></td>
			</tr>

			<tr>
				<td>促銷活動ID:</td>
				<td><%=ordersVO.getPromotionId()%></td>
			</tr>

			<tr>
				<td>優惠券ID:</td>
				<td><%=ordersVO.getCouponId()%></td>
			</tr>

			<tr>
				<td>付款方式:</td>
				<td><%=ordersVO.getPaymentMethod()%></td>
			</tr>

			<tr>
				<td>付款狀態:</td>
				<td><%=ordersVO.getPaymentStatus()%></td>
			</tr>

			<tr>
				<td>退貨原因:</td>
				<td><%=ordersVO.getReturnReason()%></td>
			</tr>

			<tr>
				<td>退貨說明:</td>
				<td><%=ordersVO.getReturnText()%></td>
			</tr>

			<tr>
				<td>退貨拒絕原因:</td>
				<td><select name="refusalReason">
						<option value="">-- 請選擇 --</option>
						<c:forEach var="reason" items="${refusalReasonList}">
							<option value="${reason}"
								${ordersVO.refusalReason == reason.label ? 'selected' : ''}>
								${reason.label}</option>
						</c:forEach>
				</select></td>
			</tr>

			<jsp:useBean id="ordersSvc" scope="page"
				class="com.orders.model.OrdersService" />
			<tr>
				<td>退貨狀態:<font color=red><b>*</b></font></td>
				<td><select name="returnStatus">
						<c:forEach var="status" items="${returnStatusList}">
							<option value="${status.name()}"
								${ordersVO.returnStatus == status.label ? 'selected' : ''}>
								${status.label}</option>
						</c:forEach>
				</select></td>

			</tr>
			<tr>
				<td>訂單建立時間:</td>
				<td><%=ordersVO.getOrderCreateTime()%></td>
			</tr>

			<tr>
				<td>訂單更新時間:</td>
				<td><%=ordersVO.getOrderUpdateTime()%></td>
			</tr>

			<tr>
				<td>付款建立時間:</td>
				<td><%=ordersVO.getPaymentCreateTime()%></td>
			</tr>

			<tr>
				<td>退貨建立時間:</td>
				<td><%=ordersVO.getReturnCreateTime()%></td>
			</tr>

			<tr>
				<td>退貨審核時間:</td>
				<td><%=ordersVO.getReturnApproveTime()%></td>
			</tr>
		</table>
		<br> 
		<input type="hidden" name="action" value="update"> 
		<input type="hidden" name="orderId" value="<%=ordersVO.getOrderId()%>">
		<input type="submit" value="送出修改"></FORM>
</body>

</html>