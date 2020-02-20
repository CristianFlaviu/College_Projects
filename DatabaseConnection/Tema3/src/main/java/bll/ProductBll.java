package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import dao.GeneralDao;
import model.Client;
import model.Product;
import validator.ProductPriceValidator;
import validator.Validator;

public class ProductBll {

	private List<Validator<Product>> validators;
	
	public ProductBll() {
	
		validators=new ArrayList<Validator<Product>>();
		validators.add(new ProductPriceValidator());
		
	}
	public Product findProductById(int id) {
		Product aProduct = (Product) GeneralDao.findById(id,Product.class.getSimpleName());
		if (aProduct== null) {
			throw new NoSuchElementException("The Product with id =" + id + " was not found!");
		}
		return aProduct;
	}

	public int insertProduct(Product aProduct) {
		for (Validator<Product> v : validators) {
			v.validate(aProduct);
		}
		return GeneralDao.insert(aProduct);
	}
	
	public int DeleteProduct(int id )
	{
		if(GeneralDao.deleteByID(id,Product.class.getSimpleName())==0)
		{
			throw new NoSuchElementException("The Product with id ="+ id + " was not found!");
		}
		return 0;
	}
	public void uptadeProduct(Product aProduct)
	{
		/*for (Validator<Client> v : validators) {
			v.validate(aClient);
		}*/
		 GeneralDao.update(aProduct);
		
	}
}
