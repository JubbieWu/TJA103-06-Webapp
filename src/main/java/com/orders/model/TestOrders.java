package com.orders.model;

import java.util.List;
import java.util.Scanner;

public class TestOrders {
	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("輸入訂單編號");
//		int no = sc.nextInt();		
//		
//		// 對資料庫操作，一律透過 DAO 物件的方法進行
//		OrdersDAO dao = new OrdersDAO();
//		OrdersVO ordersVO = dao.getByOrderID(no);
//		System.out.println(ordersVO);
		
		
		OrdersDAO dao = new OrdersDAO();
		List<OrdersVO> ordersVOList = dao.getAll();
		for (OrdersVO o : ordersVOList) {
			System.out.println(o);
		}
	}

}
