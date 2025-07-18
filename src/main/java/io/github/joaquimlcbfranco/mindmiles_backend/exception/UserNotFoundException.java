package io.github.joaquimlcbfranco.mindmiles_backend.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {

    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
