package com.learning.service;

import com.learning.dto.IssLocation;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/** Hits the real, live, keyless Open Notify ISS API - no mocking. */
class IssServiceTest {

    private final IssService issService = new IssService();

    @Test
    void fetchesARealCurrentLocationWithinValidCoordinateRanges() {
        IssLocation location = issService.fetchCurrentLocation();

        assertTrue(location.latitude() >= -90 && location.latitude() <= 90);
        assertTrue(location.longitude() >= -180 && location.longitude() <= 180);
    }
}
