package businessLayer;

public class BaseProduct implements MenuItems {

	private String productName;
	private int price;
	
	public BaseProduct(String name,int price) {
		productName=name;
		this.price=price;
	}
	public int  computePrice() {
		return price;		
	}
	public String getName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public void printProduct() {
		
		System.out.print(productName +" ");
		
	}
}
