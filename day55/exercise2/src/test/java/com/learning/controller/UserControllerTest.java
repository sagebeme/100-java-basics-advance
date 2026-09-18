package com.learning.controller;

import com.learning.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long createUser(String name, String email) throws Exception {
        String response = mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new User(null, name, email))))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void creatingAValidUserSucceeds() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new User(null, "Amina", "amina@example.com"))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Amina"));
    }

    @Test
    void creatingAUserWithNoNameIsRejectedWithABadRequest() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new User(null, "", "amina@example.com"))))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(400));
    }

    @Test
    void creatingAUserWithABadEmailIsRejected() throws Exception {
        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new User(null, "Amina", "not-an-email"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void fetchingAnUnknownUserReturnsNotFoundWithAMessage() throws Exception {
        mockMvc.perform(get("/api/users/999999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("No user with id 999999"));
    }

    @Test
    void searchingByNameFindsAMatchingUser() throws Exception {
        createUser("Zawadi Search Test", "zawadi@example.com");

        mockMvc.perform(get("/api/users/search").param("name", "Zawadi Search Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.name == 'Zawadi Search Test')]").exists());
    }

    @Test
    void searchingByEmailFindsAMatchingUser() throws Exception {
        createUser("Kip Search Test", "kip.search.test@example.com");

        mockMvc.perform(get("/api/users/search").param("email", "kip.search.test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.email == 'kip.search.test@example.com')]").exists());
    }

    @Test
    void updatingAnUnknownUserReturnsNotFound() throws Exception {
        mockMvc.perform(put("/api/users/999999")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new User(null, "Amina", "amina@example.com"))))
                .andExpect(status().isNotFound());
    }

    @Test
    void deletingAKnownUserSucceedsThenTheyAreGone() throws Exception {
        Long id = createUser("Delete Me", "delete.me@example.com");

        mockMvc.perform(delete("/api/users/" + id))
                .andExpect(status().isNoContent());

        mockMvc.perform(get("/api/users/" + id))
                .andExpect(status().isNotFound());
    }
}
