package com.learning.exception;

public class DuplicateProductException extends RuntimeException {
    public DuplicateProductException(String name) {
        super("There's already a product called " + name);
    }
}
