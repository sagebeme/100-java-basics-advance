package com.portfolio;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.sound.sampled.Clip;
import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class PdfToAudioConverterTest {

    private final PdfToAudioConverter converter = new PdfToAudioConverter(new PdfTextExtractor(), new WindowsSpeechSynthesizer());

    private File createPdf(Path dir, String text) throws Exception {
        File file = dir.resolve("input.pdf").toFile();
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
    void convertsARealPdfIntoARealPlayableWavFile(@TempDir Path tempDir) throws Exception {
        File pdf = createPdf(tempDir, "This text came from a PDF and should become audio.");
        File wav = tempDir.resolve("output.wav").toFile();

        String extractedText = converter.convert(pdf, wav);

        assertTrue(extractedText.contains("This text came from a PDF and should become audio."));
        assertTrue(wav.exists());
        assertTrue(wav.length() > 1000);

        // Confirm the output is a genuinely well-formed, playable audio stream (opened, not played).
        Clip clip = new AudioPlayer().open(wav);
        assertTrue(clip.getFrameLength() > 0);
        clip.close();
    }

    @Test
    void convertingAPdfWithNoTextThrows(@TempDir Path tempDir) throws Exception {
        File blankPdf = tempDir.resolve("blank.pdf").toFile();
        try (PDDocument document = new PDDocument()) {
            document.addPage(new PDPage());
            document.save(blankPdf);
        }
        File wav = tempDir.resolve("should-not-exist.wav").toFile();

        assertThrows(IllegalStateException.class, () -> converter.convert(blankPdf, wav));
    }
}
