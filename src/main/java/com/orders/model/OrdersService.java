package com.orders.model;

import java.sql.Timestamp;
import java.util.List;
import java.time.LocalDateTime;

public class OrdersService {

	private OrdersDAO_interface dao;

	public OrdersService() {
		dao = new OrdersDAO();
	}

	public OrdersVO addOrder(Integer memberId, Double amount, Double discount, Double payment, String orderStatus,
			String receivingName, String receivingAddress, String receivingPhone, Integer promotionId, Integer couponId,
			String paymentMethod, String paymentStatus, String returnReason, String returnText, String returnStatus,
			String refusalReason, Timestamp orderCreateTime, Timestamp orderUpdateTime, Timestamp paymentCreateTime,
			Timestamp returnCreateTime, Timestamp returnApproveTime) {

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
		ordersVO.setReturnStatus(returnStatus);
		ordersVO.setRefusalReason(refusalReason);
		ordersVO.setOrderCreateTime(orderCreateTime);
		ordersVO.setOrderUpdateTime(orderUpdateTime);
		ordersVO.setPaymentCreateTime(paymentCreateTime);
		ordersVO.setReturnCreateTime(returnCreateTime);
		ordersVO.setReturnApproveTime(returnApproveTime);
		
		dao.add(ordersVO);

		return ordersVO;

	}

	public OrdersVO updateOrder(Integer memberId, Double amount, Double discount, Double payment, String orderStatus,
			String receivingName, String receivingAddress, String receivingPhone, Integer promotionId, Integer couponId,
			String paymentMethod, String paymentStatus, String returnReason, String returnText, String returnStatus,
			String refusalReason, Timestamp orderCreateTime, Timestamp orderUpdateTime, Timestamp paymentCreateTime,
			Timestamp returnCreateTime, Timestamp returnApproveTime) {

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
		ordersVO.setReturnStatus(returnStatus);
		ordersVO.setRefusalReason(refusalReason);
		ordersVO.setOrderCreateTime(orderCreateTime);
		ordersVO.setOrderUpdateTime(orderUpdateTime);
		ordersVO.setPaymentCreateTime(paymentCreateTime);
		ordersVO.setReturnCreateTime(returnCreateTime);
		ordersVO.setReturnApproveTime(returnApproveTime);
		dao.update(ordersVO);

		return ordersVO;
	}
	
	public OrdersVO updateReturnStatus(Integer orderId, ReturnStatus returnStatus, RefusalReason refusalReason) {
		LocalDateTime now = LocalDateTime.now();
		Timestamp ts = Timestamp.valueOf(now);
		
        OrdersVO ordersVO = new OrdersVO();

        ordersVO.setOrderId(orderId);

        // 存 VO 時用 label
        ordersVO.setReturnStatus(returnStatus.getLabel());

        if (returnStatus == ReturnStatus.REJECTED && refusalReason != null) {
            ordersVO.setRefusalReason(refusalReason.getLabel());
        }
        
        if (returnStatus == ReturnStatus.RETURNING) {
            // RETURNING 狀態，更新退貨創建時間與審核時間
            ordersVO.setReturnCreateTime(ts);
            ordersVO.setReturnApproveTime(ts);
        } else if (returnStatus == ReturnStatus.REJECTED) {
            // REJECTED 狀態，只更新審核時間
            ordersVO.setReturnApproveTime(ts);
        }


        dao.update(ordersVO);

        return ordersVO;
    }

	public void delete(Integer orderId) {
		dao.delete(orderId);
	}

	public OrdersVO getByOrderID(Integer orderId) {
		return dao.getByOrderID(orderId);
	}

	public List<OrdersVO> getAll() {
		return dao.getAll();
	}

}
