package com.rangiffler.controller.advices;

import com.rangiffler.exception.ErrorResponse;
import com.rangiffler.exception.InvalidPhotoIdFormatException;
import com.rangiffler.exception.InvalidRequestBodyException;
import com.rangiffler.exception.InvalidUsernameException;
import com.rangiffler.exception.PhotoIdsException;
import com.rangiffler.exception.PhotoNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PhotoControllerAdvice {

    @ExceptionHandler(PhotoNotFoundException.class)
    public ResponseEntity<ErrorResponse> photoNotFoundHandler(PhotoNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidRequestBodyException.class)
    public ResponseEntity<ErrorResponse> invalidRequestBodyHandler(InvalidRequestBodyException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidUsernameException.class)
    public ResponseEntity<ErrorResponse> invalidRequestBodyHandler(InvalidUsernameException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(InvalidPhotoIdFormatException.class)
    public ResponseEntity<ErrorResponse> invalidPhotoIdFormatHandler(InvalidPhotoIdFormatException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(PhotoIdsException.class)
    public ResponseEntity<ErrorResponse> differentIdsHandler(PhotoIdsException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.BAD_REQUEST);
    }
}
