package com.learning.controller;

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
class MovieControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void usesSemanticLandmarksInsteadOfGenericDivs() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("<header")))
                .andExpect(content().string(containsString("<main")));
    }

    @Test
    void theMovieListHasAnAccessibleLabel() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(content().string(containsString("aria-label=\"Top ten ranked movies\"")));
    }

    @Test
    void everyFormFieldHasAnAssociatedLabel() throws Exception {
        String body = mockMvc.perform(get("/movies"))
                .andReturn().getResponse().getContentAsString();

        for (String field : new String[]{"title", "director", "year", "rating"}) {
            org.junit.jupiter.api.Assertions.assertTrue(
                    body.contains("for=\"" + field + "\""),
                    "expected a <label for=\"" + field + "\"> for the " + field + " field");
        }
    }

    @Test
    void declaresAViewportForResponsiveLayout() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(content().string(containsString("width=device-width, initial-scale=1.0")));
    }

    @Test
    void defersTheBootstrapScriptSoItDoesNotBlockRendering() throws Exception {
        mockMvc.perform(get("/movies"))
                .andExpect(content().string(containsString("bootstrap.bundle.min.js\" defer")));
    }
}
