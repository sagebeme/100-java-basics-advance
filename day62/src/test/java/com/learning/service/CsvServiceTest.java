package com.learning.service;

import com.learning.model.Contact;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CsvServiceTest {

    private final CsvService service = new CsvService();

    @Test
    void writesAHeaderAndOneLinePerContact() {
        String csv = service.toCsv(List.of(new Contact("Amina", "amina@example.com", "0700000001")));
        String expected = "Name,Email,Phone\n" + "Amina,amina@example.com,0700000001" + System.lineSeparator();
        assertEquals(expected, csv);
    }

    @Test
    void parsesValidRowsIntoContacts() throws IOException {
        String csv = "Name,Email,Phone\nAmina,amina@example.com,0700000001\nKip,kip@example.com,0700000002\n";
        CsvService.ImportResult result = service.parseCsv(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)));

        assertEquals(2, result.imported.size());
        assertEquals(0, result.errors.size());
        assertEquals("Amina", result.imported.get(0).getName());
    }

    @Test
    void reportsARowMissingAnEmailAsAnErrorInsteadOfImportingIt() throws IOException {
        String csv = "Name,Email,Phone\nAmina,,0700000001\n";
        CsvService.ImportResult result = service.parseCsv(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)));

        assertEquals(0, result.imported.size());
        assertEquals(1, result.errors.size());
        assertTrue(result.errors.get(0).contains("Row 2"));
    }

    @Test
    void skipsBlankLinesWithoutReportingThemAsErrors() throws IOException {
        String csv = "Name,Email,Phone\nAmina,amina@example.com,0700000001\n\n";
        CsvService.ImportResult result = service.parseCsv(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)));

        assertEquals(1, result.imported.size());
        assertEquals(0, result.errors.size());
    }
}
