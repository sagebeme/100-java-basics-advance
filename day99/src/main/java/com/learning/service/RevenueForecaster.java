package com.learning.service;

import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.LinkedHashMap;
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

    /**
     * Fits revenue against the month's position in the calendar (months since the first month),
     * not its position in the map, so a month with no sales at all leaves a gap instead of
     * pulling the later months one step back.
     */
    public TrendLine fitTrend(Map<String, Double> monthlyTotals) {
        int n = monthlyTotals.size();
        if (n < 2) {
            throw new IllegalArgumentException("Need at least 2 months of data to fit a trend");
        }
        YearMonth first = firstMonth(monthlyTotals);
        double[] xs = new double[n];
        double[] ys = new double[n];
        int i = 0;
        for (Map.Entry<String, Double> entry : monthlyTotals.entrySet()) {
            xs[i] = monthsBetween(first, YearMonth.parse(entry.getKey(), MONTH_KEY));
            ys[i] = entry.getValue();
            i++;
        }

        double xMean = Arrays.stream(xs).average().orElse(0);
        double yMean = Arrays.stream(ys).average().orElse(0);

        double numerator = 0;
        double denominator = 0;
        for (int k = 0; k < n; k++) {
            numerator += (xs[k] - xMean) * (ys[k] - yMean);
            denominator += (xs[k] - xMean) * (xs[k] - xMean);
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
        YearMonth first = firstMonth(monthlyTotals);
        YearMonth lastMonth = monthlyTotals.keySet().stream()
                .map(key -> YearMonth.parse(key, MONTH_KEY)).max(YearMonth::compareTo).orElseThrow();

        Map<String, Double> forecast = new LinkedHashMap<>();
        for (int i = 1; i <= monthsAhead; i++) {
            YearMonth futureMonth = lastMonth.plusMonths(i);
            double predicted = trend.predict(monthsBetween(first, futureMonth));
            forecast.put(futureMonth.format(MONTH_KEY), Math.max(0, predicted));
        }
        return forecast;
    }

    private static YearMonth firstMonth(Map<String, Double> monthlyTotals) {
        return monthlyTotals.keySet().stream()
                .map(key -> YearMonth.parse(key, MONTH_KEY)).min(YearMonth::compareTo).orElseThrow();
    }

    private static int monthsBetween(YearMonth from, YearMonth to) {
        return (int) ChronoUnit.MONTHS.between(from, to);
    }
}
