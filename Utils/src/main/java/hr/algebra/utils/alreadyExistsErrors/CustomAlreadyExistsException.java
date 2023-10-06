package hr.algebra.utils.alreadyExistsErrors;

public class CustomAlreadyExistsException extends RuntimeException{
    public CustomAlreadyExistsException(String message) {
        super(message);
    }
}
