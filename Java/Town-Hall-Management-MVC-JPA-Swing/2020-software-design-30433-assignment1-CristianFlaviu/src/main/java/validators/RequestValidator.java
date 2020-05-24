package validators;

import entity.Request;
import exception.EmptyFieldException;

public class RequestValidator {

    public static void requestValidator(Request request)
    {
        if(request==null)
        {
            throw  new NullPointerException();

        }
        else if(request.getTitle().equals(""))
        {
            throw new EmptyFieldException("empty title");
        }
    }
}
