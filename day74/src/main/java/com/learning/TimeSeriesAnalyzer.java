package com.learning;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TimeSeriesAnalyzer {

    public Map<LocalDate, Double> aggregateByDate(List<DataPoint> points) {
        return points.stream()
                .collect(Collectors.groupingBy(DataPoint::date, Collectors.averagingDouble(DataPoint::value)));
    }

    public Map<YearMonth, Double> aggregateByMonth(List<DataPoint> points) {
        return points.stream().collect(Collectors.groupingBy(
                p -> YearMonth.from(p.date()),
                Collectors.summingDouble(DataPoint::value)));
    }

    /**
     * A simple moving average: each output value is the mean of that point and the
     * (windowSize - 1) points before it. The first (windowSize - 1) points have no full
     * window yet, so they are left out rather than padded with a misleading partial average.
     */
    public List<Double> movingAverage(List<DataPoint> points, int windowSize) {
        List<Double> result = new ArrayList<>();
        for (int i = windowSize - 1; i < points.size(); i++) {
            double sum = 0;
            for (int j = i - windowSize + 1; j <= i; j++) {
                sum += points.get(j).value();
            }
            result.add(sum / windowSize);
        }
        return result;
    }

    /**
     * The slope of the least-squares line through the series, treating each point's x as the
     * number of days since the first point. Positive means trending up, negative means down.
     */
    public double linearTrendSlope(List<DataPoint> points) {
        if (points.size() < 2) {
            return 0;
        }
        LocalDate start = points.get(0).date();
        int n = points.size();

        double sumX = 0, sumY = 0, sumXY = 0, sumXX = 0;
        for (DataPoint p : points) {
            double x = ChronoUnit.DAYS.between(start, p.date());
            double y = p.value();
            sumX += x;
            sumY += y;
            sumXY += x * y;
            sumXX += x * x;
        }

        double denominator = n * sumXX - sumX * sumX;
        if (denominator == 0) {
            return 0;
        }
        return (n * sumXY - sumX * sumY) / denominator;
    }

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2026, 1, 1);
        List<DataPoint> points = List.of(
                new DataPoint(start, 100),
                new DataPoint(start.plusDays(1), 110),
                new DataPoint(start.plusDays(2), 105),
                new DataPoint(start.plusDays(3), 120),
                new DataPoint(start.plusDays(4), 130)
        );

        TimeSeriesAnalyzer analyzer = new TimeSeriesAnalyzer();
        System.out.println("3-day moving average: " + analyzer.movingAverage(points, 3));
        System.out.printf("Linear trend slope: %.2f per day%n", analyzer.linearTrendSlope(points));
    }
}
