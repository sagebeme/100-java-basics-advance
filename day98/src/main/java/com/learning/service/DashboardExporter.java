package com.learning.service;

import com.learning.model.SalesRecord;
import org.springframework.stereotype.Service;
import tools.jackson.databind.json.JsonMapper;
import tools.jackson.databind.node.ArrayNode;
import tools.jackson.databind.node.ObjectNode;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardExporter {

    private static final JsonMapper JSON = JsonMapper.builder().build();

    public String toCsv(List<SalesRecord> records) {
        String rows = records.stream()
                .map(r -> String.join(",",
                        r.date().toString(), csvField(r.category()), csvField(r.region()), money(r.amount()).toPlainString()))
                .collect(Collectors.joining("\n"));
        return "date,category,region,amount\n" + rows + "\n";
    }

    public String toJson(List<SalesRecord> records) {
        // Jackson escapes quotes and backslashes in the text, which a hand-built string would not.
        ArrayNode array = JSON.createArrayNode();
        for (SalesRecord r : records) {
            ObjectNode item = array.addObject();
            item.put("date", r.date().toString());
            item.put("category", r.category());
            item.put("region", r.region());
            item.put("amount", money(r.amount()));
        }
        return JSON.writeValueAsString(array);
    }

    // Two decimal places with a dot, whatever the computer's language settings: "%.2f" would write
    // 66150,00 on a German or French machine, which splits a CSV column and is invalid JSON.
    private static BigDecimal money(double amount) {
        return BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP);
    }

    // A value with a comma, quote or line break is wrapped in quotes, with its quotes doubled (RFC 4180).
    private static String csvField(String value) {
        if (value.contains(",") || value.contains("\"") || value.contains("\n") || value.contains("\r")) {
            return "\"" + value.replace("\"", "\"\"") + "\"";
        }
        return value;
    }
}
