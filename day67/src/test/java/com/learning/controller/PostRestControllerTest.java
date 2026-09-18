package com.learning.controller;

import com.learning.model.Post;
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
class PostRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    private Long createPost(String title, String content) throws Exception {
        String response = mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Post(title, content))))
                .andReturn().getResponse().getContentAsString();
        return objectMapper.readTree(response).get("id").asLong();
    }

    @Test
    void listsAllPostsIncludingOnesJustCreated() throws Exception {
        createPost("Hello World", "First post");
        mockMvc.perform(get("/api/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[?(@.title == 'Hello World')]").exists());
    }

    @Test
    void getsAKnownPostById() throws Exception {
        Long id = createPost("Learn Java", "Start with variables");
        mockMvc.perform(get("/api/posts/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").value("Start with variables"));
    }

    @Test
    void getsA404ForAnUnknownPost() throws Exception {
        mockMvc.perform(get("/api/posts/999999")).andExpect(status().isNotFound());
    }

    @Test
    void creatingWithoutATitleIsRejected() throws Exception {
        mockMvc.perform(post("/api/posts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Post("", "content"))))
                .andExpect(status().isBadRequest());
    }

    @Test
    void updatingAKnownPostChangesItsTitleAndContent() throws Exception {
        Long id = createPost("Old Title", "Old content");
        mockMvc.perform(put("/api/posts/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Post("New Title", "New content"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("New Title"));
    }

    @Test
    void deletingAKnownPostRemovesItAndReturns204() throws Exception {
        Long id = createPost("Delete Me", "content");
        mockMvc.perform(delete("/api/posts/" + id)).andExpect(status().isNoContent());
        mockMvc.perform(get("/api/posts/" + id)).andExpect(status().isNotFound());
    }
}
