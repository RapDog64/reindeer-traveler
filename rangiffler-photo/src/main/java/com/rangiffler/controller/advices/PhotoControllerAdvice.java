package com.rangiffler.controller.advices;

import com.rangiffler.exception.InvalidRequestBodyException;
import com.rangiffler.exception.InvalidUsernameException;
import com.rangiffler.exception.PhotoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PhotoControllerAdvice {

    @ExceptionHandler(PhotoNotFoundException.class)
    public ResponseEntity<Object> photoNotFoundHandler(PhotoNotFoundException photoNotFoundException) {
        return new ResponseEntity<>(photoNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<Object> invalidRequestBodyHandler(InvalidRequestBodyException invalidRequestBodyException) {
        return new ResponseEntity<>(invalidRequestBodyException.getMessage(), HttpStatus.BAD_REQUEST);
    }
//
    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<Object> invalidRequestBodyHandler(InvalidUsernameException invalidUsernameException) {
        return new ResponseEntity<>(invalidUsernameException.getMessage(), HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(RecordNotFoundException.class)
//    public ResponseEntity<Object> recordNotFoundException(RecordNotFoundException recordNotFoundException) {
//        return new ResponseEntity<>(recordNotFoundException.getMessage(), HttpStatus.NOT_FOUND);
//    }
}
