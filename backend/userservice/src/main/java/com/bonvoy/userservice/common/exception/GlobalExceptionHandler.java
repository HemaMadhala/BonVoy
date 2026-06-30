package com.bonvoy.userservice.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExistsException(UserAlreadyExistsException e){
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ErrorResponse(
                        LocalDateTime.now(),HttpStatus.CONFLICT.value(),
                        HttpStatus.CONFLICT.getReasonPhrase(),e.getMessage()
                ));
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(LocalDateTime.now(), HttpStatus.NOT_FOUND.value(),
                        HttpStatus.NOT_FOUND.getReasonPhrase(),e.getMessage()));
    }
}
