package validators;

import entity.House;
import exception.EmptyFieldException;

public class HouseValidator {

    public static void HouseValidator(House house)
    {
        if(house==null)
        {
            throw new NullPointerException("null house");
        }
        else if(house.getId().equals("")||house.getAdress().equals(""))
        {
            throw  new EmptyFieldException("");
        }
    }
}
