package validator;



import java.util.NoSuchElementException;

import dao.GeneralDao;
import model.Orders;

public class OrdersProductValidator implements Validator<Orders>{

	
	public void validate(Orders t) {
		
		if(GeneralDao.findById(t.getIdProduct(),"Product")==null)
		{
			throw new NoSuchElementException("product with id "+t.getIdProduct()+" does not exist");
		}
	}

}
