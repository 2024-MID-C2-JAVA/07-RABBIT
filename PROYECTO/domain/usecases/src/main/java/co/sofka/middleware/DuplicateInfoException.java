package co.sofka.middleware;

public class DuplicateInfoException extends RuntimeException{

    public DuplicateInfoException(String message) {
        super(message);
    }
}
