<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.orders.model.*"%>
<%-- 此頁暫練習採用 Script 的寫法取值 --%>

<%
  OrdersVO ordersVO = (OrdersVO) request.getAttribute("ordersVO");
%>

<html>
<head>
<title>訂單資料 - listOneOrder.jsp</title>

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
	width: 600px;
	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
    border: 1px solid #CCCCFF;
  }
  th, td {
    padding: 5px;
    text-align: center;
  }
</style>

</head>
<body bgcolor='white'>

<h4>此頁暫練習採用 Script 的寫法取值:</h4>
<table id="table-1">
	<tr><td>
		 <h3>訂單資料 - listOneOrder.jsp</h3>
		 <h4><a href="select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">回首頁</a></h4>
	</td></tr>
</table>

<table>
	<tr>
		<th>訂單編號</th>
		<th>會員ID</th>
		<th>原始金額</th>
		<th>折扣金額</th>
		<th>付款金額</th>
		<th>訂單狀態</th>
		<th>收件人姓名</th>
		<th>收件人地址</th>
		<th>收件人電話</th>
		<th>促銷活動ID</th>
		<th>優惠券ID</th>
		<th>付款方式</th>
		<th>付款狀態</th>
		<th>退貨原因</th>
		<th>退貨描述</th>
		<th>退貨狀態</th>
		<th>拒絕原因</th>
		<th>訂單建立時間</th>
		<th>訂單更新時間</th>
		<th>付款建立時間</th>
		<th>退貨建立時間</th>
		<th>退貨審核時間</th>
		
	</tr>
	<tr>
		<td><%=ordersVO.getOrderId()%></td>
		<td><%=ordersVO.getMemberId()%></td>
		<td><%=ordersVO.getAmount()%></td>
		<td><%=ordersVO.getDiscount()%></td>
		<td><%=ordersVO.getPayment()%></td>
		<td><%=ordersVO.getOrderStatus()%></td>
		<td><%=ordersVO.getReceivingName()%></td>
		<td><%=ordersVO.getReceivingAddress()%></td>
		<td><%=ordersVO.getReceivingPhone()%></td>
		<td><%=ordersVO.getPromotionId()%></td>
		<td><%=ordersVO.getCouponId()%></td>
		<td><%=ordersVO.getPaymentMethod()%></td>
		<td><%=ordersVO.getPaymentStatus()%></td>
		<td><%=ordersVO.getReturnReason()%></td>
		<td><%=ordersVO.getReturnText()%></td>
		<td><%=ordersVO.getReturnStatus()%></td>
		<td><%=ordersVO.getRefusalReason()%></td>
		<td><%=ordersVO.getOrderCreateTime()%></td>
		<td><%=ordersVO.getOrderUpdateTime()%></td>
		<td><%=ordersVO.getPaymentCreateTime()%></td>
		<td><%=ordersVO.getReturnCreateTime()%></td>
		<td><%=ordersVO.getReturnApproveTime()%></td>
	</tr>
</table>

</body>
</html>