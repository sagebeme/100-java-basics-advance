package com.learning.service;

import com.learning.model.SalesRecord;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class AdvancedStatistics {

    private static final DateTimeFormatter MONTH_KEY = DateTimeFormatter.ofPattern("yyyy-MM");

    /** Monthly revenue totals, sorted chronologically. */
    public Map<String, Double> monthlyTotals(List<SalesRecord> records) {
        Map<String, Double> byMonth = records.stream()
                .collect(Collectors.groupingBy(r -> r.date().format(MONTH_KEY), Collectors.summingDouble(SalesRecord::amount)));
        Map<String, Double> sorted = new LinkedHashMap<>();
        byMonth.keySet().stream().sorted().forEach(key -> sorted.put(key, byMonth.get(key)));
        return sorted;
    }

    public double mean(List<Double> values) {
        if (values.isEmpty()) {
            return 0;
        }
        return values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
    }

    public double standardDeviation(List<Double> values) {
        if (values.size() < 2) {
            return 0;
        }
        double avg = mean(values);
        double variance = values.stream().mapToDouble(v -> (v - avg) * (v - avg)).sum() / (values.size() - 1);
        return Math.sqrt(variance);
    }

    /**
     * Month-over-month percentage growth, keyed by the *later* month in each pair. The first
     * month in the series has no prior month to compare against, so it's excluded rather than
     * reported as a fabricated 0% growth.
     */
    public Map<String, Double> monthOverMonthGrowth(Map<String, Double> monthlyTotals) {
        Map<String, Double> growth = new LinkedHashMap<>();
        Double previous = null;
        for (Map.Entry<String, Double> entry : monthlyTotals.entrySet()) {
            if (previous != null && previous != 0) {
                growth.put(entry.getKey(), ((entry.getValue() - previous) / previous) * 100.0);
            }
            previous = entry.getValue();
        }
        return growth;
    }
}
