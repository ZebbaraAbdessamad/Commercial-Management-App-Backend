package com.ensab.mediatech.exceptions.handler;

import com.ensab.mediatech.exceptions.EntityAlreadyExistException;
import com.ensab.mediatech.exceptions.EntityNotFoundException;
import com.ensab.mediatech.shared.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = {EntityNotFoundException.class})
    public ResponseEntity<ErrorResponse> entityNotFoundException(EntityNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                ErrorResponse.builder()
                .timeStamp(LocalDateTime.now())
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build()
        );
    }

    @ExceptionHandler(value = {EntityAlreadyExistException.class})
    public ResponseEntity<ErrorResponse> entityAlreadyExistException(EntityAlreadyExistException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(
                ErrorResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .message(ex.getMessage())
                        .status(HttpStatus.CONFLICT)
                        .statusCode(HttpStatus.CONFLICT.value())
                        .build()
        );
    }


    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> methodArgumentNotValidException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();//Map is a data structure in Java that stores key-value pairs

        ex.getBindingResult().getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(), fieldError.getDefaultMessage());
        });
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
                ErrorResponse.builder()
                        .timeStamp(LocalDateTime.now())
                        .message(ex.getMessage())
                        .errors(errors)
                        .status(HttpStatus.UNPROCESSABLE_ENTITY)
                        .statusCode(HttpStatus.UNPROCESSABLE_ENTITY.value())
                        .build()
        );
    }
}

