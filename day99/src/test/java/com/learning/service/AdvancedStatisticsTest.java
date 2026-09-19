package com.learning.service;

import com.learning.model.SalesRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AdvancedStatisticsTest {

    private final AdvancedStatistics statistics = new AdvancedStatistics();

    @Test
    void meanOfKnownValuesIsCorrect() {
        assertEquals(20.0, statistics.mean(List.of(10.0, 20.0, 30.0)), 0.001);
    }

    @Test
    void meanOfEmptyListIsZero() {
        assertEquals(0.0, statistics.mean(List.of()));
    }

    @Test
    void standardDeviationOfKnownValuesIsCorrect() {
        // Sample stddev of {2,4,4,4,5,5,7,9} is exactly 2.13809 (well-known textbook example).
        List<Double> values = List.of(2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0);
        assertEquals(2.13809, statistics.standardDeviation(values), 0.001);
    }

    @Test
    void standardDeviationOfASingleValueIsZero() {
        assertEquals(0.0, statistics.standardDeviation(List.of(5.0)));
    }

    @Test
    void monthlyTotalsGroupsChronologically() {
        List<SalesRecord> records = List.of(
                new SalesRecord(LocalDate.of(2026, 3, 1), "A", "R", 100),
                new SalesRecord(LocalDate.of(2026, 1, 1), "A", "R", 200),
                new SalesRecord(LocalDate.of(2026, 2, 1), "A", "R", 300));

        Map<String, Double> totals = statistics.monthlyTotals(records);

        assertEquals(List.of("2026-01", "2026-02", "2026-03"), List.copyOf(totals.keySet()));
        assertEquals(200.0, totals.get("2026-01"));
    }

    @Test
    void monthOverMonthGrowthExcludesTheFirstMonth() {
        Map<String, Double> totals = new java.util.LinkedHashMap<>();
        totals.put("2026-01", 100.0);
        totals.put("2026-02", 150.0);
        totals.put("2026-03", 120.0);

        Map<String, Double> growth = statistics.monthOverMonthGrowth(totals);

        assertEquals(2, growth.size());
        assertEquals(50.0, growth.get("2026-02"), 0.001);
        assertEquals(-20.0, growth.get("2026-03"), 0.001);
    }
}
