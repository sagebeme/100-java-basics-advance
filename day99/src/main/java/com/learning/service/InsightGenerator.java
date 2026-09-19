package com.learning.service;

import com.learning.model.SalesRecord;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Produces plain-language insights computed directly from the data - every sentence here is
 * backed by an actual number, not a canned template filled with placeholder text.
 */
@Component
public class InsightGenerator {

    private final AdvancedStatistics statistics;

    public InsightGenerator(AdvancedStatistics statistics) {
        this.statistics = statistics;
    }

    public List<String> generate(List<SalesRecord> records) {
        List<String> insights = new ArrayList<>();
        if (records.isEmpty()) {
            insights.add("No data available to generate insights.");
            return insights;
        }

        double total = records.stream().mapToDouble(SalesRecord::amount).sum();

        Map<String, Double> byCategory = records.stream()
                .collect(Collectors.groupingBy(SalesRecord::category, Collectors.summingDouble(SalesRecord::amount)));
        Map.Entry<String, Double> topCategory = byCategory.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow();
        insights.add("Top category: %s (KES %,.0f, %.1f%% of total revenue)".formatted(
                topCategory.getKey(), topCategory.getValue(), topCategory.getValue() / total * 100));

        Map<String, Double> byRegion = records.stream()
                .collect(Collectors.groupingBy(SalesRecord::region, Collectors.summingDouble(SalesRecord::amount)));
        Map.Entry<String, Double> topRegion = byRegion.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow();
        insights.add("Top region: %s (KES %,.0f, %.1f%% of total revenue)".formatted(
                topRegion.getKey(), topRegion.getValue(), topRegion.getValue() / total * 100));

        Map<String, Double> monthlyTotals = statistics.monthlyTotals(records);
        Map.Entry<String, Double> bestMonth = monthlyTotals.entrySet().stream()
                .max(Map.Entry.comparingByValue()).orElseThrow();
        Map.Entry<String, Double> weakestMonth = monthlyTotals.entrySet().stream()
                .min(Map.Entry.comparingByValue()).orElseThrow();
        insights.add("Strongest month: %s (KES %,.0f)".formatted(bestMonth.getKey(), bestMonth.getValue()));
        insights.add("Weakest month: %s (KES %,.0f)".formatted(weakestMonth.getKey(), weakestMonth.getValue()));

        Map<String, Double> growthRates = statistics.monthOverMonthGrowth(monthlyTotals);
        if (!growthRates.isEmpty()) {
            double averageGrowth = statistics.mean(new ArrayList<>(growthRates.values()));
            String direction = averageGrowth >= 0 ? "growing" : "declining";
            insights.add("Revenue is %s on average, %.1f%% month-over-month".formatted(direction, averageGrowth));
        }

        double stdDev = statistics.standardDeviation(new ArrayList<>(monthlyTotals.values()));
        double mean = statistics.mean(new ArrayList<>(monthlyTotals.values()));
        insights.add("Monthly revenue averages KES %,.0f with a standard deviation of KES %,.0f".formatted(mean, stdDev));

        return insights;
    }
}
