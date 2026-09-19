package com.learning;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SalesAggregator {

    public Map<String, List<Sale>> groupByCategory(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(Sale::category));
    }

    public Map<String, Double> totalsByCategory(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(Sale::category, Collectors.summingDouble(Sale::amount)));
    }

    public Map<String, Double> averagesByCategory(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(Sale::category, Collectors.averagingDouble(Sale::amount)));
    }

    public Map<String, Long> countByCategory(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(Sale::category, Collectors.counting()));
    }

    /**
     * Groups by category, then by region within each category - a two-level aggregation.
     */
    public Map<String, Map<String, Double>> totalsByCategoryAndRegion(List<Sale> sales) {
        return sales.stream().collect(Collectors.groupingBy(
                Sale::category,
                Collectors.groupingBy(Sale::region, Collectors.summingDouble(Sale::amount))));
    }

    public String topCategoryByTotal(List<Sale> sales) {
        return totalsByCategory(sales).entrySet().stream()
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(null);
    }

    public String generateSummary(List<Sale> sales) {
        StringBuilder summary = new StringBuilder("Sales Summary\n");
        Map<String, Double> totals = totalsByCategory(sales);
        Map<String, Double> averages = averagesByCategory(sales);
        Map<String, Long> counts = countByCategory(sales);

        for (String category : totals.keySet().stream().sorted().toList()) {
            summary.append(String.format("%s: total=%.2f, average=%.2f, count=%d%n",
                    category, totals.get(category), averages.get(category), counts.get(category)));
        }
        summary.append("Top category: ").append(topCategoryByTotal(sales)).append("\n");
        return summary.toString();
    }

    public static void main(String[] args) {
        List<Sale> sales = List.of(
                new Sale("Electronics", "Nairobi", 5000),
                new Sale("Electronics", "Mombasa", 3000),
                new Sale("Groceries", "Nairobi", 1200),
                new Sale("Groceries", "Kisumu", 800),
                new Sale("Clothing", "Nairobi", 2000)
        );

        SalesAggregator aggregator = new SalesAggregator();
        System.out.print(aggregator.generateSummary(sales));
    }
}
