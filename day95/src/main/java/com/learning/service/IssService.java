package com.learning.service;

import com.learning.dto.IssLocation;
import com.learning.exception.ApiException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import tools.jackson.databind.JsonNode;

@Service
public class IssService {

    private static final String URL = "http://api.open-notify.org/iss-now.json";

    private final RestClient restClient = RestClient.create();

    /** Current real-time latitude/longitude of the International Space Station. */
    public IssLocation fetchCurrentLocation() {
        try {
            JsonNode root = restClient.get().uri(URL).retrieve().body(JsonNode.class);
            JsonNode position = root.get("iss_position");
            if (position == null) {
                throw new ApiException("ISS API response missing 'iss_position'", null);
            }
            double latitude = Double.parseDouble(position.get("latitude").asString());
            double longitude = Double.parseDouble(position.get("longitude").asString());
            return new IssLocation(latitude, longitude);
        } catch (RestClientException e) {
            throw new ApiException("Could not fetch ISS location: " + e.getMessage(), e);
        }
    }
}
