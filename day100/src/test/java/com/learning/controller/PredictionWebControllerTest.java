package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PredictionWebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void theFormPageShowsInputsAndModelEvaluationMetrics() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Model Evaluation")))
                .andExpect(content().string(containsString("id=\"experience\"")))
                .andExpect(content().string(containsString("Technology")));
    }

    @Test
    void submittingTheFormShowsAPredictionResult() throws Exception {
        mockMvc.perform(post("/predict")
                        .param("experience", "8")
                        .param("education", "Master")
                        .param("location", "Urban")
                        .param("industry", "Finance"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Predicted annual earnings")));
    }
}
