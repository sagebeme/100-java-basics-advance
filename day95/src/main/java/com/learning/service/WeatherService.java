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

    private final RestClient restClient = RestClient.create();

    /** Current weather for Nairobi, Kenya, from Open-Meteo (free, no API key required). */
    public WeatherInfo fetchCurrentWeather() {
        try {
            JsonNode root = restClient.get().uri(URL).retrieve().body(JsonNode.class);
            JsonNode current = root.get("current_weather");
            if (current == null) {
                throw new ApiException("Weather API response missing 'current_weather'", null);
            }
            return new WeatherInfo(current.get("temperature").asDouble(), current.get("windspeed").asDouble());
        } catch (RestClientException e) {
            throw new ApiException("Could not fetch weather data: " + e.getMessage(), e);
        }
    }
}
