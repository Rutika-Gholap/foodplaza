package com.foodplaza.dao;

import java.util.List;

import com.foodplaza.pojo.Food;

public interface FoodDao {
	
	boolean addFood(Food f);
	
	boolean updateFood(Food f);
	
	boolean deleteFood(int foodId);
	
    Food displayFoodById(int foodId);
	
    Food displayFoodByName(String foodName);
	
	List<Food>displayAllFood();
	
	List<Food> displayFoodByRange(int ir,int fr);

//	boolean checkId(int foodId);
//	Food displayFood(int foodId);
//	Food displayFood(String foodName);
//  List<Food>displayFood();
//		

}