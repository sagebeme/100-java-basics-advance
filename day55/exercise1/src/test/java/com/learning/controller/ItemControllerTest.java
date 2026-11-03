package com.learning.controller;

import com.learning.model.Item;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// The tests share one running app, so each one adds its own items and never assumes the shop is empty.
@SpringBootTest
@AutoConfigureMockMvc
class ItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private long add(String name, int shillings) throws Exception {
        String body = mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Item(null, name, shillings))))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(body).get("id").asLong();
    }

    @Test
    void addingAnItemAnswers201WithWhereToFindIt() throws Exception {
        mockMvc.perform(post("/api/items")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Unga 2kg\",\"shillings\":210}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", org.hamcrest.Matchers.matchesPattern("/api/items/\\d+")))
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.name").value("Unga 2kg"))
                .andExpect(jsonPath("$.shillings").value(210));
    }

    @Test
    void eachNewItemGetsItsOwnId() throws Exception {
        long first = add("Sukari 1kg", 160);
        long second = add("Chumvi", 30);
        org.junit.jupiter.api.Assertions.assertNotEquals(first, second);
    }

    @Test
    void anItemCanBeFetchedById() throws Exception {
        long id = add("Kimbo 500g", 290);
        mockMvc.perform(get("/api/items/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Kimbo 500g"))
                .andExpect(jsonPath("$.shillings").value(290));
    }

    @Test
    void anUnknownIdIs404() throws Exception {
        mockMvc.perform(get("/api/items/999999")).andExpect(status().isNotFound());
    }

    @Test
    void theListHasEveryItemInIdOrder() throws Exception {
        long a = add("Maziwa 500ml", 60);
        long b = add("Mkate", 65);
        mockMvc.perform(get("/api/items"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.id == " + a + ")].name").value("Maziwa 500ml"))
                .andExpect(jsonPath("$[?(@.id == " + b + ")].name").value("Mkate"));
        String body = mockMvc.perform(get("/api/items")).andReturn().getResponse().getContentAsString();
        long previous = 0;
        for (var item : objectMapper.readTree(body)) {
            org.junit.jupiter.api.Assertions.assertTrue(item.get("id").asLong() > previous, body);
            previous = item.get("id").asLong();
        }
    }

    @Test
    void putChangesTheNameAndPrice() throws Exception {
        long id = add("Rice 1kg", 180);
        mockMvc.perform(put("/api/items/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Pishori rice 1kg\",\"shillings\":240}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Pishori rice 1kg"))
                .andExpect(jsonPath("$.shillings").value(240));
        mockMvc.perform(get("/api/items/" + id)).andExpect(jsonPath("$.shillings").value(240));
    }

    @Test
    void puttingAnUnknownIdIs404() throws Exception {
        mockMvc.perform(put("/api/items/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Ghost\",\"shillings\":1}"))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletingAnswers204ThenTheItemIsGone() throws Exception {
        long id = add("Matchbox", 5);
        mockMvc.perform(delete("/api/items/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/items/" + id)).andExpect(status().isNotFound());
        mockMvc.perform(delete("/api/items/" + id)).andExpect(status().isNotFound());
    }
}
