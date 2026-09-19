package com.learning.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class ContactForm {

    @NotBlank(message = "Please enter your name")
    private String name;

    @NotBlank(message = "Please enter your email")
    @Email(message = "Please enter a valid email address")
    private String email;

    @NotBlank(message = "Please enter a message")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
