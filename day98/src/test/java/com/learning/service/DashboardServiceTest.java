package com.learning.service;

import com.learning.model.SalesRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DashboardServiceTest {

    private DashboardService service;

    @BeforeEach
    void setUp() throws IOException {
        service = new DashboardService(new SalesDataLoader());
    }

    private List<SalesRecord> sampleRecords() {
        return List.of(
                new SalesRecord(LocalDate.of(2026, 1, 5), "Electronics", "Nairobi", 1000),
                new SalesRecord(LocalDate.of(2026, 1, 15), "Electronics", "Mombasa", 500),
                new SalesRecord(LocalDate.of(2026, 2, 5), "Clothing", "Nairobi", 300),
                new SalesRecord(LocalDate.of(2026, 2, 10), "Clothing", "Mombasa", 200)
        );
    }

    @Test
    void totalRevenueSumsEveryRecordsAmount() {
        assertEquals(2000.0, service.totalRevenue(sampleRecords()), 0.01);
    }

    @Test
    void revenueByCategoryGroupsAndSumsCorrectly() {
        Map<String, Double> result = service.revenueByCategory(sampleRecords());

        assertEquals(1500.0, result.get("Electronics"), 0.01);
        assertEquals(500.0, result.get("Clothing"), 0.01);
    }

    @Test
    void revenueByRegionGroupsAndSumsCorrectly() {
        Map<String, Double> result = service.revenueByRegion(sampleRecords());

        assertEquals(1300.0, result.get("Nairobi"), 0.01);
        assertEquals(700.0, result.get("Mombasa"), 0.01);
    }

    @Test
    void revenueByMonthGroupsChronologicallyByYearMonth() {
        Map<String, Double> result = service.revenueByMonth(sampleRecords());

        List<String> keysInOrder = List.copyOf(result.keySet());
        assertEquals(List.of("2026-01", "2026-02"), keysInOrder);
        assertEquals(1500.0, result.get("2026-01"), 0.01);
        assertEquals(500.0, result.get("2026-02"), 0.01);
    }

    @Test
    void filterByCategoryOnlyReturnsMatchingRecords() {
        List<SalesRecord> filtered = service.filter("Electronics", null);
        assertTrue(filtered.stream().allMatch(r -> r.category().equals("Electronics")));
        assertFalse(filtered.isEmpty());
    }

    @Test
    void filterByRegionOnlyReturnsMatchingRecords() {
        List<SalesRecord> filtered = service.filter(null, "Nairobi");
        assertTrue(filtered.stream().allMatch(r -> r.region().equals("Nairobi")));
        assertFalse(filtered.isEmpty());
    }

    @Test
    void filterByBothCategoryAndRegionNarrowsFurther() {
        List<SalesRecord> both = service.filter("Electronics", "Nairobi");
        List<SalesRecord> categoryOnly = service.filter("Electronics", null);

        assertTrue(both.size() <= categoryOnly.size());
        assertTrue(both.stream().allMatch(r -> r.category().equals("Electronics") && r.region().equals("Nairobi")));
    }

    @Test
    void blankFiltersActLikeNoFilter() {
        assertEquals(service.filter(null, null).size(), service.filter("", "").size());
    }

    @Test
    void distinctCategoriesAndRegionsAreSortedAndNonEmpty() {
        List<String> categories = service.distinctCategories();
        List<String> regions = service.distinctRegions();

        assertFalse(categories.isEmpty());
        assertFalse(regions.isEmpty());
        assertEquals(categories.stream().sorted().toList(), categories);
        assertEquals(regions.stream().sorted().toList(), regions);
    }
}
