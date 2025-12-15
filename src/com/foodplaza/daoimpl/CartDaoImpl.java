package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.CartDao;
import com.foodplaza.pojo.Cart;
import com.foodplaza.pojo.Customer;
import com.foodplaza.utility.DBConnection;

public class CartDaoImpl implements CartDao{
	
	int row;

	Connection conn;
	PreparedStatement ps;
	Statement st;
	ResultSet rs;
	Cart ct = new Cart();
	
	String insertCartQuery,deleteCartQuery, displayCartByIdQuery, displayCartByEmailQuery, displayAllCartQuery, updateCartQuery;
	
	@Override
	public boolean addToCart(Cart ct) {
		
		insertCartQuery = "insert into Cart(foodId, foodName, foodPrice, foodQty, customerEmail)values(?,?,?,?,?)";
	
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(insertCartQuery);
			
			ps.setInt(1, ct.getFoodId());
			ps.setString(2, ct.getFoodName());
			ps.setDouble(3, ct.getFoodPrice());
			ps.setInt(4, ct.getFoodQuantity());
			ps.setString(5, ct.getCustomerEmailId());
			
			int row = ps.executeUpdate();
			if(row >0) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCart(Cart ct) {
		updateCartQuery = "update cart set foodQty=?";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(updateCartQuery);
			
			ps.setInt(1, ct.getFoodQuantity());
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
	public boolean deleteCart(int cartId) {
		deleteCartQuery = "delete from cart where cartId = ?";
		try {
			conn= DBConnection.getConnection();
			ps= conn.prepareStatement(deleteCartQuery);
			ps.setInt(1, cartId);
			
			row = ps.executeUpdate();
			if(row>0) {
				return true;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public Cart displaycartById(int cartId) {
		displayCartByIdQuery = "select * from cart where cartid =?";
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(displayCartByIdQuery);
			ps.setInt(1, cartId);
			rs = ps.executeQuery();
			while(rs.next())
			{
				ct.setCartId(rs.getInt("cartId"));
				ct.setCustomerEmailId(rs.getString("customerEmail"));
				ct.setFoodId(rs.getInt("foodId"));
				ct.setFoodName(rs.getString("foodName"));
				ct.setFoodPrice(rs.getDouble("foodPrice"));
				ct.setFoodQuantity(rs.getInt("foodQty"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 
		}
		return ct;
	}

	@Override
	public Cart displayCartByEmail(String customerEmail) {
		displayCartByEmailQuery = "Select * from cart where customerEmail =?";
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(displayCartByEmailQuery);
			
			ps.setString(1, customerEmail);
			rs= ps.executeQuery();
			while(rs.next()) {
				
				ct.setCartId(rs.getInt("cartId"));
				ct.setCustomerEmailId(rs.getString("customerEmail"));
				ct.setFoodId(rs.getInt("foodId"));
				ct.setFoodName(rs.getString("foodName"));
				ct.setFoodPrice(rs.getDouble("foodPrice"));
				ct.setFoodQuantity(rs.getInt("foodQty"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ct;
	}

	@Override
	public List<Cart> displayAllCart() {
		List<Cart> cartList = new ArrayList<Cart>();
		displayAllCartQuery = "select * from cart";
		try {
			conn = DBConnection.getConnection();
			st = conn.createStatement();
			
			rs = st.executeQuery(displayAllCartQuery);

			while(rs.next())
			{
				Cart ct =  new Cart();
				ct.setCartId(rs.getInt("cartId"));
				ct.setCustomerEmailId(rs.getString("customerEmail"));
				ct.setFoodId(rs.getInt("foodId"));
				ct.setFoodName(rs.getString("foodName"));
				ct.setFoodPrice(rs.getDouble("foodPrice"));
				ct.setFoodQuantity(rs.getInt("foodQty"));
				
				cartList.add(ct);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return cartList;
	}


}