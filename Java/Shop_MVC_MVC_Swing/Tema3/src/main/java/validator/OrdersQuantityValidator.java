package validator;


import java.util.NoSuchElementException;

import dao.GeneralDao;
import model.Orders;
import model.Product;

public class OrdersQuantityValidator implements Validator<Orders>{


	public void validate(Orders t) {
		
		Product apProduct=(Product) GeneralDao.findById(t.getIdProduct(),"Product");
		if(apProduct!=null)
		{
			if(apProduct.getQuantity()<t.getQuantityOrdered())
			{
				throw new NoSuchElementException("to big quantity"); 
			}
		}
	}

}
