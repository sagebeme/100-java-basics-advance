package com.learning.controller;

import com.learning.dto.CryptoPrice;
import com.learning.dto.IssLocation;
import com.learning.dto.WeatherInfo;
import com.learning.exception.ApiException;
import com.learning.service.CryptoService;
import com.learning.service.IssService;
import com.learning.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.not;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/** The services are replaced with mocks, so the page is tested without the internet. */
@SpringBootTest
@AutoConfigureMockMvc
class DashboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private WeatherService weatherService;

    @MockitoBean
    private CryptoService cryptoService;

    @MockitoBean
    private IssService issService;

    @Test
    void theDashboardShowsAllThreeSectionsAndAutoRefreshes() throws Exception {
        when(weatherService.fetchCurrentWeather()).thenReturn(new WeatherInfo(23.4, 11.2));
        when(cryptoService.fetchPrices(anyList())).thenReturn(List.of(new CryptoPrice("bitcoin", 64000)));
        when(issService.fetchCurrentLocation()).thenReturn(new IssLocation(-1.28, 36.82));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Nairobi Weather")))
                .andExpect(content().string(containsString("23.4")))
                .andExpect(content().string(containsString("Crypto Prices")))
                .andExpect(content().string(containsString("bitcoin")))
                .andExpect(content().string(containsString("ISS Current Location")))
                .andExpect(content().string(containsString("36.82")))
                .andExpect(content().string(containsString("http-equiv=\"refresh\"")))
                .andExpect(content().string(containsString("Last updated:")));
    }

    @Test
    void oneFailingApiDoesNotTakeDownTheOtherSections() throws Exception {
        when(weatherService.fetchCurrentWeather()).thenReturn(new WeatherInfo(23.4, 11.2));
        when(cryptoService.fetchPrices(anyList())).thenThrow(new ApiException("Could not fetch crypto prices: 429", null));
        when(issService.fetchCurrentLocation()).thenReturn(new IssLocation(-1.28, 36.82));

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("23.4")))
                .andExpect(content().string(containsString("Could not fetch crypto prices: 429")))
                .andExpect(content().string(containsString("36.82")))
                .andExpect(content().string(not(containsString("weather-error"))));
    }
}
