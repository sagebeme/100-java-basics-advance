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
import java.util.regex.Pattern;

@Service
public class CsvService {

    private static final Pattern PHONE_OR_NUMBER = Pattern.compile("[+-][0-9 ]+");

    public String toCsv(List<Contact> contacts) {
        StringBuilder csv = new StringBuilder("Name,Email,Phone\n");
        for (Contact c : contacts) {
            csv.append(cell(c.getName())).append(',')
               .append(cell(c.getEmail())).append(',')
               .append(cell(c.getPhone())).append('\n');
        }
        return csv.toString();
    }

    /**
     * One value, made safe for a CSV file that people will open in Excel or Google Sheets.
     * <ul>
     *   <li>A value with a comma, a quote or a line break goes in quotes, with its quotes doubled:
     *       {@code Mama Njeri, Kayole} becomes {@code "Mama Njeri, Kayole"}.</li>
     *   <li>A value that a spreadsheet would run as a formula ({@code =HYPERLINK(...)}, {@code @SUM(...)})
     *       gets a {@code '} in front, so it's shown as text. This is "CSV injection": anyone who can
     *       type a contact's name could otherwise put a formula in the office's spreadsheet.
     *       Phone numbers such as {@code +254 712 345678} are left alone.</li>
     * </ul>
     */
    static String cell(String value) {
        if (value == null) {
            return "";
        }
        String text = value;
        if (!text.isEmpty() && "=+-@\t\r".indexOf(text.charAt(0)) >= 0 && !PHONE_OR_NUMBER.matcher(text).matches()) {
            text = "'" + text;
        }
        if (text.contains(",") || text.contains("\"") || text.contains("\n") || text.contains("\r")) {
            text = "\"" + text.replace("\"", "\"\"") + "\"";
        }
        return text;
    }

    public static class ImportResult {
        public final List<Contact> imported = new ArrayList<>();
        public final List<String> errors = new ArrayList<>();
    }

    /**
     * Parses a CSV with a header row "Name,Email,Phone". Fields may be quoted, as {@link #cell}
     * writes them. A row missing a name or email is reported as an error instead of silently
     * dropped or silently imported. Quoted values can't span lines here.
     */
    public ImportResult parseCsv(InputStream input) throws IOException {
        ImportResult result = new ImportResult();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8))) {
            reader.readLine(); // the header
            String line;
            int rowNumber = 1;
            while ((line = reader.readLine()) != null) {
                rowNumber++;
                if (line.isBlank()) continue;
                List<String> parts = splitLine(line);
                if (parts == null) {
                    result.errors.add("Row " + rowNumber + ": a quote is never closed");
                    continue;
                }
                String name = field(parts, 0);
                String email = field(parts, 1);
                String phone = field(parts, 2);

                if (name.isEmpty() || email.isEmpty()) {
                    result.errors.add("Row " + rowNumber + ": name and email are required");
                    continue;
                }
                result.imported.add(new Contact(name, email, phone));
            }
        }
        return result;
    }

    private static String field(List<String> parts, int i) {
        if (i >= parts.size()) {
            return "";
        }
        String value = parts.get(i).trim();
        // Undo the ' that cell() puts in front of a formula.
        if (value.length() > 1 && value.charAt(0) == '\'' && "=+-@".indexOf(value.charAt(1)) >= 0) {
            value = value.substring(1);
        }
        return value;
    }

    // Splits one line at commas outside quotes; "" inside quotes is one ". Null if a quote isn't closed.
    static List<String> splitLine(String line) {
        if (line.startsWith("﻿")) {
            line = line.substring(1); // the byte-order mark Excel puts at the start of UTF-8 files
        }
        List<String> fields = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        boolean quoted = false;
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (quoted) {
                if (c == '"' && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    current.append('"');
                    i++;
                } else if (c == '"') {
                    quoted = false;
                } else {
                    current.append(c);
                }
            } else if (c == '"') {
                quoted = true;
            } else if (c == ',') {
                fields.add(current.toString());
                current.setLength(0);
            } else {
                current.append(c);
            }
        }
        if (quoted) {
            return null;
        }
        fields.add(current.toString());
        return fields;
    }
}
