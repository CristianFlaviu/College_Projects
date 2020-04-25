package businessLayer;


import java.util.HashSet;
import java.util.List;

public class CompositeProduct implements MenuItems {

	private  String compositeProductName;
	private HashSet <MenuItems> listOfProduct=new HashSet<MenuItems>();
	
	public CompositeProduct(String name ) {
		compositeProductName=name;
	}
	
	public CompositeProduct (String name,HashSet<MenuItems> set)
	{
		listOfProduct=set;
		compositeProductName=name;
	}
	
	public String getName()
	{
		return compositeProductName;
	}
	
	public void addProduct(MenuItems aItems)
	{
		listOfProduct.add(aItems);
	}
	
	public void deleteProduct(String ProductName)
	{
		MenuItems deleteProduct = null;
		for(MenuItems aItems:listOfProduct)
		{
			if(aItems.getName().equals(ProductName))
			{
				deleteProduct=aItems;
			}
				
		}
		
		listOfProduct.remove(deleteProduct);
	}
	
	public int computePrice() {
		int price=0;
		for(MenuItems aMenuItem:listOfProduct)
		{
			price=price+aMenuItem.computePrice();
		}
		return price;
	}



	public void printProduct() {
	
		for(MenuItems aMenuItem:listOfProduct)
		{
			aMenuItem.printProduct();
		}
		System.out.println("\n ");
	}
	
	
}
