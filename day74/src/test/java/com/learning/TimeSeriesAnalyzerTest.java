package com.learning;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TimeSeriesAnalyzerTest {

    private final TimeSeriesAnalyzer analyzer = new TimeSeriesAnalyzer();
    private final LocalDate start = LocalDate.of(2026, 1, 1);

    private List<DataPoint> series() {
        return List.of(
                new DataPoint(start, 100),
                new DataPoint(start.plusDays(1), 110),
                new DataPoint(start.plusDays(2), 105),
                new DataPoint(start.plusDays(3), 120),
                new DataPoint(start.plusDays(4), 130)
        );
    }

    @Test
    void averagesMultipleReadingsOnTheSameDate() {
        List<DataPoint> points = List.of(
                new DataPoint(start, 100),
                new DataPoint(start, 200)
        );
        Map<LocalDate, Double> result = analyzer.aggregateByDate(points);
        assertEquals(150.0, result.get(start));
    }

    @Test
    void sumsValuesWithinTheSameMonth() {
        List<DataPoint> points = List.of(
                new DataPoint(LocalDate.of(2026, 1, 5), 100),
                new DataPoint(LocalDate.of(2026, 1, 20), 200),
                new DataPoint(LocalDate.of(2026, 2, 1), 50)
        );
        Map<YearMonth, Double> result = analyzer.aggregateByMonth(points);
        assertEquals(300.0, result.get(YearMonth.of(2026, 1)));
        assertEquals(50.0, result.get(YearMonth.of(2026, 2)));
    }

    @Test
    void movingAverageProducesOneValuePerFullWindow() {
        List<Double> result = analyzer.movingAverage(series(), 3);
        assertEquals(3, result.size());
        assertEquals(105.0, result.get(0), 0.001);
        assertEquals(111.667, result.get(1), 0.001);
        assertEquals(118.333, result.get(2), 0.001);
    }

    @Test
    void aWindowLargerThanTheDataProducesNoAverages() {
        assertTrue(analyzer.movingAverage(series(), 10).isEmpty());
    }

    @Test
    void slopeIsPositiveForAnUpwardTrend() {
        assertEquals(7.0, analyzer.linearTrendSlope(series()), 0.001);
    }

    @Test
    void slopeIsNegativeForADownwardTrend() {
        List<DataPoint> declining = List.of(
                new DataPoint(start, 100),
                new DataPoint(start.plusDays(1), 90),
                new DataPoint(start.plusDays(2), 80)
        );
        assertTrue(analyzer.linearTrendSlope(declining) < 0);
    }

    @Test
    void slopeIsZeroForAFlatSeries() {
        List<DataPoint> flat = List.of(
                new DataPoint(start, 100),
                new DataPoint(start.plusDays(1), 100),
                new DataPoint(start.plusDays(2), 100)
        );
        assertEquals(0.0, analyzer.linearTrendSlope(flat), 0.001);
    }
}
