package dataLayer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.HashSet;

import businessLayer.MenuItems;
import businessLayer.Restaurant;

public class FileToRead {
	
static Restaurant aRestaurant;
	
	public FileToRead(Restaurant aRestaurant) {
		this.aRestaurant=aRestaurant;
	}
	
	public static void  readFromFile() {
		ObjectInputStream inputStream;
		
		try {
			inputStream=new ObjectInputStream(new FileInputStream("Meniu.bin"));
			HashSet<MenuItems> meniu=(HashSet<MenuItems>)inputStream.readObject();
			aRestaurant.setMeniu(meniu);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		} 
		
	}

}
