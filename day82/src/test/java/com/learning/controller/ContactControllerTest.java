package com.learning.controller;

import com.learning.service.ContactService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactService contactService;

    @Test
    void theContactFormIsShownOnGet() throws Exception {
        mockMvc.perform(get("/contact"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("name=\"name\"")))
                .andExpect(content().string(containsString("name=\"email\"")))
                .andExpect(content().string(containsString("name=\"message\"")));
    }

    @Test
    void submittingAValidMessageSavesItAndShowsASuccessMessage() throws Exception {
        int before = contactService.listMessages().size();

        mockMvc.perform(post("/contact")
                        .param("name", "Kip Amani")
                        .param("email", "kip@example.com")
                        .param("message", "Loved the house price predictor demo!"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Thanks for reaching out")));

        assertEquals(before + 1, contactService.listMessages().size());
        assertEquals("kip@example.com", contactService.listMessages().get(contactService.listMessages().size() - 1).email());
    }

    @Test
    void submittingWithAMissingNameShowsAValidationErrorAndDoesNotSave() throws Exception {
        int before = contactService.listMessages().size();

        mockMvc.perform(post("/contact")
                        .param("name", "")
                        .param("email", "kip@example.com")
                        .param("message", "Hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Please enter your name")))
                .andExpect(content().string(not(containsString("Thanks for reaching out"))));

        assertEquals(before, contactService.listMessages().size());
    }

    @Test
    void submittingWithAnInvalidEmailShowsAValidationErrorAndDoesNotSave() throws Exception {
        int before = contactService.listMessages().size();

        mockMvc.perform(post("/contact")
                        .param("name", "Zawadi")
                        .param("email", "not-an-email")
                        .param("message", "Hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Please enter a valid email address")));

        assertEquals(before, contactService.listMessages().size());
    }
}
