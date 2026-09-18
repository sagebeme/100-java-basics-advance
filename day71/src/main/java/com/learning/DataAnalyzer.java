package com.learning;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataAnalyzer {

    public enum Trend { UP, DOWN, FLAT }

    /**
     * Parses "month,amount" CSV content (with a header row) into sale records.
     */
    public List<SaleRecord> loadData(String csvContent) throws IOException {
        List<SaleRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new StringReader(csvContent))) {
            reader.readLine(); // header
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) continue;
                String[] parts = line.split(",");
                records.add(new SaleRecord(parts[0].trim(), Double.parseDouble(parts[1].trim())));
            }
        }
        return records;
    }

    public Statistics calculateStats(List<Double> values) {
        if (values.isEmpty()) {
            return new Statistics(0, 0, 0, 0, 0);
        }
        double mean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double max = Collections.max(values);
        double min = Collections.min(values);

        List<Double> sorted = values.stream().sorted().toList();
        int n = sorted.size();
        double median = (n % 2 == 0)
                ? (sorted.get(n / 2 - 1) + sorted.get(n / 2)) / 2.0
                : sorted.get(n / 2);

        double variance = values.stream().mapToDouble(v -> Math.pow(v - mean, 2)).average().orElse(0);
        double standardDeviation = Math.sqrt(variance);

        return new Statistics(mean, max, min, median, standardDeviation);
    }

    /**
     * Compares the average of the first half of the series against the second half to say
     * whether sales are trending up, down, or flat (within 1% of each other).
     */
    public Trend findTrend(List<Double> values) {
        if (values.size() < 2) {
            return Trend.FLAT;
        }
        int mid = values.size() / 2;
        double firstHalfAvg = values.subList(0, mid).stream().mapToDouble(Double::doubleValue).average().orElse(0);
        double secondHalfAvg = values.subList(mid, values.size()).stream().mapToDouble(Double::doubleValue).average().orElse(0);

        double change = firstHalfAvg == 0 ? 0 : (secondHalfAvg - firstHalfAvg) / firstHalfAvg;
        if (change > 0.01) return Trend.UP;
        if (change < -0.01) return Trend.DOWN;
        return Trend.FLAT;
    }

    public String generateReport(List<SaleRecord> records) {
        List<Double> amounts = records.stream().map(SaleRecord::amount).toList();
        Statistics stats = calculateStats(amounts);
        Trend trend = findTrend(amounts);

        return String.format(
                "Sales Report%n" +
                "Records: %d%n" +
                "Mean: %.2f%n" +
                "Median: %.2f%n" +
                "Max: %.2f%n" +
                "Min: %.2f%n" +
                "Std Dev: %.2f%n" +
                "Trend: %s%n",
                records.size(), stats.mean(), stats.median(), stats.max(), stats.min(), stats.standardDeviation(), trend);
    }

    public static void main(String[] args) throws IOException {
        String sampleCsv = """
                month,amount
                Jan,1000
                Feb,1200
                Mar,900
                Apr,1500
                May,1800
                Jun,2000
                """;

        DataAnalyzer analyzer = new DataAnalyzer();
        List<SaleRecord> records = analyzer.loadData(sampleCsv);
        System.out.print(analyzer.generateReport(records));
    }
}
