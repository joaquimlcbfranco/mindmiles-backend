package io.github.joaquimlcbfranco.mindmiles_backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExistsException error) {
        ErrorResponse errorResponse = ErrorResponse.builder(error, HttpStatus.CONFLICT, error.getMessage()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException error) {
        ErrorResponse errorResponse = ErrorResponse.builder(error, HttpStatus.NOT_FOUND, error.getMessage()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(InvalidParametersException error) {
        ErrorResponse errorResponse = ErrorResponse.builder(error, HttpStatus.BAD_REQUEST, error.getMessage()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception error) {
        ErrorResponse errorResponse = ErrorResponse.builder(error, HttpStatus.BAD_REQUEST, error.getMessage()).build();

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

}
