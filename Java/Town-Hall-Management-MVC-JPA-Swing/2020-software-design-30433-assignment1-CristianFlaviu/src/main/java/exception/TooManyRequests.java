package exception;

public class TooManyRequests extends RuntimeException {

    public TooManyRequests(String message)
    {
        super(message);
    }
}
