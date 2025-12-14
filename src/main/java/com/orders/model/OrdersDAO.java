package com.orders.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrdersDAO implements OrdersDAO_interface {
	public static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String URL = "jdbc:mysql://localhost:3306/project06?serverTimezone=Asia/Taipei";
	public static final String USER = "root";
	public static final String PASSWORD = "123456";

	public static final String INSERT_STMT = "INSERT INTO orders (member_id, amount, discount, payment, order_status, receiving_name, receiving_address, receiving_phone, promotion_id, coupon_id, payment_method, payment_status, return_reason, return_text, return_status, refusal_reason, order_create_time, order_update_time, payment_create_time, return_create_time, return_approve_time) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String DELETE_STMT = "delete from orders where order_id = ?";
//	private static final String UPDATE_STMT = "update orders set return_status = ?, refusal_reason = ? , return_create_time = ?, return_approve_time = ? where order_id = ?";
	public static final String FIND_BY_ORDERID = "select * from orders where order_id = ?";
	public static final String GET_ALL = "select * from orders order by order_id desc";

	static {
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	@Override
	public void add(OrdersVO ordersVO) {

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(INSERT_STMT);) {
			pstmt.setInt(1, ordersVO.getMemberId());
			pstmt.setDouble(2, ordersVO.getAmount());
			pstmt.setDouble(3, ordersVO.getDiscount());
			pstmt.setDouble(4, ordersVO.getPayment());
			pstmt.setString(5, ordersVO.getOrderStatus());
			pstmt.setString(6, ordersVO.getReceivingName());
			pstmt.setString(7, ordersVO.getReceivingAddress());
			pstmt.setString(8, ordersVO.getReceivingPhone());
			pstmt.setObject(9, ordersVO.getPromotionId());
			pstmt.setObject(10, ordersVO.getCouponId());
			pstmt.setString(11, ordersVO.getPaymentMethod());
			pstmt.setString(12, ordersVO.getPaymentStatus());
			pstmt.setString(13, ordersVO.getReturnReason());
			pstmt.setString(14, ordersVO.getReturnText());
			pstmt.setString(15, ordersVO.getReturnStatus());
			pstmt.setString(16, ordersVO.getRefusalReason());
			pstmt.setTimestamp(17, ordersVO.getOrderCreateTime());
			pstmt.setTimestamp(18, ordersVO.getOrderUpdateTime());
			pstmt.setTimestamp(19, ordersVO.getPaymentCreateTime());
			pstmt.setTimestamp(20, ordersVO.getReturnCreateTime());
			pstmt.setTimestamp(21, ordersVO.getReturnApproveTime());

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@Override
	public void update(OrdersVO ordersVO) {

		StringBuilder sql = new StringBuilder("UPDATE orders SET return_status = ?");
		List<Object> params = new ArrayList<>();
		params.add(ordersVO.getReturnStatus());

		// 動態判斷是否要更新 refusal_reason
		if (ordersVO.getRefusalReason() != null) {
			sql.append(", refusal_reason = ?");
			params.add(ordersVO.getRefusalReason());
		}

		if (ordersVO.getReturnCreateTime() != null) {
			sql.append(", return_create_time = ?");
			params.add(ordersVO.getReturnCreateTime());
		}

		if (ordersVO.getReturnApproveTime() != null) {
			sql.append(", return_approve_time = ?");
			params.add(ordersVO.getReturnApproveTime());
		}

		sql.append(" WHERE order_id = ?");
		params.add(ordersVO.getOrderId());

		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(sql.toString())) {

			// 設定參數
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(i + 1, params.get(i));
			}

			pstmt.executeUpdate();

		} catch (SQLException se) {
			throw new RuntimeException("A database error occured. " + se.getMessage());
		}
	}

	@Override
	public void delete(Integer orderId) {
		try {
			Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
			PreparedStatement pstmt = con.prepareStatement(DELETE_STMT);

			pstmt.setInt(1, orderId);

			pstmt.executeUpdate();

		} catch (SQLException se) {
			se.printStackTrace();
		}

	}

	@Override
	public OrdersVO getByOrderID(Integer orderId) {
		OrdersVO ordersVO = null; // 宣告要回傳的資料
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(FIND_BY_ORDERID);) {
			pstmt.setInt(1, orderId);

			try (ResultSet rs = pstmt.executeQuery();) {

				while (rs.next()) { // 游標移動
					ordersVO = new OrdersVO();
					ordersVO.setOrderId(orderId);
					ordersVO.setMemberId(rs.getInt("member_id"));
					ordersVO.setAmount(rs.getDouble("amount"));
					ordersVO.setDiscount(rs.getDouble("discount"));
					ordersVO.setPayment(rs.getDouble("payment"));
					ordersVO.setOrderStatus(rs.getString("order_status"));
					ordersVO.setReceivingName(rs.getString("receiving_name")); // 把database的值取出後set到ordersVO裡
					ordersVO.setReceivingAddress(rs.getString("receiving_address"));
					ordersVO.setReceivingPhone(rs.getString("receiving_phone"));
					ordersVO.setPromotionId(rs.getInt("promotion_id"));
					ordersVO.setCouponId(rs.getInt("coupon_id"));
					ordersVO.setPaymentMethod(rs.getString("payment_method"));
					ordersVO.setPaymentStatus(rs.getString("payment_status"));
					ordersVO.setReturnReason(rs.getString("return_reason"));
					ordersVO.setReturnText(rs.getString("return_text"));
					ordersVO.setReturnStatus(rs.getString("return_status"));
					ordersVO.setRefusalReason(rs.getString("refusal_reason"));
					ordersVO.setOrderCreateTime(rs.getTimestamp("order_create_time"));
					ordersVO.setOrderUpdateTime(rs.getTimestamp("order_update_time"));
					ordersVO.setPaymentCreateTime(rs.getTimestamp("payment_create_time"));
					ordersVO.setReturnCreateTime(rs.getTimestamp("return_create_time"));
					ordersVO.setReturnApproveTime(rs.getTimestamp("return_approve_time"));
				}
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}

		return ordersVO;
	}

	@Override
	public List<OrdersVO> getAll() {
		List<OrdersVO> ordersVOList = new ArrayList<>();
		try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
				PreparedStatement pstmt = con.prepareStatement(GET_ALL);
				ResultSet rs = pstmt.executeQuery();) {

			while (rs.next()) { // 游標移動
				OrdersVO ordersVO = new OrdersVO();
				ordersVO.setOrderId(rs.getInt("order_id"));// 把database的值取出後set到ordersVO裡
				ordersVO.setMemberId(rs.getInt("member_id"));
				ordersVO.setAmount(rs.getDouble("amount"));
				ordersVO.setDiscount(rs.getDouble("discount"));
				ordersVO.setPayment(rs.getDouble("payment"));
				ordersVO.setOrderStatus(rs.getString("order_status"));
				ordersVO.setReceivingName(rs.getString("receiving_name"));
				ordersVO.setReceivingAddress(rs.getString("receiving_address"));
				ordersVO.setReceivingPhone(rs.getString("receiving_phone"));
				ordersVO.setPromotionId(rs.getInt("promotion_id"));
				ordersVO.setCouponId(rs.getInt("coupon_id"));
				ordersVO.setPaymentMethod(rs.getString("payment_method"));
				ordersVO.setPaymentStatus(rs.getString("payment_status"));
				ordersVO.setReturnReason(rs.getString("return_reason"));
				ordersVO.setReturnText(rs.getString("return_text"));
				ordersVO.setReturnStatus(rs.getString("return_status"));
				ordersVO.setRefusalReason(rs.getString("refusal_reason"));
				ordersVO.setOrderCreateTime(rs.getTimestamp("order_create_time"));
				ordersVO.setOrderUpdateTime(rs.getTimestamp("order_update_time"));
				ordersVO.setPaymentCreateTime(rs.getTimestamp("payment_create_time"));
				ordersVO.setReturnCreateTime(rs.getTimestamp("return_create_time"));
				ordersVO.setReturnApproveTime(rs.getTimestamp("return_approve_time"));

				ordersVOList.add(ordersVO);
			}

		} catch (SQLException se) {
			se.printStackTrace();
		}
		return ordersVOList;
	}

}
