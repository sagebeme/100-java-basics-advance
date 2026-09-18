package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BlogControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void navbarShowsTheBlogTitle() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("navbar")))
                .andExpect(content().string(containsString("My Blog")));
    }

    @Test
    void rendersACardForEveryPost() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(content().string(containsString("Learn Java")))
                .andExpect(content().string(containsString("Build an API")))
                .andExpect(content().string(containsString("Style with Bootstrap")));
    }

    @Test
    void eachPostLinksToItsOwnReadMoreUrl() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(content().string(containsString("href=\"/posts/1\"")))
                .andExpect(content().string(containsString("href=\"/posts/2\"")))
                .andExpect(content().string(containsString("href=\"/posts/3\"")));
    }

    @Test
    void hasATwoColumnResponsiveLayoutWithASidebar() throws Exception {
        mockMvc.perform(get("/posts"))
                .andExpect(content().string(containsString("col-md-8")))
                .andExpect(content().string(containsString("col-md-4")))
                .andExpect(content().string(containsString("Sidebar")));
    }
}
