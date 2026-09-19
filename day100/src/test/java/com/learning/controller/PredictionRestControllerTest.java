package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PredictionRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void predictsUsingTheExactRequestShapeFromTheDaysReadme() throws Exception {
        String requestBody = """
                {
                  "experience": 5,
                  "education": "Bachelor",
                  "location": "Urban",
                  "industry": "Technology"
                }
                """;

        mockMvc.perform(post("/api/predict")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.predictedEarnings").isNumber())
                .andExpect(jsonPath("$.confidence").isNumber())
                .andExpect(jsonPath("$.modelVersion").value("1.0"));
    }

    @Test
    void rejectsAMissingRequiredFieldAsABadRequest() throws Exception {
        String requestBody = """
                {
                  "experience": 5,
                  "education": "",
                  "location": "Urban",
                  "industry": "Technology"
                }
                """;

        mockMvc.perform(post("/api/predict")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest());
    }
}
