package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Orders;

public interface OrdersDao {

	boolean placeOrder(Orders o);
	
	boolean updateOrder(Orders o);
	
	boolean deleteOrder(int orderId);
	
	Orders displayOrderById(int orderId);
	
	Orders displayOrderByEmail(String customerEmail);
	
	List<Orders> displayAllOrder();
}
