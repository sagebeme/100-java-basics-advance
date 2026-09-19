package com.portfolio;

import java.io.File;
import java.io.IOException;

public class PdfToAudioConverter {

    private final PdfTextExtractor textExtractor;
    private final WindowsSpeechSynthesizer speechSynthesizer;

    public PdfToAudioConverter(PdfTextExtractor textExtractor, WindowsSpeechSynthesizer speechSynthesizer) {
        this.textExtractor = textExtractor;
        this.speechSynthesizer = speechSynthesizer;
    }

    public String convert(File pdfFile, File outputWavFile) throws IOException, InterruptedException {
        String text = textExtractor.extractText(pdfFile);
        if (text == null || text.isBlank()) {
            throw new IllegalStateException("No extractable text found in " + pdfFile);
        }
        speechSynthesizer.speakToFile(text, outputWavFile);
        return text;
    }
}
