package com.learning.service;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class RevenueForecasterTest {

    private final RevenueForecaster forecaster = new RevenueForecaster();

    @Test
    void fitsAnExactTrendLineForPerfectlyLinearData() {
        // y = 100 + 50x for x = 0,1,2,3
        Map<String, Double> totals = new LinkedHashMap<>();
        totals.put("2026-01", 100.0);
        totals.put("2026-02", 150.0);
        totals.put("2026-03", 200.0);
        totals.put("2026-04", 250.0);

        RevenueForecaster.TrendLine trend = forecaster.fitTrend(totals);

        assertEquals(50.0, trend.slope(), 0.001);
        assertEquals(100.0, trend.intercept(), 0.001);
    }

    @Test
    void forecastContinuesTheLinearTrendForTheRequestedMonths() {
        Map<String, Double> totals = new LinkedHashMap<>();
        totals.put("2026-01", 100.0);
        totals.put("2026-02", 150.0);
        totals.put("2026-03", 200.0);
        totals.put("2026-04", 250.0);

        Map<String, Double> forecast = forecaster.forecast(totals, 3);

        assertEquals(List.of("2026-05", "2026-06", "2026-07"), List.copyOf(forecast.keySet()));
        assertEquals(300.0, forecast.get("2026-05"), 0.001);
        assertEquals(350.0, forecast.get("2026-06"), 0.001);
        assertEquals(400.0, forecast.get("2026-07"), 0.001);
    }

    @Test
    void forecastNeverGoesNegativeEvenWithASteepDownwardTrend() {
        Map<String, Double> totals = new LinkedHashMap<>();
        totals.put("2026-01", 100.0);
        totals.put("2026-02", 10.0);

        Map<String, Double> forecast = forecaster.forecast(totals, 5);

        assertTrue(forecast.values().stream().allMatch(v -> v >= 0));
    }

    @Test
    void fittingATrendWithFewerThanTwoMonthsThrows() {
        Map<String, Double> single = new LinkedHashMap<>();
        single.put("2026-01", 100.0);

        assertThrows(IllegalArgumentException.class, () -> forecaster.fitTrend(single));
    }

    @Test
    void forecastHandlesTheDecemberToJanuaryYearRollover() {
        Map<String, Double> totals = new LinkedHashMap<>();
        totals.put("2025-11", 100.0);
        totals.put("2025-12", 120.0);

        Map<String, Double> forecast = forecaster.forecast(totals, 2);

        assertEquals(List.of("2026-01", "2026-02"), List.copyOf(forecast.keySet()));
    }
}
