package io.github.joaquimlcbfranco.mindmiles_backend.exception;

public class ActivityNotFoundException extends RuntimeException {

    public ActivityNotFoundException() {

    }

    public ActivityNotFoundException(String message) {
        super(message);
    }
}
