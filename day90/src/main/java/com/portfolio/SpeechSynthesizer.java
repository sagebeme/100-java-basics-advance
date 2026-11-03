package com.portfolio;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

/**
 * Turns text into a .wav file. Each operating system has its own offline speech engine, so
 * there is one implementation per engine, and {@link #forThisComputer()} picks the right one.
 */
public interface SpeechSynthesizer {

    void speakToFile(String text, File outputWavFile) throws IOException, InterruptedException;

    static SpeechSynthesizer forThisComputer() {
        String os = System.getProperty("os.name", "").toLowerCase(Locale.ROOT);
        if (os.contains("win")) {
            return new WindowsSpeechSynthesizer();
        }
        if (os.contains("mac")) {
            return CommandSpeechSynthesizer.macSay();
        }
        return CommandSpeechSynthesizer.espeakNg();
    }
}
