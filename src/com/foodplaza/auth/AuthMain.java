package com.foodplaza.auth;

/*
 * public interface FoodDao {
    boolean addFood(Food f);               // Admin
    boolean updateFood(Food f);            // Admin
    boolean deleteFood(int foodId);        // Admin
    Food displayFoodById(int foodId);      // Admin
    Food displayFoodByName(String name);   // Customer + Admin
    List<Food> displayAllFood();           // Customer + Admin
}
public interface CustomerDao {
    boolean addCustomer(Customer c);              // Customer (SignUp)
    boolean updateCustomer(Customer c);           // Customer (Update Profile)
    boolean deleteCustomer(int customerId);       // Admin
    Customer displayCustomerById(int customerId); // Admin
    Customer displayCustomerByName(String name);  // Admin
    List<Customer> displayAllCustomer();          // Admin
}
public interface CartDao {
    boolean addToCart(Cart c);                  // Customer
    boolean updateCart(Cart c);                 // Customer
    boolean deleteCart(int cartId);             // Customer
    Cart displayCartById(int cartId);           // Customer + Admin (support)
    List<Cart> displayCartByEmailId(String email); // Customer + Admin
    List<Cart> displayAllCart();                // Admin
}
public interface OrderDao {
    boolean placeOrder(Orders o);                 // Customer
    boolean updateOrderStatus(Orders o);          // Admin
    boolean deleteOrder(int orderId);             // Customer (cancel) + Admin
    Orders displayOrderById(int orderId);         // Customer (self) + Admin
    Orders displayOrderByEmail(String email);     // Customer (self) + Admin
    List<Orders> displayAllOrders();              // Admin
}


 * */


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.daoimpl.FoodDaoImpl;
import com.foodplaza.daoimpl.OrdersDaoImpl;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Food;
import com.foodplaza.pojo.Orders;
import com.foodplaza.utility.DBConnection;

public class AuthMain {

