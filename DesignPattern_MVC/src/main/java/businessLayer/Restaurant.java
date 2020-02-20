package businessLayer;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;



public class Restaurant extends Observable implements IRestaurantProcessing{

	private static int IdOrder = 1;

	private HashSet<Order> orders;
	private HashSet<MenuItems> meniuri;
	private Map<Order, HashSet<MenuItems>> ordersDetail;

	private List<Observer> observatorii;

	
	public Restaurant() {
		meniuri = new HashSet<MenuItems>();
		ordersDetail = new HashMap<Order, HashSet<MenuItems>>();
		orders=new HashSet<Order>();
		observatorii=new ArrayList<>();
	}
	public Restaurant(HashSet<MenuItems> listaMeniu) {
		assert 1==2:"size to small";
		
		observatorii=new ArrayList<>();
		orders=new HashSet<Order>();
		ordersDetail = new HashMap<Order, HashSet<MenuItems>>();
		meniuri =new HashSet<MenuItems>();
		for(MenuItems aItems:listaMeniu)
		meniuri.add(aItems);
	}
	public void addObserver(Observer aObserver)
	{
		observatorii.add(aObserver);
	}
	public void setMeniu(HashSet<MenuItems> aHashSet)
	{
		meniuri=aHashSet;
	}
	public HashSet<MenuItems> getMeniu()
	{
		return meniuri;
	}
	

	
	public void addProductToMenu(MenuItems aItems) {
		meniuri.add(aItems);
	}

	public void deleteProductFromMenu(String ProductName) {

		MenuItems deleleProduct = null;
		for (MenuItems aItems : meniuri) {
			if (aItems.getName().equals(ProductName)) {
				deleleProduct = aItems;
			}
			
		}
		meniuri.remove(deleleProduct);

	}

	public MenuItems getProduct(String productName) {
		for (MenuItems aItems : meniuri) {
			if (aItems.getName().equals(productName)) {
				return aItems;
			}
		}
		return null;
	}
	public void editProductFromMenu(MenuItems aItems) {
	
		MenuItems selectedProduct=getProduct(aItems.getName());
		
		if(selectedProduct.getClass().isInstance(BaseProduct.class.getSimpleName()))
		{
			((BaseProduct)selectedProduct).setPrice(((BaseProduct)aItems).getPrice());
		}
		else
		{
			
		}
		
	}

	public void notifyAllObservers()
	{
		for(Observer aObserver:observatorii)
			aObserver.update(null, null);
	}
	public void PrintMeniu() {
		for (MenuItems aItems : meniuri) {
			System.out.println(aItems.getName());
		}
	}

	public Order newOrder(int table, HashSet<MenuItems> ListaProduse) {
		
		Order aOrder=new Order(table);
		ordersDetail.put(aOrder, ListaProduse);
		orders.add(aOrder);
		generateFactura(aOrder.getOrderID());
		
notifyAllObservers();
	
		return aOrder;
	
	}

	public int computePrice(Order aOrder) {
		
		HashSet<MenuItems> arrayList=ordersDetail.get(aOrder);
		
		int price=0;
		for(MenuItems aItems:arrayList)
		{
			price=price+aItems.computePrice();
		}
		
		return price;
	}

	public void generateFactura(int id) {

		HashSet<MenuItems> produseComandate=null;
		Order order=null;
		for(Order aOrder:orders)
		{
			if(aOrder.getOrderID()==id)
			{	order=aOrder;
				produseComandate=ordersDetail.get(aOrder);
			}
		}
		
		try {
			FileWriter fileWriter = new FileWriter("Factura" + order.getOrderID() + ".txt");
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.print("Table " + order.getTable() + "\n");
			for (MenuItems aItems :produseComandate) {
				printWriter.print(aItems.getName() + "......." + aItems.computePrice() + "\n");
			}

			printWriter.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
		
	}

	

	public String[][] getMenuProducts()
	{
		String[][] meniu=new String[100][100];
		int count=0;
		for(MenuItems aItems:meniuri)
		{
			meniu[count][0]=aItems.getName();
			meniu[count][1]=aItems.computePrice()+"";
			
			count++;
		}
		
		return meniu;
	}

	public String[][] getAllOrders()
	{
		String[][] allOders=new String[100][100];
		int count=0;
for(Order aOrder:orders)
{
	allOders[count][0]=aOrder.getOrderID()+"";
	allOders[count][1]=aOrder.getDate()+"";
	allOders[count][2]=aOrder.getTable()+"";
	count++;
	
}
		return allOders;
	}

}
