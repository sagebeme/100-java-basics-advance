package com.learning.controller;

import tools.jackson.databind.ObjectMapper;
import com.learning.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void creatingAnItemAssignsItAnIdAndReturnsIt() throws Exception {
        Item item = new Item(null, "Notebook");

        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(item)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.name").value("Notebook"));
    }

    @Test
    void aCreatedItemCanBeFetchedById() throws Exception {
        String response = mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Item(null, "Pen"))))
                .andReturn().getResponse().getContentAsString();
        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(get("/api/items/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Pen"));
    }

    @Test
    void fetchingAnUnknownIdReturnsNotFound() throws Exception {
        mockMvc.perform(get("/api/items/999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void updatingAnItemChangesItsName() throws Exception {
        String response = mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Item(null, "Old Name"))))
                .andReturn().getResponse().getContentAsString();
        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(put("/api/items/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Item(null, "New Name"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    @Test
    void deletingAnItemMakesItUnreachable() throws Exception {
        String response = mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Item(null, "Eraser"))))
                .andReturn().getResponse().getContentAsString();
        Long id = objectMapper.readTree(response).get("id").asLong();

        mockMvc.perform(delete("/api/items/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/items/" + id))
                .andExpect(status().isNotFound());
    }

    @Test
    void allItemsIncludesEveryCreatedItem() throws Exception {
        mockMvc.perform(post("/api/items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Item(null, "Ruler"))));

        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'Ruler')]").exists());
    }
}
