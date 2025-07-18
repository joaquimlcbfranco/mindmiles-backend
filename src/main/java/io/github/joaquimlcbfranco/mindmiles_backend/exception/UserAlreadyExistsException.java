package io.github.joaquimlcbfranco.mindmiles_backend.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException() {

    }

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
