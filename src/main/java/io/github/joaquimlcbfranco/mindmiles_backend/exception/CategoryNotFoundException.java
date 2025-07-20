package io.github.joaquimlcbfranco.mindmiles_backend.exception;

public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException() {

    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
