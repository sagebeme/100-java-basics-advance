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
class PaletteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void theHomePageRendersTheDefaultPalette() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("#3B6EA5")));
    }

    @Test
    void requestingATriadicSchemeRendersThreeSwatches() throws Exception {
        mockMvc.perform(get("/").param("baseColor", "#FF0000").param("scheme", "TRIADIC"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("#FF0000")))
                .andExpect(content().string(containsString("hex-code")));
    }

    @Test
    void exportingAsCssReturnsCssContentTypeAndAttachmentHeader() throws Exception {
        mockMvc.perform(get("/export").param("baseColor", "#FF0000").param("scheme", "COMPLEMENTARY").param("format", "css"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("text/css")))
                .andExpect(header().string("Content-Disposition", containsString("palette.css")))
                .andExpect(content().string(containsString(":root {")))
                .andExpect(content().string(containsString("#FF0000")));
    }

    @Test
    void exportingAsJsonReturnsJsonContentTypeAndAttachmentHeader() throws Exception {
        mockMvc.perform(get("/export").param("baseColor", "#FF0000").param("scheme", "COMPLEMENTARY").param("format", "json"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("application/json")))
                .andExpect(header().string("Content-Disposition", containsString("palette.json")))
                .andExpect(content().string(containsString("#FF0000")));
    }
}
