package validators;

import entity.Document;
import exception.EmptyFieldException;
import messages.RootMessages;

public class DocumentValidator {

    public static void DocumentValidator(Document document) {
        if (document == null) {
            throw new NullPointerException("null document   .");
        } else if (document.getNume().equals("")
                || document.getId().equals("") ||
                document.getType().equals("")) {
            throw  new EmptyFieldException("");

        }

    }
}
