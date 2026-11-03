package com.learning;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class StaticResourceTest {

    @Autowired
    private MockMvc mockMvc;

    private String homePage() throws Exception {
        return mockMvc.perform(get("/index.html"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getContentAsString();
    }

    @Test
    void theHomePageIsServedAtTheRoot() throws Exception {
        mockMvc.perform(get("/")).andExpect(forwardedUrl("index.html"));
        org.junit.jupiter.api.Assertions.assertTrue(homePage().contains("Madafu na Juice"));
    }

    @Test
    void theHomePageLinksItsFilesFromTheRootNotFromStatic() throws Exception {
        String page = homePage();
        for (String link : new String[] {
                "href=\"/css/style.css\"", "src=\"/js/app.js\"", "src=\"/images/logo.svg\"", "href=\"/menu/today.txt\""}) {
            org.junit.jupiter.api.Assertions.assertTrue(page.contains(link), "the page needs " + link);
        }
        org.junit.jupiter.api.Assertions.assertFalse(page.contains("/static/"), "static/ is never part of the URL");
    }

    @Test
    void theHomePageWorksOnAPhone() throws Exception {
        String page = homePage();
        org.junit.jupiter.api.Assertions.assertTrue(page.contains("name=\"viewport\""), "add the viewport meta tag");
        org.junit.jupiter.api.Assertions.assertTrue(page.matches("(?s).*<img[^>]+alt=\"[^\"]+\".*"), "the logo needs alt text");
    }

    @Test
    void servesTheStylesheetScriptAndLogoWithTheirTypes() throws Exception {
        mockMvc.perform(get("/css/style.css"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith("text/css"));
        mockMvc.perform(get("/js/app.js"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("DOMContentLoaded")));
        mockMvc.perform(get("/images/logo.svg"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.valueOf("image/svg+xml")));
    }

    @Test
    void theMenuBoardIsServedThroughItsOwnHandler() throws Exception {
        mockMvc.perform(get("/menu/today.txt"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Madafu")));
    }

    @Test
    void phonesMayKeepTheMenuForADay() throws Exception {
        mockMvc.perform(get("/menu/today.txt"))
                .andExpect(header().string("Cache-Control", containsString("max-age=86400")));
    }

    @Test
    void missingFilesAre404() throws Exception {
        mockMvc.perform(get("/css/missing.css")).andExpect(status().isNotFound());
        mockMvc.perform(get("/menu/yesterday.txt")).andExpect(status().isNotFound());
        mockMvc.perform(get("/static/css/style.css")).andExpect(status().isNotFound());
    }
}
