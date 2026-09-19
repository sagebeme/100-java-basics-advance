package com.learning;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class DashboardTest {

    private Map<String, Double> sampleData() {
        Map<String, Double> data = new LinkedHashMap<>();
        data.put("Jan", 1000.0);
        data.put("Feb", 1200.0);
        data.put("Mar", 900.0);
        return data;
    }

    @Test
    void filterAboveKeepsOnlyMatchingEntries() {
        Dashboard dashboard = new Dashboard(sampleData());
        dashboard.filterAbove(1000.0);

        assertEquals(2, dashboard.getData().size());
        assertTrue(dashboard.getData().containsKey("Feb"));
        assertFalse(dashboard.getData().containsKey("Mar"));
    }

    @Test
    void everyChartTypeCarriesTheGivenTitle() {
        Dashboard dashboard = new Dashboard(sampleData());
        assertEquals("Sales", dashboard.bar("Sales").getTitle().getText());
        assertEquals("Sales", dashboard.line("Sales").getTitle().getText());
        assertEquals("Sales", dashboard.pie("Sales").getTitle().getText());
        assertEquals("Sales", dashboard.area("Sales").getTitle().getText());
    }

    @Test
    void exportAllProducesFourRealPngFiles(@TempDir File tempDir) throws IOException {
        Dashboard dashboard = new Dashboard(sampleData());
        Map<String, File> exported = dashboard.exportAll(tempDir, "Sales");

        assertEquals(4, exported.size());
        byte[] pngSignature = {(byte) 0x89, 'P', 'N', 'G', '\r', '\n', 0x1A, '\n'};

        for (File file : exported.values()) {
            assertTrue(file.exists(), file.getName() + " should exist");
            assertTrue(file.length() > 0, file.getName() + " should not be empty");
            byte[] header = new byte[8];
            try (var in = Files.newInputStream(file.toPath())) {
                in.read(header);
            }
            assertArrayEquals(pngSignature, header, file.getName() + " should be a real PNG");
        }
    }

    @Test
    void exportingAfterFilteringStillProducesValidFiles(@TempDir File tempDir) throws IOException {
        Dashboard dashboard = new Dashboard(sampleData());
        dashboard.filterAbove(1000.0);
        assertEquals(2, dashboard.getData().size());

        Map<String, File> exported = dashboard.exportAll(tempDir, "Filtered");

        assertTrue(exported.get("bar").length() > 0);
        assertTrue(exported.get("pie").length() > 0);
    }
}
