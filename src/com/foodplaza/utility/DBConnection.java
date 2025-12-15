package com.foodplaza.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	

	
	public static Connection getConnection() throws ClassNotFoundException, SQLException
	{
		String url,user,password;
		url = "jdbc:mysql://localhost:3306/foodplaza_rutika";
		user = "root";
		password = "root@";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection conn =  DriverManager.getConnection(url,user,password);
		
		return conn;
	}

}