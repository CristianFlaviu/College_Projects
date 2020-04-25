package dataLayer;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import java.util.HashSet;

import businessLayer.MenuItems;

public class FileToWrite {
	
 private  static HashSet<MenuItems> aHashSet=new HashSet<>();
	
	public FileToWrite(HashSet<MenuItems> aHashSet) {
		
		this.aHashSet=aHashSet;
	}
	
	public static void writeToFile( ) {
		try {
			ObjectOutputStream outStream=new ObjectOutputStream(new FileOutputStream("Meniu.bin"));
			outStream.writeObject(aHashSet);
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
}
