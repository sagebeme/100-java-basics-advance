package com.learning.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

    @GetMapping("/")
    public String home() {
        return "Welcome to Spring Boot!";
    }

    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello!");
        return response;
    }

    @GetMapping("/hello/{name}")
    public Map<String, String> helloName(@PathVariable String name) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Hello, " + name + "!");
        return response;
    }

    @GetMapping("/api/info")
    public Map<String, String> info() {
        Map<String, String> response = new HashMap<>();
        response.put("application", "Day 54 - Introduction to Spring Boot");
        response.put("version", "1.0.0");
        return response;
    }
}
