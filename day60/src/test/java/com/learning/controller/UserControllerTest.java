package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void showsAnEmptyRegistrationForm() throws Exception {
        mockMvc.perform(get("/users/new"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("action=\"/users\"")));
    }

    @Test
    void submittingAValidUserRedirectsAndPersistsIt() throws Exception {
        mockMvc.perform(post("/users").param("name", "Amina").param("email", "amina@example.com"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/users"));

        mockMvc.perform(get("/users"))
                .andExpect(content().string(containsString("Amina")))
                .andExpect(content().string(containsString("amina@example.com")));
    }

    @Test
    void submittingWithNoNameReShowsTheFormWithAnError() throws Exception {
        mockMvc.perform(post("/users").param("name", "").param("email", "amina@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Name is required")));
    }

    @Test
    void submittingWithABadEmailReShowsTheFormWithAnError() throws Exception {
        mockMvc.perform(post("/users").param("name", "Amina").param("email", "not-an-email"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Invalid email")));
    }

    @Test
    void aRejectedSubmissionIsNotSavedToTheDatabase() throws Exception {
        mockMvc.perform(post("/users").param("name", "").param("email", "not-an-email"));

        mockMvc.perform(get("/users"))
                .andExpect(content().string(org.hamcrest.Matchers.not(containsString("not-an-email"))));
    }
}
