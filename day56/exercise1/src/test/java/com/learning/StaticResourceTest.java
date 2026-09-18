package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StaticResourceTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void servesTheCssFileWithTheRightContentType() throws Exception {
        mockMvc.perform(get("/css/style.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/css"))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("font-family")));
    }

    @Test
    void servesTheJavaScriptFile() throws Exception {
        mockMvc.perform(get("/js/app.js"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("DOMContentLoaded")));
    }

    @Test
    void servesTheImageFile() throws Exception {
        mockMvc.perform(get("/images/logo.svg"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("image/svg+xml")));
    }

    @Test
    void servesFilesThroughTheCustomAssetsHandler() throws Exception {
        mockMvc.perform(get("/assets/extra.css"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("custom /assets/**")));
    }

    @Test
    void aMissingStaticFileReturnsNotFound() throws Exception {
        mockMvc.perform(get("/css/does-not-exist.css"))
                .andExpect(status().isNotFound());
    }
}
