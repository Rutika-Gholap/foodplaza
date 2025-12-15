package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Cart;


public interface CartDao {
	
	boolean addToCart(Cart ct);
	
	boolean updateCart(Cart ct);
	
	boolean deleteCart(int cartId);
	
    Cart displaycartById(int cartId);
	
    Cart displayCartByEmail(String customerEmail);
    
	List<Cart>displayAllCart();
	

}