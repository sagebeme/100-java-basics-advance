package com.learning.controller;

import com.learning.model.HouseFeatures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Uses the real model, trained at application startup from the bundled training-data.csv by
 * TrainingDataLoader - no mocking of the ML layer.
 */
@SpringBootTest
@AutoConfigureMockMvc
class PredictionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void predictsAPlausiblePositivePriceForAMidSizedHouse() throws Exception {
        HouseFeatures features = new HouseFeatures(1800, 3, 2, 10);

        mockMvc.perform(post("/api/predict/house-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(features)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").isNumber())
                .andExpect(jsonPath("$.price", org.hamcrest.Matchers.greaterThan(0.0)));
    }

    @Test
    void aLargerHousePredictsAHigherPriceThanASmallerOneAllElseEqual() throws Exception {
        String smallResponse = mockMvc.perform(post("/api/predict/house-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new HouseFeatures(1000, 2, 1, 10))))
                .andReturn().getResponse().getContentAsString();
        String largeResponse = mockMvc.perform(post("/api/predict/house-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new HouseFeatures(2800, 4, 3, 10))))
                .andReturn().getResponse().getContentAsString();

        double smallPrice = objectMapper.readTree(smallResponse).get("price").asDouble();
        double largePrice = objectMapper.readTree(largeResponse).get("price").asDouble();

        org.junit.jupiter.api.Assertions.assertTrue(largePrice > smallPrice);
    }

    @Test
    void rejectsNonPositiveSquareFootageAsABadRequest() throws Exception {
        String invalidJson = "{\"squareFeet\":-100,\"bedrooms\":3,\"bathrooms\":2,\"ageYears\":10}";

        mockMvc.perform(post("/api/predict/house-price")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(invalidJson))
                .andExpect(status().isBadRequest());
    }
}
