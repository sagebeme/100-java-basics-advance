package com.learning.service;

import com.learning.model.EarningsRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EarningsDataLoader {

    public List<EarningsRecord> load(String classpathFile) throws IOException {
        List<EarningsRecord> records = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(
                new ClassPathResource(classpathFile).getInputStream(), StandardCharsets.UTF_8))) {
            String line = reader.readLine(); // header
            while ((line = reader.readLine()) != null) {
                if (line.isBlank()) {
                    continue;
                }
                String[] parts = line.split(",");
                records.add(new EarningsRecord(
                        Double.parseDouble(parts[0].trim()),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        Double.parseDouble(parts[4].trim())));
            }
        }
        return Collections.unmodifiableList(records);
    }
}
