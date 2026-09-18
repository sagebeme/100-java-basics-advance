package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void navbarLinksToHomeAndSignup() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("navbar")))
                .andExpect(content().string(containsString("href=\"/signup\"")));
    }

    @Test
    void rendersACardForEveryItemInTheModel() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Fast")))
                .andExpect(content().string(containsString("Simple")))
                .andExpect(content().string(containsString("Tested")))
                .andExpect(content().string(containsString("Spring Boot starts in seconds.")));
    }

    @Test
    void everyCardHasAViewDetailsButton() throws Exception {
        String body = mockMvc.perform(get("/"))
                .andReturn().getResponse().getContentAsString();
        long buttonCount = body.lines().filter(l -> l.contains("View Details")).count();
        org.junit.jupiter.api.Assertions.assertEquals(3, buttonCount);
    }

    @Test
    void signupPageHasAFormWithNameAndEmailFields() throws Exception {
        mockMvc.perform(get("/signup"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("action=\"/signup\"")))
                .andExpect(content().string(containsString("name=\"name\"")))
                .andExpect(content().string(containsString("name=\"email\"")))
                .andExpect(content().string(containsString("type=\"submit\"")));
    }
}
