package com.learning.exception;

// Already written.
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(long id) {
        super("No member with id " + id);
    }
}
