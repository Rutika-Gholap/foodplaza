package com.foodplaza.controller;
import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CartDaoImpl;
import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.daoimpl.FoodDaoImpl;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Food;
public class CartMain {
	
	public static void main(String[] args) {
		
		int choice,foodQty, cartId;
		Scanner sc = new Scanner(System.in);
		String emailId,foodName, customerEmail;
		boolean flag;
		Cart ct = new Cart();
		CartDaoImpl ctdi = new CartDaoImpl();
		FoodDaoImpl fdi =  new FoodDaoImpl();
		
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		do {
		System.out.println("1. Add to Cart\n2.Update cart\n3.Delete cart\n4.Display cart by Id\n5.Display cart by EmailId\n6.Display All\n7.Exit");
		System.out.println("Enter your choice: ");
		choice = sc.nextInt();
		switch(choice)
		{
		case 1:
			System.out.println("Enter your emailId:");
			emailId=sc.next();
			Customer c = cdi.displayCustomerByEmail(emailId);
			
			//System.out.println(c.getCustomerEmail());
			if(c.getCustomerEmail() != null)
			{
				
				System.out.println("Enter your foodName:");
				sc.nextLine(); 
				foodName = sc.nextLine();
				
				Food f = fdi.displayFoodByName(foodName);
				
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
	}

}