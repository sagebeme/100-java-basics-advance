package com.learning.service;

import com.learning.dto.WeatherInfo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Hits the real, live, keyless Open-Meteo API - no mocking. */
class WeatherServiceTest {

    private final WeatherService weatherService = new WeatherService();

    @Test
    void fetchesRealCurrentWeatherWithinPlausibleRanges() {
        WeatherInfo weather = weatherService.fetchCurrentWeather();

        assertTrue(weather.temperatureCelsius() > -30 && weather.temperatureCelsius() < 55,
                "Temperature out of plausible range: " + weather.temperatureCelsius());
        assertTrue(weather.windSpeedKmh() >= 0 && weather.windSpeedKmh() < 300,
                "Wind speed out of plausible range: " + weather.windSpeedKmh());
    }
}
