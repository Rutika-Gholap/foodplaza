package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Customer;

public interface CustomerDao {
	boolean addCustomer(Customer c);
	boolean updateCustomer(Customer c);
	boolean deleteCustomer(int CustomerId);
	Customer displayCustomerById(int CustomerId);
	Customer displayCustomerByName(String CustomerName);
	Customer displayCustomerByEmail(String CustomerEmail);
	List<Customer> displayAllCustomer();

}