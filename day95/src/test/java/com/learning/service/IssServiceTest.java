package com.learning.service;

import com.learning.dto.IssLocation;
import com.learning.exception.ApiException;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

/** Fixed responses for the logic; one real call to Open Notify, skipped when unreachable. */
class IssServiceTest {

    private static final String URL = "http://api.open-notify.org/iss-now.json";

    private final RestClient.Builder builder = RestClient.builder();
    private final MockRestServiceServer server = MockRestServiceServer.bindTo(builder).build();
    private final IssService issService = new IssService(builder);

    @Test
    void readsTheCoordinatesTheApiSendsAsStrings() {
        server.expect(requestTo(URL)).andRespond(withSuccess(
                "{\"message\":\"success\",\"iss_position\":{\"latitude\":\"-1.2833\",\"longitude\":\"36.8167\"}}",
                MediaType.APPLICATION_JSON));

        assertEquals(new IssLocation(-1.2833, 36.8167), issService.fetchCurrentLocation());
    }

    @Test
    void unreadableCoordinatesAreAnApiErrorNotACrash() {
        server.expect(requestTo(URL)).andRespond(withSuccess(
                "{\"iss_position\":{\"latitude\":\"north-ish\"}}", MediaType.APPLICATION_JSON));

        assertThrows(ApiException.class, issService::fetchCurrentLocation);
    }

    @Test
    void fetchesARealCurrentLocationWithinValidCoordinateRanges() {
        IssLocation location;
        try {
            location = new IssService().fetchCurrentLocation();
        } catch (ApiException e) {
            assumeTrue(!(e.getCause() instanceof RestClientException), "Open Notify unreachable: " + e.getMessage());
            throw e;
        }

        assertTrue(location.latitude() >= -90 && location.latitude() <= 90);
        assertTrue(location.longitude() >= -180 && location.longitude() <= 180);
    }
}
