package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PageControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void homePageRendersWithNavigationToAllSections() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Amina Kip")))
                .andExpect(content().string(containsString("href=\"/projects\"")))
                .andExpect(content().string(containsString("href=\"/contact\"")));
    }

    @Test
    void projectsPageListsEveryProjectFromTheService() throws Exception {
        mockMvc.perform(get("/projects"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("House Price Predictor")))
                .andExpect(content().string(containsString("Blog Platform REST API")))
                .andExpect(content().string(containsString("Text to Morse Code Converter")));
    }

    @Test
    void pagesIncludeAResponsiveViewportMeta() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("name=\"viewport\"")));
    }
}
