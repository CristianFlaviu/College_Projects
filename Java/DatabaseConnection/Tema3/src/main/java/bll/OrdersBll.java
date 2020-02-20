package bll;

import java.util.ArrayList;
import java.util.List;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import dao.GeneralDao;
import model.Client;
import model.Orders;
import validator.ClientAgeValidator;
import validator.ClientEmailValidator;
import validator.OrdersClientValidor;
import validator.OrdersProductValidator;
import validator.OrdersQuantityValidator;
import validator.Validator;

public class OrdersBll {

	private List<Validator<Orders>> validators;
	
	public OrdersBll() {
	

		validators = new ArrayList<Validator<Orders>>();
		validators.add(new OrdersClientValidor());
		validators.add(new OrdersProductValidator());
		validators.add(new OrdersQuantityValidator());
		
	}
	public int insertOrders(Orders order) {
		for (Validator<Orders> v : validators) {
			v.validate(order);
		}
		return GeneralDao.insert(order);
	}
	
	public void updateOrders(Orders orders)
	{
		GeneralDao.update(orders);
	}
}
