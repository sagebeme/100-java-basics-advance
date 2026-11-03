package com.learning.exception;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// Turns exceptions thrown anywhere in a controller into a clear JSON answer.
@RestControllerAdvice
public class GlobalExceptionHandler {

    // TODO: one @ExceptionHandler method for each row of the README's error table,
    //       each returning ResponseEntity<ApiError> with the right status.
}
