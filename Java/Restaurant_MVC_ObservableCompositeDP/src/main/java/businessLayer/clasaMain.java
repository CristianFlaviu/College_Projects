package businessLayer;

import java.awt.Menu;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import dataLayer.FileToRead;
import dataLayer.FileToWrite;
import presentation.ChefView;

public class clasaMain {

	
	public static void main(String[] args)
	{
		HashSet<MenuItems> meniuRestaurant= new HashSet<>();
		
		MenuItems chips=new BaseProduct("chips", 2);
		MenuItems curlyChips=new BaseProduct("curryChips", 3);
		MenuItems cheese=new BaseProduct("cheese", 3);
		MenuItems cheeseChips=new CompositeProduct("cheese&chips");
		((CompositeProduct)cheeseChips).addProduct(cheese);	
		((CompositeProduct)cheeseChips).addProduct(chips);
		
		MenuItems burge=new BaseProduct("burger", 20);
		MenuItems cheeseBurger=new CompositeProduct("Cheese Burger");
		((CompositeProduct)cheeseBurger).addProduct(cheese);
		((CompositeProduct)cheeseBurger).addProduct(burge);
		
		MenuItems ceapa=new BaseProduct("ceapa", 12);
		MenuItems morcov=new BaseProduct("morcov", 4);
		MenuItems ridichi=new BaseProduct("ridichi", 7);
		
		
		meniuRestaurant.add(chips);
		meniuRestaurant.add(curlyChips);
		meniuRestaurant.add(cheese);
		meniuRestaurant.add(cheeseChips);
		meniuRestaurant.add(burge);
		meniuRestaurant.add(cheeseBurger);
		meniuRestaurant.add(ceapa);
		meniuRestaurant.add(morcov);
		meniuRestaurant.add(ridichi);
		
		ChefView aChefView=new ChefView();
		aChefView.setVisible(true);
		
		
		FileToWrite aFileToWrite=new FileToWrite(meniuRestaurant);
		aFileToWrite.writeToFile();
		Restaurant aRestaurant=new Restaurant();
		aRestaurant.addObserver(aChefView);
		
		FileToRead aFileToRead=new FileToRead(aRestaurant);
		aFileToRead.readFromFile();
		aRestaurant.PrintMeniu();
	
	
	}
}
