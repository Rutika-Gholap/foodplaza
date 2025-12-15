package com.foodplaza.controller;

import java.util.List;
import java.util.Scanner;

import com.foodplaza.daoimpl.FoodDaoImpl;
import com.foodplaza.pojo.Food;

public class FoodMain {
	
	public static void main(String[] args) {
		
		int  choice;
		
		int foodId;
		String foodName,foodCategory,foodType;
		double foodPrice;
		boolean flag;
		
		Scanner sc = new Scanner(System.in);
		
		FoodDaoImpl fdi = new FoodDaoImpl();
		Food f = new Food();
		
		do
		{
			System.out.println("1.Add Food\n2.Update Food\n3.Delete Food\n4.Display Food by Id\n5.Display Food by Name\n6.Display Food by Range\n7.Display All Food\n8.Exit\nEnter your choice:");
			
			choice =  sc.nextInt();
			switch(choice)
			{
			case 1:
				System.out.println("Enter Food Name:");
				foodName =  sc.next();
				
				System.out.println("Enter Food Category:");
				foodCategory =  sc.next();
				
				System.out.println("Enter Food Price:");
				foodPrice =  sc.nextDouble();
				
				System.out.println("Enter Food Type:");
				foodType = sc.next();
				
				f.setFoodName(foodName);
				f.setFoodPrice(foodPrice);
				f.setFoodCategory(foodCategory);
				f.setFoodType(foodType);
				
				flag = fdi.addFood(f);
				if(flag == true)
				{
					System.out.println("Food added successfully");
				}
				else
				{
					System.out.println("Something went wrong");
				}
				
				break;
			case 2:
				System.out.println("Enter food id: ");
				foodId = sc.nextInt();
				
				Food existingFood = fdi.displayFoodById(foodId);
				//System.out.println(existingFood);
				if (existingFood.getFoodName() == null) {
			        System.out.println("ID not found");
			        break;
			    }
				
				existingFood.setFoodId(foodId); 
				int updatechoice;
				do {
					System.out.println("1.update name\n2.update price\n3.update Category\n4.update type\n5.Save changes and Exit");
					System.out.println("Enter choice: ");
					updatechoice = sc.nextInt();
					switch(updatechoice) {
					case 1:
						System.out.println("Enter name to update:");
						foodName =  sc.next();
						existingFood.setFoodName(foodName);
						break;
					case 2:
						System.out.println("Enter Food Price:");
						foodPrice =  sc.nextDouble();
						existingFood.setFoodPrice(foodPrice);
						break;
					case 3:
						System.out.println("Enter Food Category:");
						foodCategory =  sc.next();
						existingFood.setFoodCategory(foodCategory);
						break;
					case 4:
						System.out.println("Enter Food Type:");
						foodType = sc.next();
						existingFood.setFoodType(foodType);
						break;
					case 5:
						if(fdi.updateFood(existingFood)) {
							System.out.println("Food updated");
						}
						else{
							System.out.println("Update Failed!!");
						}
						break;
					default:
						System.out.println("wrong choice");
						break;
						
					}
				}while(updatechoice!=5);
					
				break;
			case 3:
				System.out.println("Enter food id: ");
				foodId = sc.nextInt();
				f.setFoodId(foodId);
				if(fdi.deleteFood(foodId)) {
					System.out.println("Food deleted successfully");
				}
				else
				{
					System.out.println("Something went wrong");
				}
				break;
			case 4:
				System.out.println("Enter Food Id to display food details:");
				foodId = sc.nextInt();
				
				f = fdi.displayFoodById(foodId);
				
//				System.out.println(f);
//				if(f!=null)
				
				if(f.getFoodName() != null)
				{
					System.out.println(f.getFoodName() + " " + f.getFoodType() + " " + f.getFoodCategory() + " " + f.getFoodPrice());
				}
				else
				{
					System.out.println("Food id not found");
				}
				
				break;
			case 5:
				System.out.println("Enter Food Name:");
				foodName =  sc.next();
				f = fdi.displayFoodByName(foodName);
				if(f.getFoodType()!= null) {
					System.out.println(f.getFoodName() + " " + f.getFoodType() + " " + f.getFoodCategory() + " " + f.getFoodPrice());
				}
				else {
					System.out.println("Food not found!");
				}
				break;
			case 6:
				System.out.println("Enter initial range:");
				int ir = sc.nextInt();
				System.out.println("Enter final range:");
				int fr = sc.nextInt();
				List<Food> food = fdi.displayFoodByRange(ir, fr);
				
				for(Food f1 : food)
				{
					System.out.println(f1.getFoodName() + " " + f1.getFoodType() + " " + f1.getFoodCategory() + " " + f1.getFoodPrice());
				}
				break;
				
			case 7:
				List<Food> foodList = fdi.displayAllFood();
				
				for(Food f1 : foodList)
				{
					System.out.println(f1.getFoodName() + " " + f1.getFoodType() + " " + f1.getFoodCategory() + " " + f1.getFoodPrice());
				}
				break;
			case 8:
				System.out.println("Thanku for visiting");
				break;
			default :
				System.out.println("Entered wrong choice!!!");
				break;
				
			}
		}while(choice<8);
	}

}