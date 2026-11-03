package com.learning.service;

import com.learning.dto.WeatherInfo;
import com.learning.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import tools.jackson.databind.JsonNode;

@Service
public class WeatherService {

    private static final String URL = "https://api.open-meteo.com/v1/forecast"
            + "?latitude=-1.2833&longitude=36.8167&current_weather=true";

    private final RestClient restClient;

    public WeatherService() {
        this(RestClient.builder());
    }

    /** For tests: pass a builder bound to a MockRestServiceServer to answer without the internet. */
    WeatherService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    /** Current weather for Nairobi, Kenya, from Open-Meteo (free, no API key required). */
    public WeatherInfo fetchCurrentWeather() {
        JsonNode root;
        try {
            root = restClient.get().uri(URL).retrieve().body(JsonNode.class);
        } catch (RestClientException e) {
            throw new ApiException("Could not fetch weather data: " + e.getMessage(), e);
        }
        // Never trust the shape of someone else's JSON: check every field before using it.
        JsonNode current = root == null ? null : root.get("current_weather");
        if (current == null || !current.path("temperature").isNumber() || !current.path("windspeed").isNumber()) {
            throw new ApiException("Weather API response missing the current temperature or wind speed", null);
        }
        return new WeatherInfo(current.get("temperature").asDouble(), current.get("windspeed").asDouble());
    }
}
