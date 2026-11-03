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
        String expected = "Name,Email,Phone\n" + "Amina,amina@example.com,0700000001\n";
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

    @Test
    void quotesAValueWithACommaOrAQuote() {
        String csv = service.toCsv(List.of(new Contact("Mama Njeri, Kayole", "njeri@example.com", "0711 000 111"),
                new Contact("Otieno \"Oti\" Omondi", "oti@example.com", "")));
        assertTrue(csv.contains("\"Mama Njeri, Kayole\",njeri@example.com,0711 000 111\n"));
        assertTrue(csv.contains("\"Otieno \"\"Oti\"\" Omondi\",oti@example.com,\n"));
    }

    @Test
    void aFormulaIsWrittenAsTextButAPhoneNumberIsNot() {
        // A ' in front, then quoted because of its quotes.
        assertEquals("\"'=HYPERLINK(\"\"http://x\"\")\"", CsvService.cell("=HYPERLINK(\"http://x\")"));
        assertEquals("'@SUM(A1)", CsvService.cell("@SUM(A1)"));
        assertEquals("+254 712 345678", CsvService.cell("+254 712 345678"));
        assertEquals("'-2+3", CsvService.cell("-2+3"));
    }

    @Test
    void whatItWritesItReadsBack() throws IOException {
        List<Contact> tricky = List.of(new Contact("Mama Njeri, Kayole", "njeri@example.com", "+254 712 345678"),
                new Contact("=CMD()", "a\"b@example.com", "0700"));
        CsvService.ImportResult result = service.parseCsv(
                new ByteArrayInputStream(service.toCsv(tricky).getBytes(StandardCharsets.UTF_8)));

        assertEquals(0, result.errors.size());
        assertEquals("Mama Njeri, Kayole", result.imported.get(0).getName());
        assertEquals("+254 712 345678", result.imported.get(0).getPhone());
        assertEquals("=CMD()", result.imported.get(1).getName());
        assertEquals("a\"b@example.com", result.imported.get(1).getEmail());
    }

    @Test
    void anUnclosedQuoteIsAnErrorForThatRowOnly() throws IOException {
        String csv = "Name,Email,Phone\n\"Amina,amina@example.com,0700\nKip,kip@example.com,0701\n";
        CsvService.ImportResult result = service.parseCsv(new ByteArrayInputStream(csv.getBytes(StandardCharsets.UTF_8)));

        assertEquals(1, result.imported.size());
        assertEquals("Row 2: a quote is never closed", result.errors.get(0));
    }
}
