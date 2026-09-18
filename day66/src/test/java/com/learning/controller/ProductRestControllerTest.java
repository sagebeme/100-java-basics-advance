package com.learning.controller;

import com.learning.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class ProductRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long createProduct(String name, double price) throws Exception {
        String response = mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product(name, price))))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void creatingAProductReturns201WithTheSavedResource() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product("Notebook", 250))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Notebook"));
    }

    @Test
    void gettingAKnownProductReturns200() throws Exception {
        Long id = createProduct("Pen", 20);
        mockMvc.perform(get("/api/v1/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pen"));
    }

    @Test
    void gettingAnUnknownProductReturns404WithAStructuredError() throws Exception {
        mockMvc.perform(get("/api/v1/products/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.message").value("No product with id 999999"));
    }

    @Test
    void creatingAnInvalidProductReturns400() throws Exception {
        mockMvc.perform(post("/api/v1/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product("", -5))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void updatingAKnownProductReturns200WithNewValues() throws Exception {
        Long id = createProduct("Old Name", 10);
        mockMvc.perform(put("/api/v1/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product("New Name", 15))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    @Test
    void updatingAnUnknownProductReturns404() throws Exception {
        mockMvc.perform(put("/api/v1/products/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Product("X", 1))))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletingAKnownProductReturns204ThenItIsGone() throws Exception {
        Long id = createProduct("Delete Me", 5);
        mockMvc.perform(delete("/api/v1/products/" + id))
                .andExpect(status().isNoContent());
        mockMvc.perform(get("/api/v1/products/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletingAnUnknownProductReturns404InsteadOfSilentlySucceeding() throws Exception {
        mockMvc.perform(delete("/api/v1/products/999999"))
                .andExpect(status().isNotFound());
    }
}
