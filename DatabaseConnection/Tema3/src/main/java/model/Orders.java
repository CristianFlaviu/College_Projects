package model;

public class Orders {

	private int IdClient;
	private  int IdProduct;
	private int quantityOrdered;
	private int OrderId;
	
	public Orders() {
		IdClient=15;
		IdProduct=1;
		quantityOrdered=25;
		OrderId=3;
	}
	
	public Orders(int idClient,int idProduct,int quantity,int OrderId)
	{
		this.IdClient=idClient;
		this.IdProduct=idProduct;
		this.quantityOrdered=quantity;
		this.OrderId=OrderId;
		
	}
	public int getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(int quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public int getIdClient() {
		return IdClient;
	}
	public void setIdClient(int idClient) {
		IdClient = idClient;
	}
	public int getIdProduct() {
		return IdProduct;
	}
	public void setIdProduct(int idProduct) {
		IdProduct = idProduct;
	}

	public void setTotalPrice(int totalPrice) {
		quantityOrdered = totalPrice;
	}
	public int getOrderId() {
		return OrderId;
	}
	public void setOrderId(int orderId) {
		OrderId = orderId;
	}
	
	public String toString()
	{
		return IdClient+" "+IdProduct+ " "+" "+quantityOrdered+" "+OrderId;
	}
	public String ForPrint()
	{
		return "Id-ul comenzii :"+OrderId+"\n"+"Id-ul Clientului :"+IdClient+"\nId-ul Produsului "+ IdProduct+"\nCantatitatea Comandata "+quantityOrdered;
	}
	public String[] toArrayString()
	{
		String[] aStrings=new String[4];
		aStrings[1]=IdClient+"";
		aStrings[2]=IdProduct+"";
		aStrings[3]=quantityOrdered+"";
		aStrings[0]=OrderId+"";
		return aStrings;
	}
}
