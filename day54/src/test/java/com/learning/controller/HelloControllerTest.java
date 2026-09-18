package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homeReturnsAWelcomeMessage() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to Spring Boot!"));
    }

    @Test
    void helloReturnsAGenericGreeting() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello!"));
    }

    @Test
    void helloWithNameReturnsAPersonalizedGreeting() throws Exception {
        mockMvc.perform(get("/hello/Amina"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello, Amina!"));
    }

    @Test
    void infoReturnsApplicationDetails() throws Exception {
        mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.application").exists())
                .andExpect(jsonPath("$.version").value("1.0.0"));
    }
}
