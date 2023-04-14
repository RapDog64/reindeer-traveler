package com.rangiffler.controller.advices;

import com.rangiffler.exception.CountryNotFoundException;
import com.rangiffler.exception.InvalidCountryIdException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CountryControllerHandler {

    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<Object> recordNotFoundException(CountryNotFoundException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidCountryIdException.class)
    public ResponseEntity<Object> recordNotFoundException(InvalidCountryIdException exception) {
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
