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

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ApiError> notFound(MemberNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.message(404, e.getMessage()));
    }

    @ExceptionHandler(DuplicatePhoneException.class)
    public ResponseEntity<ApiError> duplicate(DuplicatePhoneException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ApiError.message(409, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> invalid(MethodArgumentNotValidException e) {
        Map<String, String> errors = new LinkedHashMap<>();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            errors.putIfAbsent(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(ApiError.fields(400, errors));
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> unreadable(HttpMessageNotReadableException e) {
        return ResponseEntity.badRequest().body(ApiError.message(400, "The body isn't valid JSON for a member"));
    }
}
