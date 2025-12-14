package com.orders.model;

import java.util.List;

public interface OrdersDAO_interface {
	public void add(OrdersVO ordersVO);
	public void update(OrdersVO ordersVO);
	public void delete(Integer orderId);
	
	public OrdersVO getByOrderID(Integer orderId);
	public List<OrdersVO> getAll();
}
