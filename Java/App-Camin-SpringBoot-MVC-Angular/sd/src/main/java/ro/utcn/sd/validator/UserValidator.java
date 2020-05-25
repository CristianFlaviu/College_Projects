package ro.utcn.sd.validator;


import ro.utcn.sd.Exceptions.LenghthFieldException;
import ro.utcn.sd.Exceptions.MissingAtributeException;
import ro.utcn.sd.Exceptions.NotUniqueEmailException;
import ro.utcn.sd.Exceptions.NotUniqueUsernameException;
import ro.utcn.sd.entity.User;

import java.util.List;
import java.util.Objects;

public class UserValidator {

    public static void validate(User user, List<User> userList)
    {

        if(Objects.isNull(user))
        {
            throw new  NullPointerException();
        }

        if(Objects.isNull(user.getRoom())) {
            throw new MissingAtributeException("The room with the specified Number does not exists");
        }
        if(Objects.isNull(user.getId())|| Objects.isNull(user.getFirstName())
        ||Objects.isNull(user.getLastName()) || Objects.isNull(user.getPassword()) || Objects.isNull(user.getEmail())
        ||Objects.isNull(user.getUsername()))
        {
            throw  new MissingAtributeException("Some attributes are missing ");
        }
        if(userList.stream().anyMatch(a->a.getEmail().equals(user.getEmail())))
        {
            throw  new NotUniqueEmailException();
        }
        if(userList.stream().anyMatch(a->a.getUsername().equals(user.getUsername())))
        {
            throw  new NotUniqueUsernameException();
        }

        if(user.getFirstName().length()<2)
            throw new LenghthFieldException("First Name shorter than 2 chracters");

        if(user.getLastName().length()<2)
            throw new LenghthFieldException("Last Name shorter than 2 chracters");

        if(user.getUsername().length()<3)
            throw new LenghthFieldException("User Name shorter than 3 chracters");

        if(user.getPassword().length()<3)
            throw new LenghthFieldException("Password shorter than 3 chracters");

        if(user.getEmail().length()<5)
            throw new LenghthFieldException("Email shorter than 5 chracters");


    }
}
