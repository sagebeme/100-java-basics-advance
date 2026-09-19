package com.learning.service;

import com.learning.model.SalesRecord;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReportGeneratorTest {

    private final AdvancedStatistics statistics = new AdvancedStatistics();
    private final ReportGenerator reportGenerator = new ReportGenerator(
            statistics, new RevenueForecaster(), new InsightGenerator(statistics));

    private List<SalesRecord> sampleRecords() {
        return List.of(
                new SalesRecord(LocalDate.of(2026, 1, 1), "Electronics", "Nairobi", 1000),
                new SalesRecord(LocalDate.of(2026, 2, 1), "Electronics", "Nairobi", 1200),
                new SalesRecord(LocalDate.of(2026, 3, 1), "Clothing", "Mombasa", 800));
    }

    @Test
    void producesARealPdfFile() throws IOException {
        byte[] pdf = reportGenerator.generatePdfReport(sampleRecords());

        assertTrue(pdf.length > 100);
        assertEquals('%', (char) pdf[0]);
        assertEquals('P', (char) pdf[1]);
        assertEquals('D', (char) pdf[2]);
        assertEquals('F', (char) pdf[3]);
    }

    @Test
    void pdfTextContainsTheTitleTotalAndInsights() throws IOException {
        byte[] pdf = reportGenerator.generatePdfReport(sampleRecords());

        try (PDDocument document = Loader.loadPDF(pdf)) {
            String text = new PDFTextStripper().getText(document);

            assertTrue(text.contains("Advanced Analytics Report"));
            assertTrue(text.contains("Total revenue"));
            assertTrue(text.contains("Top category: Electronics"));
            assertTrue(text.contains("3-Month Forecast"));
        }
    }
}
