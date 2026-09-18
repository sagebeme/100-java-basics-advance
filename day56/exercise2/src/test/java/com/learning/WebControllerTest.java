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
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePageRendersItsTemplateVariables() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Welcome")))
                .andExpect(content().string(containsString("shares its header and footer")));
    }

    @Test
    void aboutPageRendersItsOwnVariables() throws Exception {
        mockMvc.perform(get("/about"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("About")))
                .andExpect(content().string(containsString("reusable layout fragments")));
    }

    @Test
    void bothPagesShareTheSameHeaderFragment() throws Exception {
        String home = mockMvc.perform(get("/")).andReturn().getResponse().getContentAsString();
        String about = mockMvc.perform(get("/about")).andReturn().getResponse().getContentAsString();

        org.junit.jupiter.api.Assertions.assertTrue(home.contains("<a href=\"/about\">About</a>"));
        org.junit.jupiter.api.Assertions.assertTrue(about.contains("<a href=\"/about\">About</a>"));
    }

    @Test
    void bothPagesShareTheSameFooterFragment() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("Day 56 - Static Files and Templates")));
        mockMvc.perform(get("/about"))
                .andExpect(content().string(containsString("Day 56 - Static Files and Templates")));
    }
}