	public static void main(String[] args) {
		
		int choice, moduleChoice;
		String adminEmail, adminPassword;
		
		int foodId;
		String foodName,foodCategory,foodType;
		double foodPrice;
		boolean flag;
		
		int CustomerId;
		String name;
		
		int foodQty, cartId;
		String emailId, customerEmail;
		
		String email, orderStatus;
		int option, orderId;
		String orderDate = new Date().toString();
		
		String checkAdminEmailQuery, checkAdminPasswordQuery, checkCustomerEmailQuery, checkCustomerPasswordQuery;
		Scanner sc= new Scanner(System.in);
		
		FoodDaoImpl fdi = new FoodDaoImpl();
		Food f = new Food();
		
		Customer c= new Customer();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		
		Cart ct = new Cart();
		CartDaoImpl ctdi = new CartDaoImpl();
		
		Orders o = new Orders();
		OrdersDaoImpl odi =  new OrdersDaoImpl();
		Connection conn;
		PreparedStatement ps;
		ResultSet rs;
		
		checkAdminEmailQuery= "Select * from admin where adminEmail= ?";
		checkAdminPasswordQuery= "Select * from admin where adminEmail = ? and adminPassword =?";
		checkCustomerEmailQuery="Select * from customer where email = ?";
		checkCustomerPasswordQuery="Select * from customer where email = ? and password =?";
		
		System.out.println(".....WELCOME TO FOODPLAZA......");
		
		System.out.println("1.Admin login\n2.Customer login\n3.Exit");
		System.out.println("Enter your choice:");
		choice = sc.nextInt();
		switch(choice)
		{
		case 1:
			
			try {
				conn= DBConnection.getConnection();
				
				System.out.println("Enter your email id:");
				adminEmail= sc.next();
				ps = conn.prepareStatement(checkAdminEmailQuery);
				ps.setString(1, adminEmail);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					System.out.println("Enter your password : ");
					adminPassword = sc.next();
					ps = conn.prepareStatement(checkAdminPasswordQuery);
					ps.setString(1, adminEmail);
					ps.setString(2, adminPassword);
					
					rs = ps.executeQuery();
					
					if(rs.next()) {
						System.out.println("1.FOOD");
						System.out.println("2.CUSTOMER");
						System.out.println("3.CART");
						System.out.println("4.ORDER\n5.Exit");
						System.out.println("choose module:");
						moduleChoice=sc.nextInt();
						switch(moduleChoice) {
						case 1:
							//food....
							do
							{
								System.out.println("1.Add Food\n2.Update Food\n3.Delete Food\n4.Display Food by Id\n5.Display Food by Name\n6.Display Food by Range\n7.Display All Food\n8.Exit\nEnter your choice:");
								
								choice =  sc.nextInt();
								switch(choice)
								{
								case 1:
									System.out.println("Enter Food Name:");
									foodName =  sc.next();
									
									System.out.println("Enter Food Category:");
									foodCategory =  sc.next();
									
									System.out.println("Enter Food Price:");
									foodPrice =  sc.nextDouble();
									
									System.out.println("Enter Food Type:");
									foodType = sc.next();
									
									f.setFoodName(foodName);
									f.setFoodPrice(foodPrice);
									f.setFoodCategory(foodCategory);
									f.setFoodType(foodType);
									
									flag = fdi.addFood(f);
									if(flag == true)
									{
										System.out.println("Food added successfully");
									}
									else
									{
										System.out.println("Something went wrong");
									}
									
									break;
								case 2:
									System.out.println("Enter food id: ");
									foodId = sc.nextInt();
									
									Food existingFood = fdi.displayFoodById(foodId);
									//System.out.println(existingFood);
									if (existingFood.getFoodName() == null) {
								        System.out.println("ID not found");
								        break;
								    }
									
									existingFood.setFoodId(foodId); 
									int updatechoice;
									do {
										System.out.println("1.update name\n2.update price\n3.update Category\n4.update type\n5.Save changes and Exit");
										System.out.println("Enter choice: ");
										updatechoice = sc.nextInt();
										switch(updatechoice) {
										case 1:
											System.out.println("Enter name to update:");
											foodName =  sc.next();
											existingFood.setFoodName(foodName);
											break;
										case 2:
											System.out.println("Enter Food Price:");
											foodPrice =  sc.nextDouble();
											existingFood.setFoodPrice(foodPrice);
											break;
										case 3:
											System.out.println("Enter Food Category:");
											foodCategory =  sc.next();
											existingFood.setFoodCategory(foodCategory);
											break;
										case 4:
											System.out.println("Enter Food Type:");
											foodType = sc.next();
											existingFood.setFoodType(foodType);
											break;
										case 5:
											if(fdi.updateFood(existingFood)) {
												System.out.println("Food updated");
											}
											else{
												System.out.println("Update Failed!!");
											}
											break;
										default:
											System.out.println("wrong choice");
											break;
											
										}
									}while(updatechoice!=5);
										
									break;
								case 3:
									System.out.println("Enter food id: ");
									foodId = sc.nextInt();
									f.setFoodId(foodId);
									if(fdi.deleteFood(foodId)) {
										System.out.println("Food deleted successfully");
									}
									else
									{
										System.out.println("Something went wrong");
									}
									break;
								case 4:
									System.out.println("Enter Food Id to display food details:");
									foodId = sc.nextInt();
									
									f = fdi.displayFoodById(foodId);
									
//									System.out.println(f);
//									if(f!=null)
									
									if(f.getFoodName() != null)
									{
										System.out.println(f.getFoodName() + " " + f.getFoodType() + " " + f.getFoodCategory() + " " + f.getFoodPrice());
									}
									else
									{
										System.out.println("Food id not found");
									}
									
									break;
								case 5:
									System.out.println("Enter Food Name:");
									foodName =  sc.next();
									f = fdi.displayFoodByName(foodName);
									if(f.getFoodType()!= null) {
										System.out.println(f.getFoodName() + " " + f.getFoodType() + " " + f.getFoodCategory() + " " + f.getFoodPrice());
									}
									else {
										System.out.println("Food not found!");
									}
									break;
								case 6:
									System.out.println("Enter initial range:");
									int ir = sc.nextInt();
									System.out.println("Enter final range:");
									int fr = sc.nextInt();
									List<Food> food = fdi.displayFoodByRange(ir, fr);
									
									for(Food f1 : food)
									{
										System.out.println(f1.getFoodName() + " " + f1.getFoodType() + " " + f1.getFoodCategory() + " " + f1.getFoodPrice());
									}
									break;
									
								case 7:
									List<Food> foodList = fdi.displayAllFood();
									
									for(Food f1 : foodList)
									{
										System.out.println(f1.getFoodName() + " " + f1.getFoodType() + " " + f1.getFoodCategory() + " " + f1.getFoodPrice());
									}
									break;
								case 8:
									System.out.println("Thanku for visiting");
									break;
								default :
									System.out.println("Entered wrong choice!!!");
									break;
									
								}
							}while(choice<8);
							break;
						case 2:
							//costumer..
							System.out.println("1.Delete Customer\n2.Display Customer By Id\n3.Display Customer By Name\n4.Display All Customer\n5.Exit");
							System.out.println("Enter your choice:");
							System.out.println("");
							choice = sc.nextInt();
							switch(choice) {
							case 1:
								System.out.println("Enter Customer id: ");
								CustomerId = sc.nextInt();
								c.setCustomerId(CustomerId);
								if(cdi.deleteCustomer(CustomerId)) {
									System.out.println("Customer deleted successfully");
								}
								else
								{
									System.out.println("Something went wrong");
								}
								break;
							case 2:
								System.out.println("Enter Customer Id:");
								CustomerId= sc.nextInt();
								c= cdi.displayCustomerById(CustomerId);
								if(c.getCustomerName() != null) {
									System.out.println(c.getCustomerId()+" "+c.getCustomerName()+" "+c.getCustomerEmail()+" "+c.getCustomerContact()+" "+c.getCustomerAddress()+" "+c.getCustomerPassword());
								}
								else
								{
									System.out.println("Id not found");
								}
								break;
							case 3:
								System.out.println("Enter customer Name:");
								name =  sc.next();
								c = cdi.displayCustomerByName(name);
								if(c.getCustomerName()!= null) {
									System.out.println(c.getCustomerId()+" "+c.getCustomerName()+" "+c.getCustomerEmail()+" "+c.getCustomerContact()+" "+c.getCustomerAddress()+" "+c.getCustomerPassword());
								}
								else {
									System.out.println("Customer not found!");
								}
								break;
							case 4:
								List<Customer> customerList = cdi.displayAllCustomer();
								
								for(Customer c1 : customerList)
								{
									System.out.println(c1.getCustomerId()+" "+c1.getCustomerName()+" "+c1.getCustomerEmail()+" "+c1.getCustomerContact()+" "+c1.getCustomerAddress()+" "+c1.getCustomerPassword());
								}
								break;
							case 5:
								System.out.println("Thanku");
								break;
							default:
								System.out.println("Enter correct choice!");
								break;
							}while(choice<7);
							break;
						case 3:
							//cart...
							
							do {
								System.out.println("1..Display cart by Id\n2.Display cart by EmailId\n3.Display All\n3.Exit");
								System.out.println("Enter your choice: ");
								choice = sc.nextInt();
								switch(choice)
								{
								case 1:
									System.out.println("Enter your emailId:");
									emailId=sc.next();
									c = cdi.displayCustomerByEmail(emailId);
									
									//System.out.println(c.getCustomerEmail());
									if(c.getCustomerEmail() != null)
									{
										
										System.out.println("Enter your foodName:");
										sc.nextLine(); 
										foodName = sc.nextLine();
										
										f = fdi.displayFoodByName(foodName);
										
										System.out.println(f.getFoodId() + " " + f.getFoodPrice());
										if(f.getFoodName() != null)
										{
											System.out.println("Enter your Food Quantity:");
											
											foodQty=sc.nextInt();
											
											ct.setCustomerEmailId(emailId);
											ct.setFoodName(foodName);
											ct.setFoodQuantity(foodQty);
											ct.setFoodPrice(f.getFoodPrice());
											ct.setFoodId(f.getFoodId());
											
											flag=ctdi.addToCart(ct);
											if(flag==true) {
												System.out.println("Cart Added");
											}
											else {
												System.out.println("Cart not added!!");
											}
										}
										else
										{
											System.out.println("Food sold out");
										}
									}
									else
									{
										System.out.println("Kindly register first");
									}
									break;
									
								case 2:
									System.out.println("Enter your emailId:");
									customerEmail= sc.next();
									Cart existingCart = ctdi.displayCartByEmail(customerEmail);
									if (existingCart.getCustomerEmailId() == null) {
								        System.out.println("Cart not found");
								        break;
								    }
									else {
										System.out.println("Enter Quantity to change: ");
										foodQty = sc.nextInt();
										existingCart.setFoodQuantity(foodQty);
										if(ctdi.updateCart(existingCart)) {
											System.out.println("Cart updated");
										}
										else{
											System.out.println("Update Failed!!");
										}
									}
									break;
								case 3:
									System.out.println("Enter cart id:");
									cartId = sc.nextInt();
									flag= ctdi.deleteCart(cartId);
									if(flag) {
										System.out.println("Cart deleted");
									}
									else {
										System.out.println("Cart not found");
									}
									break;
								case 4:
									System.out.println("Enter Cart Id:");
									cartId= sc.nextInt();
									ct= ctdi.displaycartById(cartId);
									if(ct.getCustomerEmailId() != null) {
										System.out.println(ct.getCartId()+" "+ct.getCustomerEmailId()+" "+ct.getFoodName()+" "+ct.getFoodPrice()+" "+ct.getFoodQuantity());
									}
									else
									{
										System.out.println("Id not found");
									}
									break;
								case 5:
									System.out.println("Enter customer Email:");
									customerEmail= sc.next();
									ct = ctdi.displayCartByEmail(customerEmail);
									if(ct.getCustomerEmailId() != null) {
										System.out.println(ct.getCartId()+" "+ct.getCustomerEmailId()+" "+ct.getFoodName()+" "+ct.getFoodPrice()+" "+ct.getFoodQuantity());
									}
									else {
										System.out.println("Customer not found!!");
									}
									break;
								case 6:
									List<Cart> cartList = ctdi.displayAllCart();
									
									for(Cart ct1 : cartList)
									{
										System.out.println(ct1.getCartId()+" "+ct1.getCustomerEmailId()+" "+ct1.getFoodName()+" "+ct1.getFoodPrice()+" "+ct1.getFoodQuantity());
									}
									break;
								case 7:
									System.out.println("Thanku");
									break;
								default:
									System.out.println("Wrong choice");
									break;
								}
								}while(choice<7);
							break;
						case 4:
							//order..
							
							do {
								
								System.out.println("1.Update Order\n2.Delete Order\n3.Display Order By Id\n4.Display Order By Customer EmailId\n5.Display All Orders\n6.Exit\nEnter your Choice");
								option = sc.nextInt();
								switch(option)
								{
								case 1:
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
								case 2:
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
								case 3:
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
								case 4:
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
								case 5:
									List<Orders> orderList = odi.displayAllOrder();
									
									for(Orders o1 : orderList)
									{
										System.out.println(o1.getOrderId()+"  "+o1.getCustomerEmail()+"  "+o1.getFoodName()+"         "+o1.getTotalBill()+"  "+o1.getOrderDate()+"  "+o1.getOrderStatus());
									}
									break;
								case 6:
									System.out.println("Thanku");
									break;
								default:
									System.out.println("Wrong Choice");
									break;
								}
							}while(option<6);
							break;
						case 5:
							//exit
							break;
						}

					} else {
						System.out.println("Wrong password \n Login Failed..");
					}
				} else {
					System.out.println("Wrong email id\n Login Failed..");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case 2:
			//costumer
			try {
				conn= DBConnection.getConnection();
				
				System.out.println("Enter emailId : ");
				email = sc.next();
				ps = conn.prepareStatement(checkCustomerEmailQuery);
				ps.setString(1, email);
				
				rs = ps.executeQuery();
				
				if(rs.next()) {
					System.out.println("Enter your password : ");
					String CosPassword = sc.next();
					ps = conn.prepareStatement(checkCustomerPasswordQuery);
					ps.setString(1, email);
					ps.setString(2, CosPassword);
					
					rs = ps.executeQuery();
					
					
					if(rs.next()) {
						System.out.println("1.FOOD");
						System.out.println("2.CUSTOMER");
						System.out.println("3.CART");
						System.out.println("4.ORDER\n5.Exit");
						System.out.println("choose module:");
						moduleChoice=sc.nextInt();
						switch(moduleChoice) {
						case 1:
							//food....
							
							do
							{
								System.out.println("1.Display Food by Name\n2.Display Food by Range\n3.Display All Food\n4.Exit\nEnter your choice:");
								
								choice =  sc.nextInt();
								switch(choice)
								{
								case 1:
									System.out.println("Enter Food Name:");
									foodName =  sc.next();
									f = fdi.displayFoodByName(foodName);
									if(f.getFoodType()!= null) {
										System.out.println(f.getFoodName() + " " + f.getFoodType() + " " + f.getFoodCategory() + " " + f.getFoodPrice());
									}
									else {
										System.out.println("Food not found!");
									}
									break;
								case 2:
									System.out.println("Enter initial range:");
									int ir = sc.nextInt();
									System.out.println("Enter final range:");
									int fr = sc.nextInt();
									List<Food> food = fdi.displayFoodByRange(ir, fr);
									
									for(Food f1 : food)
									{
										System.out.println(f1.getFoodName() + " " + f1.getFoodType() + " " + f1.getFoodCategory() + " " + f1.getFoodPrice());
									}
									break;
									
								case 3:
									List<Food> foodList = fdi.displayAllFood();
									
									for(Food f1 : foodList)
									{
										System.out.println(f1.getFoodName() + " " + f1.getFoodType() + " " + f1.getFoodCategory() + " " + f1.getFoodPrice());
									}
									break;
								case 4:
									System.out.println("Thanku for visiting");
									break;
								default :
									System.out.println("Entered wrong choice!!!");
									break;
									
								}
							}while(choice<4);
							
							break;
						case 2:
							//costumer..
							
							do {
								System.out.println("1.SignUp\n2.Update Profile\n3.Exit");
								System.out.println("Enter your choice:");
								choice = sc.nextInt();
								long contact;
								String address;
								String password;
								switch(choice) {
								case 1:
									System.out.println("Enter your name:");
									name= sc.next();
									System.out.println("Enter your EmailId:");
									email= sc.next();
									System.out.println("Enter your Contact number:");
									contact = sc.nextLong();
									System.out.println("Enter your Address:");
									address = sc.next();
									System.out.println("Enter your Password:");
									password = sc.next();
									
									c.setCustomerName(name);
									c.setCustomerEmail(email);
									c.setCustomerContact(contact);
									c.setCustomerAddress(address);
									c.setCustomerPassword(password);
									
									if(flag=cdi.addCustomer(c)) {
										System.out.println("Customer Added");
									}
									else {
										System.out.println("Customer not added");
									}
									
									break;
								case 2:
									System.out.println("Enter Customer id: ");
									CustomerId = sc.nextInt();
									
									Customer existingcustomer = cdi.displayCustomerById(CustomerId);
									//System.out.println(existingcustomer);
									if (existingcustomer.getCustomerName() == null) {
								        System.out.println("ID not found");
								        break;
								    }
									
									existingcustomer.setCustomerId(CustomerId); 
									int updatechoice;
									do {
										System.out.println("1.update name\n2.update email\n3.update Contact number\n4.update address\n5.update password\n6.Save changes and Exit");
										System.out.println("Enter choice: ");
										updatechoice = sc.nextInt();
										switch(updatechoice) {
										case 1:
											System.out.println("Enter name to update:");
											name =  sc.next();
											existingcustomer.setCustomerName(name);
											break;
										case 2:
											System.out.println("Enter email:");
											email = sc.next();
											existingcustomer.setCustomerEmail(email);
											break;
										case 3:
											System.out.println("Enter Contact number:");
											contact =  sc.nextLong();
											existingcustomer.setCustomerContact(contact);
											break;
										case 4:
											System.out.println("Enter address :");
											address = sc.next();
											existingcustomer.setCustomerAddress(address);
											break;
										case 5:
											System.out.println("Enter new password:");
											password = sc.next();
											existingcustomer.setCustomerPassword(password);
										case 6:
											if(cdi.updateCustomer(existingcustomer)) {
												System.out.println("Customer updated");
											}
											else{
												System.out.println("Update Failed!!");
											}
											break;
										default:
											System.out.println("wrong choice");
											break;
											
										}
									}while(updatechoice!=6);
										
									break;
								
								case 3:
									System.out.println("Thanku");
									break;
								default:
									System.out.println("Enter correct choice!");
									break;
								}
								
							}while(choice<3);
							
							break;
						case 3:
							//cart...
							
							do {
								System.out.println("1. Add to Cart\n2.Update cart\n3.Delete cart\n4.Display cart by Id\n5.Display cart by EmailId\n6.Exit");
								System.out.println("Enter your choice: ");
								choice = sc.nextInt();
								switch(choice)
								{
								case 1:
									System.out.println("Enter your emailId:");
									emailId=sc.next();
									c = cdi.displayCustomerByEmail(emailId);
									
									//System.out.println(c.getCustomerEmail());
									if(c.getCustomerEmail() != null)
									{
										
										System.out.println("Enter your foodName:");
										sc.nextLine(); 
										foodName = sc.nextLine();
										
										f = fdi.displayFoodByName(foodName);
										
										System.out.println(f.getFoodId() + " " + f.getFoodPrice());
										if(f.getFoodName() != null)
										{
											System.out.println("Enter your Food Quantity:");
											
											foodQty=sc.nextInt();
											
											ct.setCustomerEmailId(emailId);
											ct.setFoodName(foodName);
											ct.setFoodQuantity(foodQty);
											ct.setFoodPrice(f.getFoodPrice());
											ct.setFoodId(f.getFoodId());
											
											flag=ctdi.addToCart(ct);
											if(flag==true) {
												System.out.println("Cart Added");
											}
											else {
												System.out.println("Cart not added!!");
											}
										}
										else
										{
											System.out.println("Food sold out");
										}
									}
									else
									{
										System.out.println("Kindly register first");
									}
									break;
									
								case 2:
									System.out.println("Enter your emailId:");
									customerEmail= sc.next();
									Cart existingCart = ctdi.displayCartByEmail(customerEmail);
									if (existingCart.getCustomerEmailId() == null) {
								        System.out.println("Cart not found");
								        break;
								    }
									else {
										System.out.println("Enter Quantity to change: ");
										foodQty = sc.nextInt();
										existingCart.setFoodQuantity(foodQty);
										if(ctdi.updateCart(existingCart)) {
											System.out.println("Cart updated");
										}
										else{
											System.out.println("Update Failed!!");
										}
									}
									break;
								case 3:
									System.out.println("Enter cart id:");
									cartId = sc.nextInt();
									flag= ctdi.deleteCart(cartId);
									if(flag) {
										System.out.println("Cart deleted");
									}
									else {
										System.out.println("Cart not found");
									}
									break;
								case 4:
									System.out.println("Enter Cart Id:");
									cartId= sc.nextInt();
									ct= ctdi.displaycartById(cartId);
									if(ct.getCustomerEmailId() != null) {
										System.out.println(ct.getCartId()+" "+ct.getCustomerEmailId()+" "+ct.getFoodName()+" "+ct.getFoodPrice()+" "+ct.getFoodQuantity());
									}
									else
									{
										System.out.println("Id not found");
									}
									break;
								case 5:
									System.out.println("Enter customer Email:");
									customerEmail= sc.next();
									ct = ctdi.displayCartByEmail(customerEmail);
									if(ct.getCustomerEmailId() != null) {
										System.out.println(ct.getCartId()+" "+ct.getCustomerEmailId()+" "+ct.getFoodName()+" "+ct.getFoodPrice()+" "+ct.getFoodQuantity());
									}
									else {
										System.out.println("Customer not found!!");
									}
									break;
								case 6:
									System.out.println("Thanku");
									break;
								default:
									System.out.println("Wrong choice");
									break;
								}
								}while(choice<6);
							break;
						case 4:
							//order..
							
							do {
								
								System.out.println("1.Place Order\n2.Cancel Order\n3.Display Order By Id\n4.Display Order By Customer EmailId\n5.Exit\nEnter your Choice");
								option = sc.nextInt();
								switch(option)
								{
								case 1:
									System.out.println("Enter your emailId:");
									email = sc.next();
									
									ct = ctdi.displayCartByEmail(email);
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
								case 3:
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
								case 4:
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
								case 5:
									System.out.println("Thanku");
									break;
								default:
									System.out.println("Wrong Choice");
									break;
										
								}
							}while(option<5);
							break;
						case 5:
							//exit
							break;
						}

					}
					else {
						System.out.println("Wrong Password!");
					}
				}
				else {
					System.out.println("You entered wrong emailId !!");
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
			break;
		case 3:
			System.out.println("Thank you");
			break;
		default:
			System.out.println("Wrong choice!!");
			break;
				
		}
	}
	
}
	
