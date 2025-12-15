package com.foodplaza.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.OrdersDao;
import com.foodplaza.pojo.Customer;
import com.foodplaza.pojo.Orders;
import com.foodplaza.utility.DBConnection;
import com.mysql.cj.protocol.Resultset;

public class OrdersDaoImpl implements OrdersDao{

		Connection conn;
		PreparedStatement ps;
		Statement st;
		ResultSet rs;
		
		Orders o = new Orders();
		
		String placedOrderQuery, updateOrderQuery, deleteOrderQuery, displayOrderById, displayOrderByEmailQuery, displayAllOrderQuery;
		int row;
		
		@Override
		public boolean placeOrder(Orders o) {
			try {
				placedOrderQuery = "Insert into Orders(foodName,customerEmail,totalBill,orderStatus,orderDate)values(?,?,?,?,?)";
			
				conn= DBConnection.getConnection();
				ps = conn.prepareStatement(placedOrderQuery);
				ps.setString(1, o.getFoodName());
				ps.setString(2, o.getCustomerEmail());
				ps.setDouble(3,o.getTotalBill());
				ps.setString(4, o.getOrderStatus());
				ps.setString(5, o.getOrderDate());
			
				int row= ps.executeUpdate();
			
				if (row>0) {
				return true;
				}
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
			}


		@Override
		public boolean updateOrder(Orders o) {
			updateOrderQuery = "update orders set foodName=?, customerEmail=?, totalBill=?, orderStatus=?, orderDate=? where orderId=?";
				
			try {
				conn = DBConnection.getConnection();
				ps = conn.prepareStatement(updateOrderQuery);
				ps.setString(1, o.getFoodName());
				ps.setString(2, o.getCustomerEmail());
				ps.setDouble(3, o.getTotalBill());
				ps.setString(4, o.getOrderStatus());
				ps.setString(5, o.getOrderDate());
				ps.setInt(6, o.getOrderId());
				
				row = ps.executeUpdate();
				if(row>0) {
					return true;
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}


		@Override
		public boolean deleteOrder(int orderId) {
			deleteOrderQuery = "delete from orders where orderId=?";
			try {
				conn = DBConnection.getConnection();
				ps = conn.prepareStatement(deleteOrderQuery);
				ps.setInt(1, orderId);
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
		public Orders displayOrderById(int orderId) {
			displayOrderById = "select * from orders where orderId =?";
			try {
				conn = DBConnection.getConnection();
				ps= conn.prepareStatement(displayOrderById);
				ps.setInt(1, orderId);
				rs = ps.executeQuery();
				while(rs.next()) {
					o.setOrderId(rs.getInt("orderId"));
					o.setCustomerEmailId(rs.getString("customerEmail"));
					o.setFoodName(rs.getString("foodName"));
					o.setTotalBill(rs.getDouble("totalBill"));
					o.setOrderDate(rs.getString("orderDate"));
					o.setOrderStatus(rs.getString("orderStatus"));
					
				}
				
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return o;
		}


		@Override
		public Orders displayOrderByEmail(String customerEmail) {
			displayOrderByEmailQuery = "Select * from orders where customerEmail =?";
			
			try {
				conn = DBConnection.getConnection();
				ps = conn.prepareStatement(displayOrderByEmailQuery);
				
				ps.setString(1, customerEmail);
				rs= ps.executeQuery();
				while(rs.next()) {
					
					o.setOrderId(rs.getInt("orderId"));
					o.setCustomerEmailId(rs.getString("customerEmail"));
					o.setFoodName(rs.getString("foodName"));
					o.setTotalBill(rs.getDouble("totalBill"));
					o.setOrderDate(rs.getString("orderDate"));
					o.setOrderStatus(rs.getString("orderStatus"));
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return o;
		}


		@Override
		public List<Orders> displayAllOrder() {
			List<Orders> orderList = new ArrayList<Orders>();
			displayAllOrderQuery = "select * from Orders";
			try {
				conn = DBConnection.getConnection();
				
				st = conn.createStatement();
				
				rs = st.executeQuery(displayAllOrderQuery);

				
				while(rs.next())
				{
					
					Orders o =  new Orders();
					o.setOrderId(rs.getInt("orderId"));
					o.setCustomerEmailId(rs.getString("customerEmail"));
					o.setFoodName(rs.getString("foodName"));
					o.setTotalBill(rs.getDouble("totalBill"));
					o.setOrderDate(rs.getString("orderDate"));
					o.setOrderStatus(rs.getString("orderStatus"));
					
					
					orderList.add(o);
					
				}
			} catch (ClassNotFoundException | SQLException e) {
				
				e.printStackTrace();
			}
			
			return orderList;

		}

}

