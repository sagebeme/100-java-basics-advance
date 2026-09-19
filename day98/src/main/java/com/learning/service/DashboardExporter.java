package com.learning.service;

import com.learning.model.SalesRecord;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardExporter {

    public String toCsv(List<SalesRecord> records) {
        String rows = records.stream()
                .map(r -> "%s,%s,%s,%.2f".formatted(r.date(), r.category(), r.region(), r.amount()))
                .collect(Collectors.joining("\n"));
        return "date,category,region,amount\n" + rows + "\n";
    }

    public String toJson(List<SalesRecord> records) {
        String items = records.stream()
                .map(r -> "{\"date\":\"%s\",\"category\":\"%s\",\"region\":\"%s\",\"amount\":%.2f}"
                        .formatted(r.date(), r.category(), r.region(), r.amount()))
                .collect(Collectors.joining(", "));
        return "[" + items + "]";
    }
}
