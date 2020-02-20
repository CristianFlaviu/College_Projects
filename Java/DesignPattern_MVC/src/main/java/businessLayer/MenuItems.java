package businessLayer;

import java.io.Serializable;

public interface MenuItems extends Serializable{

	public int computePrice();
	public void printProduct();
	public String getName();
}
