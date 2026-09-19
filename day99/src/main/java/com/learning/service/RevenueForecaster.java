package com.learning.service;

import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Simple linear regression (least squares) over month index vs. revenue total - the same
 * underlying math as day80's house price predictor, applied to a time series instead.
 */
@Component
public class RevenueForecaster {

    private static final DateTimeFormatter MONTH_KEY = DateTimeFormatter.ofPattern("yyyy-MM");

    public record TrendLine(double slope, double intercept) {
        public double predict(int monthIndex) {
            return intercept + slope * monthIndex;
        }
    }

    public TrendLine fitTrend(Map<String, Double> monthlyTotals) {
        List<Double> values = new ArrayList<>(monthlyTotals.values());
        int n = values.size();
        if (n < 2) {
            throw new IllegalArgumentException("Need at least 2 months of data to fit a trend");
        }

        double xMean = (n - 1) / 2.0;
        double yMean = values.stream().mapToDouble(Double::doubleValue).average().orElse(0);

        double numerator = 0;
        double denominator = 0;
        for (int i = 0; i < n; i++) {
            numerator += (i - xMean) * (values.get(i) - yMean);
            denominator += (i - xMean) * (i - xMean);
        }
        double slope = denominator == 0 ? 0 : numerator / denominator;
        double intercept = yMean - slope * xMean;
        return new TrendLine(slope, intercept);
    }

    /**
     * Forecasts the next monthsAhead months following the last month present in monthlyTotals,
     * using a trend line fit to the existing data.
     */
    public Map<String, Double> forecast(Map<String, Double> monthlyTotals, int monthsAhead) {
        TrendLine trend = fitTrend(monthlyTotals);
        int n = monthlyTotals.size();
        String lastMonthKey = monthlyTotals.keySet().stream().reduce((first, second) -> second).orElseThrow();
        YearMonth lastMonth = YearMonth.parse(lastMonthKey, MONTH_KEY);

        Map<String, Double> forecast = new LinkedHashMap<>();
        for (int i = 1; i <= monthsAhead; i++) {
            YearMonth futureMonth = lastMonth.plusMonths(i);
            double predicted = trend.predict(n - 1 + i);
            forecast.put(futureMonth.format(MONTH_KEY), Math.max(0, predicted));
        }
        return forecast;
    }
}
