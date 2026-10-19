package com.learning.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

// Every error leaves the API in the same JSON shape, with the status code that tells the client what went wrong.
// Wrong method (405, with an Allow header) and wrong content type (415) are already handled by Spring.
@RestControllerAdvice
public class ApiExceptionHandler {

    // 404: nothing lives at that id.
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ApiError> handleNotFound(ProductNotFoundException ex) {
        return error(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    // 409: the request is fine, but it clashes with what's already stored.
    @ExceptionHandler(DuplicateProductException.class)
    public ResponseEntity<ApiError> handleDuplicate(DuplicateProductException ex) {
        return error(HttpStatus.CONFLICT, ex.getMessage());
    }

    // 400: the body can't even be read (broken JSON, text where a number should be).
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiError> handleUnreadable(HttpMessageNotReadableException ex) {
        return error(HttpStatus.BAD_REQUEST, "The request body isn't valid JSON for a product");
    }

    // 422: it was read fine, but the values break a rule (a blank name, a negative price).
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(FieldError::getDefaultMessage)
                .sorted()
                .collect(Collectors.joining(", "));
        return error(HttpStatus.UNPROCESSABLE_CONTENT, message);
    }

    private static ResponseEntity<ApiError> error(HttpStatus status, String message) {
        return ResponseEntity.status(status).body(new ApiError(status.value(), message));
    }
}
