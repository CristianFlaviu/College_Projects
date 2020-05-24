package validators;

import entity.User;
import exception.EmptyFieldException;
import exception.IllegalNameException;

public class UserValidator {

    public static void validateNewUser(User user) {
        if (user == null) {
            throw new NullPointerException("Null user");
        } else if (user.getId().equals("") ||
                user.getFirstName().equals("") ||
                user.getLastName().equals("") ||
                user.getEmail().equals("") ||
                user.getPassword().equals(""))
        {
            throw new EmptyFieldException("Empty field");
        }
        else if(user.getEmail().contains("<admin>"))
        {
            throw new IllegalNameException("Illegal email");
        }


    }
}
