package com.learning.service;

import com.learning.model.SalesRecord;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class ReportGenerator {

    private final AdvancedStatistics statistics;
    private final RevenueForecaster forecaster;
    private final InsightGenerator insightGenerator;

    public ReportGenerator(AdvancedStatistics statistics, RevenueForecaster forecaster, InsightGenerator insightGenerator) {
        this.statistics = statistics;
        this.forecaster = forecaster;
        this.insightGenerator = insightGenerator;
    }

    /** Generates a real PDF summarizing totals, insights, and a 3-month forecast. */
    public byte[] generatePdfReport(List<SalesRecord> records) throws IOException {
        double total = records.stream().mapToDouble(SalesRecord::amount).sum();
        List<String> insights = insightGenerator.generate(records);
        Map<String, Double> monthlyTotals = statistics.monthlyTotals(records);
        Map<String, Double> forecast = forecaster.forecast(monthlyTotals, 3);

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            PDType1Font titleFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD);
            PDType1Font bodyFont = new PDType1Font(Standard14Fonts.FontName.HELVETICA);

            try (PDPageContentStream stream = new PDPageContentStream(document, page)) {
                float y = 720;
                y = writeLine(stream, titleFont, 18, 50, y, "Advanced Analytics Report");
                y -= 10;
                y = writeLine(stream, bodyFont, 12, 50, y, "Total revenue: KES %,.0f across %d records".formatted(total, records.size()));
                y -= 15;

                y = writeLine(stream, titleFont, 14, 50, y, "Key Insights");
                for (String insight : insights) {
                    y = writeLine(stream, bodyFont, 11, 60, y, "- " + insight);
                }
                y -= 15;

                y = writeLine(stream, titleFont, 14, 50, y, "3-Month Forecast");
                for (Map.Entry<String, Double> entry : forecast.entrySet()) {
                    y = writeLine(stream, bodyFont, 11, 60, y, "%s: KES %,.0f (projected)".formatted(entry.getKey(), entry.getValue()));
                }
            }

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            document.save(out);
            return out.toByteArray();
        }
    }

    private float writeLine(PDPageContentStream stream, PDType1Font font, float fontSize, float x, float y, String text) throws IOException {
        stream.beginText();
        stream.setFont(font, fontSize);
        stream.newLineAtOffset(x, y);
        stream.showText(text);
        stream.endText();
        return y - (fontSize + 6);
    }
}
