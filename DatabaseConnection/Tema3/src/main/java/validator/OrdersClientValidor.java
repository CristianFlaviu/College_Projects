package validator;

import java.util.NoSuchElementException;

import dao.GeneralDao;
import model.Orders;

public class OrdersClientValidor implements Validator <Orders> {


	public void validate(Orders t) {
		
		if(GeneralDao.findById(t.getIdClient(), "Client")==null)
		{
			throw new NoSuchElementException("the Client with the id "+t.getIdClient()+" does not exist");
		}
		
	}

}
