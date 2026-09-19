package com.portfolio;

import java.io.File;

/**
 * Usage: java com.portfolio.PdfToAudioApp <input.pdf> <output.wav> [--play]
 */
public class PdfToAudioApp {

    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: PdfToAudioApp <input.pdf> <output.wav> [--play]");
            return;
        }

        File pdfFile = new File(args[0]);
        File outputWavFile = new File(args[1]);
        boolean play = args.length > 2 && args[2].equals("--play");

        PdfToAudioConverter converter = new PdfToAudioConverter(new PdfTextExtractor(), new WindowsSpeechSynthesizer());
        String text = converter.convert(pdfFile, outputWavFile);

        System.out.println("Extracted " + text.length() + " characters of text.");
        System.out.println("Audio saved to " + outputWavFile.getAbsolutePath());

        if (play) {
            new AudioPlayer().play(outputWavFile);
        }
    }
}
