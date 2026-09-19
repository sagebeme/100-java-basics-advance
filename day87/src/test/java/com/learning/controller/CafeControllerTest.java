package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Runs against the real data seeded by DataSeeder at startup - no mocking of the search layer.
 */
@SpringBootTest
@AutoConfigureMockMvc
class CafeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void theHomePageListsSeededCafesAndIncludesTheMap() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Java House")))
                .andExpect(content().string(containsString("id=\"map\"")))
                .andExpect(content().string(containsString("leaflet")));
    }

    @Test
    void searchingByLocationFiltersToMatchingCafesOnly() throws Exception {
        mockMvc.perform(get("/").param("location", "Westlands"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Artcaffe Westlands")))
                .andExpect(content().string(not(containsString("Cafe Deli Karen"))));
    }

    @Test
    void filteringByMinimumRatingExcludesLowerRatedCafes() throws Exception {
        mockMvc.perform(get("/").param("minRating", "4.5"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Spring Valley Coffee"))) // rating 4.6
                .andExpect(content().string(not(containsString("CBD Express Coffee")))); // rating 3.6
    }

    @Test
    void filteringByWifiOnlyExcludesCafesWithoutWifi() throws Exception {
        mockMvc.perform(get("/").param("wifiOnly", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string(not(containsString("Cafe Deli Karen")))) // no wifi
                .andExpect(content().string(containsString("Java House"))); // has wifi
    }

    @Test
    void aSearchWithNoMatchesShowsTheNoResultsMessage() throws Exception {
        mockMvc.perform(get("/").param("location", "Mombasa"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("No cafes match your search")));
    }

    @Test
    void pageIncludesAResponsiveViewportMeta() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(content().string(containsString("name=\"viewport\"")));
    }
}
