package com.learning.exception;

// Already written.
public class DuplicatePhoneException extends RuntimeException {

    public DuplicatePhoneException(String phone) {
        super(phone + " is already a member");
    }
}
