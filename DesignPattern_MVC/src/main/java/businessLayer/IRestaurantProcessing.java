package businessLayer;


import java.util.Date;
import java.util.HashSet;
import java.util.List;

public interface IRestaurantProcessing {
	
 
public void deleteProductFromMenu(String productName);

public void addProductToMenu(MenuItems aItems);

public void editProductFromMenu(MenuItems aItems);

public Order newOrder(int table, HashSet<MenuItems> ListaProduse);

public int  computePrice(Order aOrder);

public void generateFactura(int id);

}
