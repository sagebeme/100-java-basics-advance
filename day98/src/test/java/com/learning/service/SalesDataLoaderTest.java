package com.learning.service;

import com.learning.model.SalesRecord;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SalesDataLoaderTest {

    private final SalesDataLoader loader = new SalesDataLoader();

    @Test
    void loadsEveryDataRowFromTheBundledCsv() throws IOException {
        List<SalesRecord> records = loader.load("sales-data.csv");

        assertEquals(48, records.size());
    }

    @Test
    void parsesTheFirstRowCorrectly() throws IOException {
        List<SalesRecord> records = loader.load("sales-data.csv");

        SalesRecord first = records.get(0);
        assertEquals(LocalDate.of(2026, 1, 10), first.date());
        assertEquals("Electronics", first.category());
        assertEquals("Nairobi", first.region());
        assertEquals(66150.0, first.amount(), 0.01);
    }

    @Test
    void everyRecordHasAPositiveAmount() throws IOException {
        List<SalesRecord> records = loader.load("sales-data.csv");
        assertTrue(records.stream().allMatch(r -> r.amount() > 0));
    }
}
