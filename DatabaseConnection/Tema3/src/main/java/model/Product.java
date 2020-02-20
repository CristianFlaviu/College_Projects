package model;

import com.mysql.cj.conf.StringProperty;

public class Product {

	
	private String productName;
    private int quantity;
    private int price;
    private int idProduct;
    
    public Product() {
		
    	idProduct=3;
    	productName="newProduct";
    	quantity=10;
    	price=101;
	}
    public Product(int idProduct,String productName,int quantity,int price)
    {
    	this.idProduct=idProduct;
    	this.productName=productName;
    	this.quantity=quantity;
    	this.price=price;
    	
    }
	public String getProductName() {
		
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getIdProduct() {
		return idProduct;
	}
	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public String toString()
	{
		return productName+" "+quantity+" "+price+" "+idProduct;
	}
	
	public String[] toArrayString()
	{
		String[] aStrings=new String[4];
		aStrings[1]=productName;
		aStrings[2]=quantity+" ";
		aStrings[3]=price+"";
		aStrings[0]=idProduct+"";
		return aStrings;
	}
	
}
