package io.github.joaquimlcbfranco.mindmiles_backend.exception;

public class InvalidParametersException extends RuntimeException {

    public InvalidParametersException() {

    }

    public InvalidParametersException(String message) {
        super(message);
    }
}
