package com.learning.service;

import com.learning.dto.IssLocation;
import com.learning.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import tools.jackson.databind.JsonNode;

@Service
public class IssService {

    // Open Notify only serves plain HTTP. Fine for public coordinates; never send secrets this way.
    private static final String URL = "http://api.open-notify.org/iss-now.json";

    private final RestClient restClient;

    public IssService() {
        this(RestClient.builder());
    }

    /** For tests: pass a builder bound to a MockRestServiceServer to answer without the internet. */
    IssService(RestClient.Builder builder) {
        this.restClient = builder.build();
    }

    /** Current real-time latitude/longitude of the International Space Station. */
    public IssLocation fetchCurrentLocation() {
        JsonNode root;
        try {
            root = restClient.get().uri(URL).retrieve().body(JsonNode.class);
        } catch (RestClientException e) {
            throw new ApiException("Could not fetch ISS location: " + e.getMessage(), e);
        }
        JsonNode position = root == null ? null : root.get("iss_position");
        if (position == null) {
            throw new ApiException("ISS API response missing 'iss_position'", null);
        }
        // The API sends the coordinates as strings, e.g. "-12.3456".
        try {
            double latitude = Double.parseDouble(position.path("latitude").asString());
            double longitude = Double.parseDouble(position.path("longitude").asString());
            return new IssLocation(latitude, longitude);
        } catch (NumberFormatException e) {
            throw new ApiException("ISS API response has unreadable coordinates", e);
        }
    }
}
