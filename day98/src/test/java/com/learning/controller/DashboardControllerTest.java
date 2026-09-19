package com.learning.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void theDashboardRendersChartsFiltersAndAutoRefresh() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("categoryChart")))
                .andExpect(content().string(containsString("regionChart")))
                .andExpect(content().string(containsString("monthChart")))
                .andExpect(content().string(containsString("http-equiv=\"refresh\"")))
                .andExpect(content().string(containsString("Electronics")));
    }

    @Test
    void filteringByCategoryChangesTheRecordCount() throws Exception {
        String allResponse = mockMvc.perform(get("/"))
                .andReturn().getResponse().getContentAsString();
        String filteredResponse = mockMvc.perform(get("/").param("category", "Electronics"))
                .andReturn().getResponse().getContentAsString();

        org.junit.jupiter.api.Assertions.assertNotEquals(allResponse.length(), filteredResponse.length());
    }

    @Test
    void exportingAsCsvReturnsCsvContentTypeAndAttachmentHeader() throws Exception {
        mockMvc.perform(get("/export").param("format", "csv"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("text/csv")))
                .andExpect(header().string("Content-Disposition", containsString("sales-data.csv")))
                .andExpect(content().string(containsString("date,category,region,amount")));
    }

    @Test
    void exportingAsJsonReturnsJsonContentTypeAndAttachmentHeader() throws Exception {
        mockMvc.perform(get("/export").param("format", "json"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Type", containsString("application/json")))
                .andExpect(header().string("Content-Disposition", containsString("sales-data.json")));
    }

    @Test
    void exportRespectsFilters() throws Exception {
        String allJson = mockMvc.perform(get("/export").param("format", "json"))
                .andReturn().getResponse().getContentAsString();
        String filteredJson = mockMvc.perform(get("/export").param("format", "json").param("category", "Sports"))
                .andReturn().getResponse().getContentAsString();

        org.junit.jupiter.api.Assertions.assertTrue(filteredJson.length() < allJson.length());
    }
}
