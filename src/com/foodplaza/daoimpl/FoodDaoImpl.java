package com.foodplaza.daoimpl;
//data access object


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.foodplaza.dao.FoodDao;
import com.foodplaza.pojo.Food;
import com.foodplaza.utility.DBConnection;
import java.sql.Statement;

public class FoodDaoImpl implements FoodDao {

	
	Connection conn;
	PreparedStatement ps;
	Statement st;
	
	ResultSet rs;
	String insertFoodQuery,displayFoodByIdQuery,displayAllFoodQuery,updateFoodQuery,displayFoodByNameQuery, deleteFoodQuery, displayFoodByRangeQuery;
	Food f =  new Food();
	int row;

	
	@Override
	public boolean addFood(Food f) {
		
		insertFoodQuery = "insert into Food(foodName,foodPrice, foodCategory,foodType)values(?,?,?,?)";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(insertFoodQuery);
			
			ps.setString(1, f.getFoodName());
			ps.setDouble(2, f.getFoodPrice());
			ps.setString(3, f.getFoodCategory());
			ps.setString(4, f.getFoodType());
			
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
	public boolean updateFood(Food f) {
		updateFoodQuery = "update food set foodName=?, foodPrice=?, foodCategory=?, foodType=? where foodId=?";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(updateFoodQuery);
			
			ps.setString(1, f.getFoodName());
			ps.setDouble(2, f.getFoodPrice());
			ps.setString(3, f.getFoodCategory());
			ps.setString(4, f.getFoodType());
			ps.setInt(5, f.getFoodId());
			
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
	public boolean deleteFood(int foodId) {
		deleteFoodQuery = "delete from food where foodId =?";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(deleteFoodQuery);
			
			ps.setInt(1, foodId);
			
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
	public Food displayFoodById(int foodid) {
		
		displayFoodByIdQuery = "select * from Food where foodid = ?";
		
		try {
			conn = DBConnection.getConnection();
			
			ps = conn.prepareStatement(displayFoodByIdQuery);
			
			ps.setInt(1, foodid);
			
			rs = ps.executeQuery();
			
			while(rs.next())
			{
				
				f.setFoodName(rs.getString("foodName"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodType(rs.getString("foodType"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public Food displayFoodByName(String foodName) {
		displayFoodByNameQuery = "Select * from Food where foodName =?";
		
		try {
			conn = DBConnection.getConnection();
			ps = conn.prepareStatement(displayFoodByNameQuery);
			
			ps.setString(1, foodName);
			rs= ps.executeQuery();
			while(rs.next()) {
				f.setFoodId(rs.getInt("foodId"));
				f.setFoodName(rs.getString("foodName"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodType(rs.getString("foodType"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f;
	}

	@Override
	public List<Food> displayAllFood() {
		
		List<Food> foodList = new ArrayList<Food>();
		displayAllFoodQuery = "select * from Food";
		try {
			conn = DBConnection.getConnection();
			
			st = conn.createStatement();
			
			rs = st.executeQuery(displayAllFoodQuery);

			
			while(rs.next())
			{
				
				Food f =  new Food();
				f.setFoodName(rs.getString("foodName"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodType(rs.getString("foodType"));
				
				foodList.add(f);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return foodList;
	}
//	public boolean checkId(int foodId) {
//	
//	return false;
//	
//}

	@Override
	public List<Food> displayFoodByRange(int ir, int fr) {
		List<Food> foodList = new ArrayList<Food>();
		displayFoodByRangeQuery = "select * from food where foodPrice between ? and ?";
		try {
			conn = DBConnection.getConnection();
			ps= conn.prepareStatement(displayFoodByRangeQuery);
			ps.setInt(1, ir); 
	        ps.setInt(2, fr); 
	        rs= ps.executeQuery();

			
			while(rs.next())
			{
				
				Food f =  new Food();
				f.setFoodName(rs.getString("foodName"));
				f.setFoodCategory(rs.getString("foodCategory"));
				f.setFoodPrice(rs.getDouble("foodPrice"));
				f.setFoodType(rs.getString("foodType"));
				
				foodList.add(f);
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		
		return foodList;
	}

}
	


