package com.learning;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class SalesAggregatorTest {

    private final SalesAggregator aggregator = new SalesAggregator();

    private final List<Sale> sales = List.of(
            new Sale("Electronics", "Nairobi", 5000),
            new Sale("Electronics", "Mombasa", 3000),
            new Sale("Groceries", "Nairobi", 1200),
            new Sale("Groceries", "Kisumu", 800),
            new Sale("Clothing", "Nairobi", 2000)
    );

    @Test
    void groupsSalesByCategory() {
        Map<String, List<Sale>> grouped = aggregator.groupByCategory(sales);
        assertEquals(2, grouped.get("Electronics").size());
        assertEquals(2, grouped.get("Groceries").size());
        assertEquals(1, grouped.get("Clothing").size());
    }

    @Test
    void sumsAmountsWithinEachCategory() {
        Map<String, Double> totals = aggregator.totalsByCategory(sales);
        assertEquals(8000.0, totals.get("Electronics"));
        assertEquals(2000.0, totals.get("Groceries"));
    }

    @Test
    void averagesAmountsWithinEachCategory() {
        Map<String, Double> averages = aggregator.averagesByCategory(sales);
        assertEquals(4000.0, averages.get("Electronics"));
        assertEquals(1000.0, averages.get("Groceries"));
    }

    @Test
    void countsSalesWithinEachCategory() {
        Map<String, Long> counts = aggregator.countByCategory(sales);
        assertEquals(2L, counts.get("Electronics"));
        assertEquals(1L, counts.get("Clothing"));
    }

    @Test
    void groupsByCategoryThenByRegion() {
        Map<String, Map<String, Double>> nested = aggregator.totalsByCategoryAndRegion(sales);
        assertEquals(5000.0, nested.get("Electronics").get("Nairobi"));
        assertEquals(3000.0, nested.get("Electronics").get("Mombasa"));
    }

    @Test
    void findsTheCategoryWithTheHighestTotal() {
        assertEquals("Electronics", aggregator.topCategoryByTotal(sales));
    }

    @Test
    void summaryListsEveryCategoryAndTheTopOne() {
        String summary = aggregator.generateSummary(sales);
        assertTrue(summary.contains("Electronics: total=8000.00, average=4000.00, count=2"));
        assertTrue(summary.contains("Top category: Electronics"));
    }
}
