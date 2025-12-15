package com.foodplaza.controller;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.daoimpl.OrdersDaoImpl;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Orders;

public class OrdersMain {
public static void main(String[] args) {
	Scanner sc = new Scanner(System.in);
	 
//	Date d  = new Date();
//	String orderDate = d.toString();
	
	String orderDate = new Date().toString();
	
	OrdersDaoImpl odi =  new OrdersDaoImpl();
	CartDaoImpl ctdi = new CartDaoImpl();
	Orders o = new Orders();
	
	
	boolean flag;
	String email, orderStatus;
	int option, orderId;
	do {
		
		System.out.println("1.Place Order\n2.Update Order\n3.Delete Order\n4.Display Order By Id\n5.Display Order By Customer EmailId\n6.Display All Orders\n7.Exit\nEnter your Choice");
		option = sc.nextInt();
		switch(option)
		{
		case 1:
			System.out.println("Enter your emailId:");
			email = sc.next();
			
			Cart ct = ctdi.displayCartByEmail(email);
			if(	ct.getCustomerEmailId().equals(email))
			{
				o.setFoodName(ct.getFoodName());
				o.setCustomerEmailId(email);
				o.setOrderStatus("Pending");
				o.setOrderDate(orderDate);
				o.setTotalBill(ct.getFoodPrice() * ct.getFoodQuantity());
				
				flag =odi.placeOrder(o);
				if(flag) {
					System.out.println("Order placed sucessfully");
				}else {
					System.out.println("Something went wrong");
				}
			}
			else
			{
				System.out.println("Add Food to Cart First");
			}
			break;
		case 2:
			System.out.println("Enter order Id to update:");
			orderId = sc.nextInt();
			Orders existingOrder = odi.displayOrderById(orderId);
			System.out.println("Enter order status to update: ");
			orderStatus = sc.next();
			existingOrder.setOrderStatus(orderStatus);
			flag= odi.updateOrder(existingOrder);
			if(flag) {
				System.out.println("Order updated");
			}
			else {
				System.out.println("Something went wrong");
			}
			break;
		case 3:
			System.out.println("Enter order id to delete:");
			orderId = sc.nextInt();
			flag = odi.deleteOrder(orderId);
			if(flag) {
				System.out.println("order deleted succesfully");
			}
			else {
				System.out.println("Order not found");
			}
			break;
		case 4:
			System.out.println("Enter order Id:");
			orderId = sc.nextInt();
			o = odi.displayOrderById(orderId);
			if(o.getCustomerEmail()!= null) {
				System.out.println(o.getOrderId()+"  "+o.getCustomerEmail()+"  "+o.getFoodName()+"  "+o.getTotalBill()+"  "+o.getOrderDate()+"  "+o.getOrderStatus());
			}
			else {
				System.out.println("Order not found");
			}
			break;
		case 5:
			System.out.println("Enter customer EmailId:");
			email =  sc.next();
			o = odi.displayOrderByEmail(email);
			if(o.getCustomerEmail()!= null) {
				System.out.println(o.getOrderId()+"  "+o.getCustomerEmail()+"  "+o.getFoodName()+"  "+o.getTotalBill()+"  "+o.getOrderDate()+"  "+o.getOrderStatus());
			}
			else {
				System.out.println("Order not found!");
			}
			break;
		case 6:
			List<Orders> orderList = odi.displayAllOrder();
			
			for(Orders o1 : orderList)
			{
				System.out.println(o1.getOrderId()+"  "+o1.getCustomerEmail()+"  "+o1.getFoodName()+"         "+o1.getTotalBill()+"  "+o1.getOrderDate()+"  "+o1.getOrderStatus());
			}
			break;
		case 7:
			System.out.println("Thanku");
			break;
		default:
			System.out.println("Wrong Choice");
			break;
				
		}
	}while(option<7);
}
}