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
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void pageLoadsTheBootstrapCssFromTheCdn() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("cdn.jsdelivr.net/npm/bootstrap")))
                .andExpect(content().string(containsString("bootstrap.min.css")));
    }

    @Test
    void pageLoadsTheBootstrapJavaScriptBundle() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("bootstrap.bundle.min.js")));
    }

    @Test
    void hasTheResponsiveViewportMetaTag() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("width=device-width, initial-scale=1.0")));
    }

    @Test
    void rendersTheTitleVariable() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("<title>My App</title>")));
    }

    @Test
    void servesTheLinkedCustomStylesheet() throws Exception {
        mockMvc.perform(get("/css/custom.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/css"));
    }
}
