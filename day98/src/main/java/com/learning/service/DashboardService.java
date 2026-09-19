package com.learning.service;

import com.learning.model.SalesRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private static final DateTimeFormatter MONTH_KEY = DateTimeFormatter.ofPattern("yyyy-MM");

    private final List<SalesRecord> allRecords;

    public DashboardService(SalesDataLoader dataLoader) {
        try {
            this.allRecords = dataLoader.load("sales-data.csv");
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load sales-data.csv", e);
        }
    }

    public List<SalesRecord> filter(String category, String region) {
        return allRecords.stream()
                .filter(r -> category == null || category.isBlank() || r.category().equals(category))
                .filter(r -> region == null || region.isBlank() || r.region().equals(region))
                .toList();
    }

    public double totalRevenue(List<SalesRecord> records) {
        return records.stream().mapToDouble(SalesRecord::amount).sum();
    }

    public Map<String, Double> revenueByCategory(List<SalesRecord> records) {
        return new TreeMap<>(records.stream()
                .collect(Collectors.groupingBy(SalesRecord::category, Collectors.summingDouble(SalesRecord::amount))));
    }

    public Map<String, Double> revenueByRegion(List<SalesRecord> records) {
        return new TreeMap<>(records.stream()
                .collect(Collectors.groupingBy(SalesRecord::region, Collectors.summingDouble(SalesRecord::amount))));
    }

    public Map<String, Double> revenueByMonth(List<SalesRecord> records) {
        Map<String, Double> byMonth = records.stream()
                .collect(Collectors.groupingBy(r -> r.date().format(MONTH_KEY), Collectors.summingDouble(SalesRecord::amount)));
        Map<String, Double> sorted = new LinkedHashMap<>();
        byMonth.keySet().stream().sorted().forEach(key -> sorted.put(key, byMonth.get(key)));
        return sorted;
    }

    public List<String> distinctCategories() {
        return allRecords.stream().map(SalesRecord::category).distinct().sorted().toList();
    }

    public List<String> distinctRegions() {
        return allRecords.stream().map(SalesRecord::region).distinct().sorted().toList();
    }
}
