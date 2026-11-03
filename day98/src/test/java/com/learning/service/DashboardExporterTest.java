package com.learning.service;

import com.learning.model.SalesRecord;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

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

    @Test
    void amountsUseADecimalPointOnAnyComputer() {
        Locale before = Locale.getDefault();
        Locale.setDefault(Locale.GERMANY); // Germany writes 66150,00
        try {
            assertTrue(exporter.toCsv(records).contains("Nairobi,66150.00"));
            assertTrue(exporter.toJson(records).contains("\"amount\":66150.00"));
        } finally {
            Locale.setDefault(before);
        }
    }

    @Test
    void textWithCommasOrQuotesStaysInItsColumnAndItsJsonString() {
        List<SalesRecord> odd = List.of(
                new SalesRecord(LocalDate.of(2026, 2, 1), "Home, Kitchen", "Nai\"robi", 10.5));

        assertTrue(exporter.toCsv(odd).contains("2026-02-01,\"Home, Kitchen\",\"Nai\"\"robi\",10.50"));
        assertEquals("[{\"date\":\"2026-02-01\",\"category\":\"Home, Kitchen\",\"region\":\"Nai\\\"robi\",\"amount\":10.50}]",
                exporter.toJson(odd));
    }
}
