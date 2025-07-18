package io.github.joaquimlcbfranco.mindmiles_backend.exception;

public class NoSuchUserException extends RuntimeException {

    public NoSuchUserException() {

    }

    public NoSuchUserException(String message) {
        super(message);
    }
}
