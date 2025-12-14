package com.orders.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import com.orders.model.OrdersService;
import com.orders.model.OrdersVO;
import com.orders.model.RefusalReason;
import com.orders.model.ReturnStatus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class OrdersServlet extends HttpServlet{
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		doPost(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) 
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		String action = req.getParameter("action");
	
		if ("getOne_For_Display".equals(action)) { // select_page.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 驗證查詢的輸入格式
			String str = req.getParameter("orderId");
			if (str == null || (str.trim()).length() == 0) {
				errorMsgs.add("請輸入訂單編號");
			}
			
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/orders/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			Integer orderId = null;
			try {
				orderId = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("訂單編號格式不正確");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/orders/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			// 查詢資料
			OrdersService ordersSvc = new OrdersService();
			OrdersVO ordersVO = ordersSvc.getByOrderID(orderId);
			if (ordersVO == null) {
				errorMsgs.add("查無資料");
			}
			if(!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req
						.getRequestDispatcher("/orders/select_page.jsp");
				failureView.forward(req, res);
				return;
			}
			
			// 查詢完成
			req.setAttribute("ordersVO", ordersVO); 
			String url = "/orders/listOneOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 成功轉交 listOneEmp.jsp
			successView.forward(req, res);
		}
		
		if ("getOne_For_Update".equals(action)) { // listAllOrder.jsp的請求

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			// 接收請求參數
			Integer orderId = Integer.valueOf(req.getParameter("orderId"));
			
			// 開始查詢資料
			OrdersService ordersSvc = new OrdersService();
			OrdersVO ordersVO = ordersSvc.getByOrderID(orderId);
			
			// 查詢完成,準備轉交
			req.setAttribute("ordersVO", ordersVO);
			req.setAttribute("returnStatusList", ReturnStatus.values());

			req.setAttribute("refusalReasonList", RefusalReason.values());


			String url = "/orders/update_order_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		if ("update".equals(action)) { // 來自update_order_input.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			
			Integer orderId = null;
		    try {
		        orderId = Integer.valueOf(req.getParameter("orderId").trim());
		    } catch (NumberFormatException e) {
		        errorMsgs.add("訂單ID錯誤");
		    }
		    
		    // return_status
		    String returnStatusParam = req.getParameter("returnStatus");
		    ReturnStatus returnStatus = null;
		    try {
		        returnStatus = ReturnStatus.valueOf(returnStatusParam);
		    } catch (IllegalArgumentException | NullPointerException e) {
		        errorMsgs.add("退貨狀態必須選擇");
		    }
		    
		    // refusal_reason (只有在退貨拒絕時才可能有值)
		    String refusalReasonParam = req.getParameter("refusalReason");
		    RefusalReason refusalReason = null;
		    if (returnStatus == ReturnStatus.REJECTED) { 
		        try {
		            refusalReason = RefusalReason.valueOf(refusalReasonParam);
		        } catch (IllegalArgumentException | NullPointerException e) {
		            errorMsgs.add("退貨拒絕原因必須選擇");
		        }
		    }
		    
		    OrdersVO ordersVO = new OrdersVO();
		    ordersVO.setOrderId(orderId);
		    ordersVO.setReturnStatus(returnStatus.getLabel());
		    ordersVO.setRefusalReason(refusalReasonParam);
		    
		    if (!errorMsgs.isEmpty()) {
		        req.setAttribute("ordersVO", ordersVO);
		        RequestDispatcher failureView = req.getRequestDispatcher("/orders/update_return_status.jsp");
		        failureView.forward(req, res);
		        return;
		    }

		    // 開始修改資料
		    OrdersService ordersSvc = new OrdersService();
		    ordersVO = ordersSvc.updateReturnStatus(orderId, returnStatus, refusalReason);
		    
		    ordersVO = ordersSvc.getByOrderID(orderId);
		    
		    // 修改完成,準備轉交
		    req.setAttribute("ordersVO", ordersVO);
		    String url = "/orders/listOneOrder.jsp";
		    RequestDispatcher successView = req.getRequestDispatcher(url);
		    successView.forward(req, res);
		}
		
		if ("insert".equals(action)) { // 來自addOrder.jsp的請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			Integer memberId = null;
			String memberIdStr = req.getParameter("memberId");
			String memberReg = "^[0-9]{3,10}$";
			if (memberIdStr == null || memberIdStr.trim().length() == 0) {
			    errorMsgs.add("會員編號: 請勿空白");
			} else if (!memberIdStr.matches(memberReg)) {
			    errorMsgs.add("會員編號: 格式錯誤，須為3~10位數字");
			} else {
			    try {
			        memberId = Integer.valueOf(memberIdStr); // 將字串轉成 Integer
			    } catch (NumberFormatException e) {
			        errorMsgs.add("會員編號: 請輸入有效數字");
			    }
			}
			
			// 原始金額
			double amount = 0.0;
			try {
			    amount = Double.parseDouble(req.getParameter("amount"));
			    if (amount < 0) errorMsgs.add("原始金額不可小於0");
			} catch (NumberFormatException e) {
			    errorMsgs.add("原始金額: 請輸入數字");
			}

			// 折扣金額
			double discount = 0.0;
			try {
			    discount = Double.parseDouble(req.getParameter("discount"));
			    if (discount < 0) errorMsgs.add("折扣金額不可小於0");
			} catch (NumberFormatException e) {
			    errorMsgs.add("折扣金額: 請輸入數字");
			}

			// 付款金額
			double payment = 0.0;
			try {
			    payment = Double.parseDouble(req.getParameter("payment"));
			    if (payment < 0) errorMsgs.add("付款金額不可小於0");
			} catch (NumberFormatException e) {
			    errorMsgs.add("付款金額: 請輸入數字");
			}

			// 訂單狀態
			String orderStatus = req.getParameter("orderStatus");
			if (orderStatus == null || orderStatus.trim().length() == 0) {
			    errorMsgs.add("訂單狀態: 請選擇");
			}

			// 收件人姓名
			String receivingName = req.getParameter("receivingName");
			if (receivingName == null || receivingName.trim().length() == 0) {
			    errorMsgs.add("收件人姓名: 請勿空白");
			}

			// 收件人地址
			String receivingAddress = req.getParameter("receivingAddress");
			if (receivingAddress == null || receivingAddress.trim().length() == 0) {
			    errorMsgs.add("收件人地址: 請勿空白");
			}

			// 收件人電話
			String receivingPhone = req.getParameter("receivingPhone");
			String phoneReg = "^[0-9\\-]{8,15}$";
			if (receivingPhone == null || receivingPhone.trim().length() == 0) {
			    errorMsgs.add("收件人電話: 請勿空白");
			} else if (!receivingPhone.matches(phoneReg)) {
			    errorMsgs.add("收件人電話: 格式錯誤");
			}

			// 促銷活動ID
			Integer promotionId = null;
			String promotionIdStr = req.getParameter("promotionId");

			if (promotionIdStr != null && !promotionIdStr.trim().isEmpty()) {
			    try {
			        promotionId = Integer.valueOf(promotionIdStr);
			    } catch (NumberFormatException e) {
			        errorMsgs.add("促銷活動ID: 請輸入有效數字");
			    }
			}

			// 優惠券ID
			Integer couponId = null;
			String couponIdStr = req.getParameter("couponId");

			if (couponIdStr != null && !couponIdStr.trim().isEmpty()) {
			    try {
			        couponId = Integer.valueOf(couponIdStr);
			    } catch (NumberFormatException e) {
			        errorMsgs.add("優惠券ID: 請輸入有效數字");
			    }
			}

			// 付款方式
			String paymentMethod = req.getParameter("paymentMethod");
			if (paymentMethod == null || paymentMethod.trim().length() == 0) {
			    errorMsgs.add("付款方式: 請選擇");
			}

			// 付款狀態
			String paymentStatus = req.getParameter("paymentStatus");
			if (paymentStatus == null || paymentStatus.trim().length() == 0) {
			    errorMsgs.add("付款狀態: 請選擇");
			}

			// 退貨原因
			String returnReason = req.getParameter("returnReason");

			// 退貨描述
			String returnText = req.getParameter("returnText");
			if (returnText != null && returnText.length() > 200) {
			    errorMsgs.add("退貨描述: 不可超過100字");
			}

			// ----------- 封裝 OrdersVO -------------
			OrdersVO ordersVO = new OrdersVO();
			ordersVO.setMemberId(memberId);
			ordersVO.setAmount(amount);
			ordersVO.setDiscount(discount);
			ordersVO.setPayment(payment);
			ordersVO.setOrderStatus(orderStatus);
			ordersVO.setReceivingName(receivingName);
			ordersVO.setReceivingAddress(receivingAddress);
			ordersVO.setReceivingPhone(receivingPhone);
			ordersVO.setPromotionId(promotionId);
			ordersVO.setCouponId(couponId);
			ordersVO.setPaymentMethod(paymentMethod);
			ordersVO.setPaymentStatus(paymentStatus);
			ordersVO.setReturnReason(returnReason);
			ordersVO.setReturnText(returnText);
			
			LocalDateTime now = LocalDateTime.now();
			Timestamp ts = Timestamp.valueOf(now);
			ordersVO.setOrderCreateTime(ts);
			
			if (!errorMsgs.isEmpty()) {
			    req.setAttribute("errorMsgs", errorMsgs);
			    req.setAttribute("ordersVO", ordersVO); // 保留輸入值
			    RequestDispatcher failureView = req.getRequestDispatcher("/orders/addOrder.jsp");
			    failureView.forward(req, res);
			    return;
			}
			
			OrdersService ordersSvc = new OrdersService();
			ordersVO = ordersSvc.addOrder(memberId, amount, discount, payment, orderStatus, receivingName,
					receivingAddress, receivingPhone, promotionId, couponId, paymentMethod, paymentStatus, returnReason,
					returnText, null, null, ts, null, null, null, null
				);
			
			String url = "/orders/listAllOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url); // 新增成功後轉交listAllOrder.jsp
			successView.forward(req, res);
		}
		if ("delete".equals(action)) { // 來自listAllOrder.jsp

			List<String> errorMsgs = new LinkedList<String>();
			// Store this set in the request scope, in case we need to
			// send the ErrorPage view.
			req.setAttribute("errorMsgs", errorMsgs);
			// 接收請求參數
			Integer ordersId = Integer.valueOf(req.getParameter("orderId"));
			// 刪除資料
			OrdersService ordersSvc = new OrdersService();
			ordersSvc.delete(ordersId);
			// 刪除完成,準備轉交
			String url = "/orders/listAllOrder.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);// 刪除成功後,轉交回送出刪除的來源網頁
			successView.forward(req, res);
		}
	}
}
