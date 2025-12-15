package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.CustomerDao;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;

public class CustomerDaoImpl implements CustomerDao{

	Connection conn;
	Statement st;
	PreparedStatement ps;
	
	String addCostumerQuery, updateCustomerQuery, displayCustomerByIdQuery, deleteCustomerQuery, displayCustomerByNameQuery,displayAllCustomerQuery, displayCustomerByEmailQuery;
	Customer c = new Customer();
	ResultSet rs;
	int row;
	
	@Override
	public boolean addCustomer(Customer c) {
		addCostumerQuery = "insert into customer(customerName, customerEmail, customerContact, customerAddress, customerPassword)values(?, ?, ?, ?, ?)";
		try {
			conn = DBConnection.getConnection();
			ps= conn.prepareStatement(addCostumerQuery);
			ps.setString(1, c.getCustomerName());
			ps.setString(2, c.getCustomerEmail());
			ps.setLong(3, c.getCustomerContact());
			ps.setString(4, c.getCustomerAddress());
			ps.setString(5, c.getCustomerPassword());
			
			
			row = ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return false;
	}

	@Override
	public boolean updateCustomer(Customer c) {
		updateCustomerQuery = "update customer set CustomerName=?, customerEmail=?, customerContact=?, customerAddress=?, customerPassword=? where CustomerId=?";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(updateCustomerQuery);
			
			ps.setString(1, c.getCustomerName());
			ps.setString(2, c.getCustomerEmail());
			ps.setLong(3, c.getCustomerContact());
			ps.setString(4, c.getCustomerAddress());
			ps.setString(5, c.getCustomerPassword());
			ps.setInt(6, c.getCustomerId());
			
			row = ps.executeUpdate();
			if(row>0)
			{
				return true;
			}		
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(int CustomerId) {
		deleteCustomerQuery = "delete from Customer where CustomerId =?";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(deleteCustomerQuery);
			
			ps.setInt(1, CustomerId);
			
			row = ps.executeUpdate();
			if(row>0)
			{
				return true;
			}
				
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Customer displayCustomerById(int CustomerId) {
		displayCustomerByIdQuery = "select * from Customer where customerId = ?";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(displayCustomerByIdQuery);
			
			ps.setInt(1, CustomerId);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				c.setCustomerId(rs.getInt("customerId"));
				c.setCustomerName(rs.getString("customerName"));
				c.setCustomerEmail(rs.getString("customerEmail"));
				c.setCustomerContact(rs.getLong("customerContact"));
				c.setCustomerAddress(rs.getString("customerAddress"));
				c.setCustomerPassword(rs.getString("customerPassword"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public Customer displayCustomerByName(String CustomerName) {
		displayCustomerByNameQuery = "Select * from Customer where CustomerName =?";
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(displayCustomerByNameQuery);
			
			ps.setString(1, CustomerName);
			rs= ps.executeQuery();
			while(rs.next()) {
				
				c.setCustomerId(rs.getInt("customerId"));
				c.setCustomerName(rs.getString("customerName"));
				c.setCustomerEmail(rs.getString("customerEmail"));
				c.setCustomerContact(rs.getLong("customerContact"));
				c.setCustomerAddress(rs.getString("customerAddress"));
				c.setCustomerPassword(rs.getString("customerPassword"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}

	@Override
	public List<Customer> displayAllCustomer() {
		List<Customer> customerList = new ArrayList<Customer>();
		displayAllCustomerQuery = "select * from Customer";
		try {
			conn = DBConnection.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery(displayAllCustomerQuery);

			
			while(rs.next())
			{
				
				Customer c =  new Customer();
				c.setCustomerId(rs.getInt("CustomerId"));
				c.setCustomerName(rs.getString("CustomerName"));
				c.setCustomerEmail(rs.getString("CustomerEmail"));
				c.setCustomerContact(rs.getLong("customerContact"));
				c.setCustomerAddress(rs.getString("customerAddress"));
				c.setCustomerPassword(rs.getString("customerPassword"));
				
				customerList.add(c);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return customerList;
	}

	@Override
	public Customer displayCustomerByEmail(String CustomerEmail) {
		displayCustomerByEmailQuery = "Select * from Customer where CustomerEmail =?";
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(displayCustomerByEmailQuery);
			
			ps.setString(1, CustomerEmail);
			rs= ps.executeQuery();
			while(rs.next()) {
				
				c.setCustomerId(rs.getInt("customerId"));
				c.setCustomerName(rs.getString("customerName"));
				c.setCustomerEmail(rs.getString("customerEmail"));
				c.setCustomerContact(rs.getLong("customerContact"));
				c.setCustomerAddress(rs.getString("customerAddress"));
				c.setCustomerPassword(rs.getString("customerPassword"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return c;
	}
	

}
