package com.learning.service;

import com.learning.model.Contact;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvService {

    public String toCsv(List<Contact> contacts) {
        StringBuilder csv = new StringBuilder("Name,Email,Phone\n");
        for (Contact c : contacts) {
            csv.append(String.format("%s,%s,%s%n", c.getName(), c.getEmail(), c.getPhone()));
        }
        return csv.toString();
    }

    public static class ImportResult {
        public final List<Contact> imported = new ArrayList<>();
        public final List<String> errors = new ArrayList<>();
    }

    /**
     * Parses a CSV with a header row "Name,Email,Phone". A row missing a name or email is
     * reported as an error instead of silently dropped or silently imported.
     */
    public ImportResult parseCsv(InputStream input) throws IOException {
        ImportResult result = new ImportResult();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            String header = reader.readLine();
            String line;
            int rowNumber = 1;
            while ((line = reader.readLine()) != null) {
                rowNumber++;
                if (line.isBlank()) continue;
                String[] parts = line.split(",", -1);
                String name = parts.length > 0 ? parts[0].trim() : "";
                String email = parts.length > 1 ? parts[1].trim() : "";
                String phone = parts.length > 2 ? parts[2].trim() : "";

                if (name.isEmpty() || email.isEmpty()) {
                    result.errors.add("Row " + rowNumber + ": name and email are required");
                    continue;
                }
                result.imported.add(new Contact(name, email, phone));
            }
        }
        return result;
    }
}
