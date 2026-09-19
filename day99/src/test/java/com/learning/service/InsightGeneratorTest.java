package com.learning.service;

import com.learning.model.SalesRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsightGeneratorTest {

    private final InsightGenerator generator = new InsightGenerator(new AdvancedStatistics());

    @Test
    void identifiesTheTopCategoryAndRegionByRevenue() {
        List<SalesRecord> records = List.of(
                new SalesRecord(LocalDate.of(2026, 1, 1), "Electronics", "Nairobi", 1000),
                new SalesRecord(LocalDate.of(2026, 1, 2), "Clothing", "Mombasa", 200),
                new SalesRecord(LocalDate.of(2026, 2, 1), "Electronics", "Nairobi", 500));

        List<String> insights = generator.generate(records);

        assertTrue(insights.stream().anyMatch(s -> s.startsWith("Top category: Electronics")));
        assertTrue(insights.stream().anyMatch(s -> s.startsWith("Top region: Nairobi")));
    }

    @Test
    void identifiesTheStrongestAndWeakestMonths() {
        List<SalesRecord> records = List.of(
                new SalesRecord(LocalDate.of(2026, 1, 1), "A", "R", 1000),
                new SalesRecord(LocalDate.of(2026, 2, 1), "A", "R", 100));

        List<String> insights = generator.generate(records);

        assertTrue(insights.stream().anyMatch(s -> s.startsWith("Strongest month: 2026-01")));
        assertTrue(insights.stream().anyMatch(s -> s.startsWith("Weakest month: 2026-02")));
    }

    @Test
    void reportsADecliningTrendWhenRevenueFalls() {
        List<SalesRecord> records = List.of(
                new SalesRecord(LocalDate.of(2026, 1, 1), "A", "R", 1000),
                new SalesRecord(LocalDate.of(2026, 2, 1), "A", "R", 500));

        List<String> insights = generator.generate(records);

        assertTrue(insights.stream().anyMatch(s -> s.contains("declining")));
    }

    @Test
    void handlesAnEmptyDatasetWithoutThrowing() {
        List<String> insights = generator.generate(List.of());
        assertFalse(insights.isEmpty());
    }
}
