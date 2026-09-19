package com.learning.service;

import com.learning.model.SalesRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DashboardExporterTest {

    private final DashboardExporter exporter = new DashboardExporter();
    private final List<SalesRecord> records = List.of(
            new SalesRecord(LocalDate.of(2026, 1, 10), "Electronics", "Nairobi", 66150.0));

    @Test
    void toCsvIncludesHeaderAndRow() {
        String csv = exporter.toCsv(records);

        assertTrue(csv.startsWith("date,category,region,amount"));
        assertTrue(csv.contains("2026-01-10,Electronics,Nairobi,66150.00"));
    }

    @Test
    void toJsonProducesAnArrayOfObjects() {
        String json = exporter.toJson(records);

        assertEquals("[{\"date\":\"2026-01-10\",\"category\":\"Electronics\",\"region\":\"Nairobi\",\"amount\":66150.00}]", json);
    }
}
