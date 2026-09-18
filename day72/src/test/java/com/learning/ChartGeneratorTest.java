package com.learning;

import org.jfree.chart.JFreeChart;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ChartGeneratorTest {

    private final ChartGenerator generator = new ChartGenerator();
    private final Map<String, Double> sales = Map.of("Jan", 1000.0, "Feb", 1200.0, "Mar", 900.0);

    @Test
    void createsABarChartWithATitle() {
        JFreeChart chart = generator.createBarChart("Monthly Sales", sales);
        assertEquals("Monthly Sales", chart.getTitle().getText());
    }

    @Test
    void createsALineChart() {
        JFreeChart chart = generator.createLineChart("Trend", sales);
        assertEquals("Trend", chart.getTitle().getText());
    }

    @Test
    void createsAPieChart() {
        JFreeChart chart = generator.createPieChart("Share", sales);
        assertEquals("Share", chart.getTitle().getText());
    }

    @Test
    void filterAboveKeepsOnlyValuesAtOrOverTheThreshold() {
        Map<String, Double> filtered = generator.filterAbove(sales, 1000.0);
        assertEquals(2, filtered.size());
        assertTrue(filtered.containsKey("Jan"));
        assertTrue(filtered.containsKey("Feb"));
        assertFalse(filtered.containsKey("Mar"));
    }

    @Test
    void exportsARealPngFileWithAValidHeader() throws IOException {
        File file = Files.createTempFile("chart", ".png").toFile();
        JFreeChart chart = generator.createBarChart("Test", sales);

        generator.exportToPng(chart, file, 400, 300);

        assertTrue(file.length() > 0, "the PNG file should not be empty");

        byte[] header = new byte[8];
        try (var in = Files.newInputStream(file.toPath())) {
            in.read(header);
        }
        byte[] pngSignature = {(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};
        assertArrayEquals(pngSignature, header, "file should start with the real PNG magic bytes");
    }
}
