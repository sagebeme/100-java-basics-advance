package com.learning.maktaba;

public class NoCopiesLeftException extends RuntimeException {
    public NoCopiesLeftException(String title) {
        super("Every copy of \"" + title + "\" is out on loan.");
    }
}
