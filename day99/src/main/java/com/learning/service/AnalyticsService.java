package com.learning.service;

import com.learning.model.SalesRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.util.List;

@Service
public class AnalyticsService {

    private final List<SalesRecord> records;

    public AnalyticsService(SalesDataLoader dataLoader) {
        try {
            this.records = dataLoader.load("sales-data.csv");
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load sales-data.csv", e);
        }
    }

    public List<SalesRecord> getRecords() {
        return records;
    }
}
