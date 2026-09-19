package com.portfolio;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PdfTextExtractorTest {

    private final PdfTextExtractor extractor = new PdfTextExtractor();

    private File createPdfWithText(Path dir, String fileName, String text) throws IOException {
        File file = dir.resolve(fileName).toFile();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText(text);
                contentStream.endText();
            }
            document.save(file);
        }
        return file;
    }

    @Test
    void extractsTextFromARealPdf() throws IOException {
        File pdf = createPdfWithText(java.nio.file.Files.createTempDirectory("pdf-test").toAbsolutePath(), "sample.pdf", "Hello from a real PDF");

        String text = extractor.extractText(pdf);

        assertTrue(text.contains("Hello from a real PDF"), "Expected extracted text to contain the written sentence, was: " + text);
    }

    @Test
    void extractsMultiLineText(@TempDir Path tempDir) throws IOException {
        File file = tempDir.resolve("multiline.pdf").toFile();
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
                contentStream.newLineAtOffset(50, 700);
                contentStream.showText("First line");
                contentStream.newLineAtOffset(0, -20);
                contentStream.showText("Second line");
                contentStream.endText();
            }
            document.save(file);
        }

        String text = extractor.extractText(file);

        assertTrue(text.contains("First line"));
        assertTrue(text.contains("Second line"));
    }
}
