package validator;

import java.util.NoSuchElementException;

import model.Client;

public class ClientAgeValidator implements Validator<Client>{

	public void validate(Client aClient) {
		if(aClient.getAge()<15)
			throw new NoSuchElementException("to young");
		
	}

}
