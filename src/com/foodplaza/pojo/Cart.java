package com.foodplaza.pojo;

public class Cart {
	
	private int cartId,foodQuantity,foodId;
	private String customerEmailId, foodName;
	private double foodPrice;
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getFoodQuantity() {
		return foodQuantity;
	}
	public void setFoodQuantity(int foodQuantity) {
		this.foodQuantity = foodQuantity;
	}
	public int getFoodId() {
		return foodId;
	}
	public void setFoodId(int foodId) {
		this.foodId = foodId;
	}
	public String getCustomerEmailId() {
		return customerEmailId;
	}
	public void setCustomerEmailId(String customerEmailId) {
		this.customerEmailId = customerEmailId;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public double getFoodPrice() {
		return foodPrice;
	}
	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}
	
	
	

}