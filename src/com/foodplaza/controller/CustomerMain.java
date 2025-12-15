package com.foodplaza.controller;

import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.CustomerDaoImpl;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Food;

public class CustomerMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice, CustomerId;
		String name,email, address, password;
		long contact;
		boolean flag;
		
		Customer c= new Customer();
		CustomerDaoImpl cdi = new CustomerDaoImpl();
		
		do {
			System.out.println("1.Add Customer\n2.Update Customer\n3.Delete Customer\n4.Display Customer By Id\n5.Display Customer By Name\n6.Display All Customer\n7.Exit");
			System.out.println("Enter your choice:");
			choice = sc.nextInt();
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
			case 4:
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
			case 5:
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
			case 6:
				List<Customer> customerList = cdi.displayAllCustomer();
				
				for(Customer c1 : customerList)
				{
					System.out.println(c1.getCustomerId()+" "+c1.getCustomerName()+" "+c1.getCustomerEmail()+" "+c1.getCustomerContact()+" "+c1.getCustomerAddress()+" "+c1.getCustomerPassword());
				}
				break;
			case 7:
				System.out.println("Thanku");
				break;
			default:
				System.out.println("Enter correct choice!");
				break;
			}
			
		}while(choice<7);
		
	}
}