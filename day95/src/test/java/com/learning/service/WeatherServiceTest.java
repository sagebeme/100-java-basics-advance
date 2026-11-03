package com.learning.service;

import com.learning.dto.WeatherInfo;
import com.learning.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import static org.hamcrest.Matchers.startsWith;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/**
 * Most tests answer with a fixed response from MockRestServiceServer, so they run offline and
 * can try responses the real API rarely sends. One test calls the real Open-Meteo API, and is
 * skipped when it can't be reached.
 */
class WeatherServiceTest {

    private final RestClient.Builder builder = RestClient.builder();
    private final MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
    private final WeatherService weatherService = new WeatherService(builder);

    @Test
    void readsTemperatureAndWindFromTheResponse() {
        server.expect(requestTo(startsWith("https://api.open-meteo.com/v1/forecast?latitude=-1.2833")))
                .andRespond(withSuccess("{\"current_weather\":{\"temperature\":23.4,\"windspeed\":11.2}}",
                        MediaType.APPLICATION_JSON));

        WeatherInfo weather = weatherService.fetchCurrentWeather();

        assertEquals(23.4, weather.temperatureCelsius());
        assertEquals(11.2, weather.windSpeedKmh());
        server.verify();
    }

    @Test
    void aResponseWithoutTheFieldsIsAnApiErrorNotACrash() {
        server.expect(requestTo(startsWith("https://api.open-meteo.com/")))
                .andRespond(withSuccess("{\"current_weather\":{\"temperature\":23.4}}", MediaType.APPLICATION_JSON));

        assertThrows(ApiException.class, weatherService::fetchCurrentWeather);
    }

    @Test
    void aServerErrorIsAnApiError() {
        server.expect(requestTo(startsWith("https://api.open-meteo.com/")))
                .andRespond(withStatus(HttpStatus.SERVICE_UNAVAILABLE));

        ApiException e = assertThrows(ApiException.class, weatherService::fetchCurrentWeather);
        assertTrue(e.getMessage().startsWith("Could not fetch weather data"));
    }

    @Test
    void fetchesRealCurrentWeatherWithinPlausibleRanges() {
        WeatherInfo weather;
        try {
            weather = new WeatherService().fetchCurrentWeather();
        } catch (ApiException e) {
            // Offline, or the API is down: skip. A response we can't read still fails the test.
            assumeTrue(!(e.getCause() instanceof RestClientException), "Open-Meteo unreachable: " + e.getMessage());
            throw e;
        }

        assertTrue(weather.temperatureCelsius() > -30 && weather.temperatureCelsius() < 55,
                "Temperature out of plausible range: " + weather.temperatureCelsius());
        assertTrue(weather.windSpeedKmh() >= 0 && weather.windSpeedKmh() < 300,
                "Wind speed out of plausible range: " + weather.windSpeedKmh());
    }
}
