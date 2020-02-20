package validator;

import java.util.NoSuchElementException;

import model.Product;

public class ProductPriceValidator implements Validator<Product>{

	
	public void validate(Product t) {
		
		if(t.getPrice()<0)
			throw new NoSuchElementException("wrong price");
		
	}

}
