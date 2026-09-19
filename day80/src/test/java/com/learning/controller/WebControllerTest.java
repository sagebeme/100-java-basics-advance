package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class WebControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void theHomePageShowsThePredictionForm() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("House Price Predictor")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString("name=\"squareFeet\"")));
    }

    @Test
    void submittingTheFormShowsAPredictedPrice() throws Exception {
        mockMvc.perform(post("/predict")
                        .param("squareFeet", "1800")
                        .param("bedrooms", "3")
                        .param("bathrooms", "2")
                        .param("ageYears", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString("Predicted price")));
    }

    @Test
    void submittingInvalidFormDataReShowsTheFormWithoutAPrediction() throws Exception {
        mockMvc.perform(post("/predict")
                        .param("squareFeet", "-500")
                        .param("bedrooms", "3")
                        .param("bathrooms", "2")
                        .param("ageYears", "10"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.not(org.hamcrest.Matchers.containsString("Predicted price"))));
    }
}
