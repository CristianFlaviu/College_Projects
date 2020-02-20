package businessLayer;

import java.util.Date;
import java.util.List;

public class Order {
	private  int orderID;
	private  Date date;
	private int table;
	private static int counterId=1; 
	
	public Order(int table) {
	
		this.orderID=counterId++;
		this.date=new Date();
		this.table=table;
	}
	public int getOrderID() {
		return orderID;
	}

	
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}


	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	public int getTable() {
		return table;
	}
	public void setTable(int table) {
		this.table = table;
	}
	@Override
	public int hashCode()
	{
		return table+orderID;
	}
}
