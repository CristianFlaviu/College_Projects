package validator;

import java.util.NoSuchElementException;

import model.Client;

public class ClientEmailValidator implements Validator<Client>{

	public void validate(Client aClient) {
	
		if(aClient.getEmail().length()<5)
			throw new NoSuchElementException("to short email");
		
	}

}
