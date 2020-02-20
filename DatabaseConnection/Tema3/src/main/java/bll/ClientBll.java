package bll;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


import dao.GeneralDao;
import model.Client;
import model.Product;
import validator.ClientAgeValidator;
import validator.ClientEmailValidator;
import validator.Validator;



public class ClientBll {

	private List<Validator<Client>> validators;

	public  ClientBll() {
	

		validators = new ArrayList<Validator<Client>>();
		validators.add(new ClientEmailValidator());
		validators.add(new ClientAgeValidator());
	}

	public Client findClientById(int id) {
		Client aClient =(Client) GeneralDao.findById(id,Client.class.getSimpleName());
		if (aClient== null) {
			throw new NoSuchElementException("The CLient with id =" + id + " was not found!");
		}
		return aClient;
	}

	public int insertClient(Client aClient) {
		for (Validator<Client> v : validators) {
			v.validate(aClient);
		}
		return GeneralDao.insert(aClient);
	}
	public int DeleteClient(int id )
	{
		if(GeneralDao.deleteByID(id,Client.class.getSimpleName())==0)
		{
			throw new NoSuchElementException("The Client with id ="+ id + " was not found!");
		}
		return 0;
	}
	public void updateClient(Client aClient)
	{
		/*for (Validator<Client> v : validators) {
			v.validate(aClient);
		}*/
		 GeneralDao.update(aClient);
		
	}
}
