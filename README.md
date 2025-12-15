# Food Plaza Management System

A **console-based Java application** for managing a food ordering system.  
It includes **Admin** and **Customer** modules with functionalities like managing food items, customers, carts, and orders. The project uses **Java, JDBC, and MySQL**.

---

## Table of Contents
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Database Setup](#database-setup)
- [How to Run](#how-to-run)
- [Author](#author)



## Features

### Admin
- Add, update, delete, and display food items
- View all customers and delete if needed
- View and manage all orders
- Update order status

### Customer
- Sign up and update profile
- View all food items
- Add items to cart
- Place or cancel orders
- View own orders

### Cart
- Add, update, delete items
- View cart by ID or email

### Orders
- Place orders from cart
- Update order status (Admin)
- Cancel orders
- View orders by ID or email


## Technologies Used
- Java (OOP concepts)
- JDBC for database connectivity
- MySQL
- Collections (ArrayList)
- Exception Handling
- Scanner for user input


## Database Setup

1. Install MySQL.
2. Create a database named `foodplaza_rutika`.
3. Create tables: `admin`, `customer`, `food`, `cart`, `orders`.
4. Update **DBConnection.java** with your MySQL credentials:

String url = "jdbc:mysql://localhost:3306/foodplaza_rutika";<br>
String user = "your-username";<br>
String password = "your-password";


## How to Run

1.Open the project in Eclipse.<br>
2.Ensure MySQL server is running and the database is ready.<br>
3.Right-click AuthMain.java → Run As → Java Application.<br>
4.Follow the console instructions to use Admin or Customer modules.

## Author

Rutika Gholap<br>
Student at Yadavrao Tasgaonkar Institute of Engineering and Technology<br>
Computer Engineering, 3rd Year
