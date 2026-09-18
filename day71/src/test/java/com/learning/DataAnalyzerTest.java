package com.learning;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataAnalyzerTest {

    private final DataAnalyzer analyzer = new DataAnalyzer();

    @Test
    void loadDataParsesEachRowSkippingTheHeader() throws IOException {
        String csv = "month,amount\nJan,1000\nFeb,1200\n";
        List<SaleRecord> records = analyzer.loadData(csv);

        assertEquals(2, records.size());
        assertEquals("Jan", records.get(0).month());
        assertEquals(1000.0, records.get(0).amount());
    }

    @Test
    void calculatesMeanMaxMinMedianAndStdDev() {
        Statistics stats = analyzer.calculateStats(List.of(10.0, 20.0, 30.0, 40.0));

        assertEquals(25.0, stats.mean());
        assertEquals(40.0, stats.max());
        assertEquals(10.0, stats.min());
        assertEquals(25.0, stats.median());
        assertEquals(11.180, stats.standardDeviation(), 0.001);
    }

    @Test
    void medianOfAnOddCountIsTheMiddleValue() {
        Statistics stats = analyzer.calculateStats(List.of(5.0, 1.0, 3.0));
        assertEquals(3.0, stats.median());
    }

    @Test
    void emptyDataProducesAllZeroStatisticsInsteadOfThrowing() {
        Statistics stats = analyzer.calculateStats(List.of());
        assertEquals(0, stats.mean());
        assertEquals(0, stats.max());
    }

    @Test
    void recognisesAnUpwardTrend() {
        assertEquals(DataAnalyzer.Trend.UP, analyzer.findTrend(List.of(100.0, 100.0, 200.0, 200.0)));
    }

    @Test
    void recognisesADownwardTrend() {
        assertEquals(DataAnalyzer.Trend.DOWN, analyzer.findTrend(List.of(200.0, 200.0, 100.0, 100.0)));
    }

    @Test
    void recognisesAFlatTrend() {
        assertEquals(DataAnalyzer.Trend.FLAT, analyzer.findTrend(List.of(100.0, 100.0, 100.0, 100.0)));
    }

    @Test
    void reportIncludesTheKeyNumbers() {
        String report = analyzer.generateReport(List.of(new SaleRecord("Jan", 100), new SaleRecord("Feb", 200)));
        assertTrue(report.contains("Records: 2"));
        assertTrue(report.contains("Mean: 150.00"));
    }
}
