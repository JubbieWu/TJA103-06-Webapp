package com.orders.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class OrdersVO implements Serializable{
	private Integer orderId;
	private Integer memberId;
	private Double amount;
	private Double discount;
	private Double payment;
	private String orderStatus;
	private String receivingName;
	private String receivingAddress;
	private String receivingPhone;
	private Integer promotionId;
	private Integer couponId;
	private String paymentMethod;
	private String paymentStatus;
	private String returnReason;
	private String returnText;
	private String returnStatus;
	private String refusalReason;
	private Timestamp orderCreateTime;
	private Timestamp orderUpdateTime;
	private Timestamp paymentCreateTime;
	private Timestamp returnCreateTime;
	private Timestamp returnApproveTime;
	
	public OrdersVO() {
		super();
	}

	public OrdersVO(Integer orderId, Integer memberId, Double amount, Double discount, Double payment,
			String orderStatus, String receivingName, String receivingAddress, String receivingPhone,
			Integer promotionId, Integer couponId, String paymentMethod, String paymentStatus, String returnReason,
			String returnText, String returnStatus, String refusalReason, Timestamp orderCreateTime,
			Timestamp orderUpdateTime, Timestamp paymentCreateTime, Timestamp returnCreateTime,
			Timestamp returnApproveTime) {
		super();
		this.orderId = orderId;
		this.memberId = memberId;
		this.amount = amount;
		this.discount = discount;
		this.payment = payment;
		this.orderStatus = orderStatus;
		this.receivingName = receivingName;
		this.receivingAddress = receivingAddress;
		this.receivingPhone = receivingPhone;
		this.promotionId = promotionId;
		this.couponId = couponId;
		this.paymentMethod = paymentMethod;
		this.paymentStatus = paymentStatus;
		this.returnReason = returnReason;
		this.returnText = returnText;
		this.returnStatus = returnStatus;
		this.refusalReason = refusalReason;
		this.orderCreateTime = orderCreateTime;
		this.orderUpdateTime = orderUpdateTime;
		this.paymentCreateTime = paymentCreateTime;
		this.returnCreateTime = returnCreateTime;
		this.returnApproveTime = returnApproveTime;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getPayment() {
		return payment;
	}

	public void setPayment(Double payment) {
		this.payment = payment;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getReceivingName() {
		return receivingName;
	}

	public void setReceivingName(String receivingName) {
		this.receivingName = receivingName;
	}

	public String getReceivingAddress() {
		return receivingAddress;
	}

	public void setReceivingAddress(String receivingAddress) {
		this.receivingAddress = receivingAddress;
	}

	public String getReceivingPhone() {
		return receivingPhone;
	}

	public void setReceivingPhone(String receivingPhone) {
		this.receivingPhone = receivingPhone;
	}

	public Integer getPromotionId() {
		return promotionId;
	}

	public void setPromotionId(Integer promotionId) {
		this.promotionId = promotionId;
	}

	public Integer getCouponId() {
		return couponId;
	}

	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public String getReturnReason() {
//		if (returnReason == null) {
//			returnReason = "無退貨";
//		}
		return returnReason;
	}

	public void setReturnReason(String returnReason) {
		this.returnReason = returnReason;
	}

	public String getReturnText() {
		return returnText;
	}

	public void setReturnText(String returnText) {
		this.returnText = returnText;
	}

	public String getReturnStatus() {
		return returnStatus;
	}

	public void setReturnStatus(String returnStatus) {
		this.returnStatus = returnStatus;
	}

	public String getRefusalReason() {
		return refusalReason;
	}

	public void setRefusalReason(String refusalReason) {
		this.refusalReason = refusalReason;
	}

	public Timestamp getOrderCreateTime() {
		return orderCreateTime;
	}

	public void setOrderCreateTime(Timestamp orderCreateTime) {
		this.orderCreateTime = orderCreateTime;
	}

	public Timestamp getOrderUpdateTime() {
		return orderUpdateTime;
	}

	public void setOrderUpdateTime(Timestamp orderUpdateTime) {
		this.orderUpdateTime = orderUpdateTime;
	}

	public Timestamp getPaymentCreateTime() {
		return paymentCreateTime;
	}

	public void setPaymentCreateTime(Timestamp paymentCreateTime) {
		this.paymentCreateTime = paymentCreateTime;
	}

	public Timestamp getReturnCreateTime() {
		return returnCreateTime;
	}

	public void setReturnCreateTime(Timestamp returnCreateTime) {
		this.returnCreateTime = returnCreateTime;
	}

	public Timestamp getReturnApproveTime() {
		return returnApproveTime;
	}

	public void setReturnApproveTime(Timestamp returnApproveTime) {
		this.returnApproveTime = returnApproveTime;
	}

	@Override
	public String toString() {
		return "OrdersVO [orderId=" + orderId + ", memberId=" + memberId + ", amount=" + amount + ", discount="
				+ discount + ", payment=" + payment + ", orderStatus=" + orderStatus + ", receivingName="
				+ receivingName + ", receivingAddress=" + receivingAddress + ", receivingPhone=" + receivingPhone
				+ ", promotionId=" + promotionId + ", couponId=" + couponId + ", paymentMethod=" + paymentMethod
				+ ", paymentStatus=" + paymentStatus + ", returnReason=" + returnReason + ", returnText=" + returnText
				+ ", returnStatus=" + returnStatus + ", refusalReason=" + refusalReason + ", orderCreateTime="
				+ orderCreateTime + ", orderUpdateTime=" + orderUpdateTime + ", paymentCreateTime=" + paymentCreateTime
				+ ", returnCreateTime=" + returnCreateTime + ", returnApproveTime=" + returnApproveTime + "]";
	}
	
	
	
}

