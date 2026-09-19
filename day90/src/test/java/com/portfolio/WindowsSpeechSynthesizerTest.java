package com.portfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.File;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exercises the real Windows SAPI engine via PowerShell - these tests actually shell out and
 * synthesize audio, so they're slower than a typical unit test but they prove the feature works
 * end to end rather than mocking the one part that matters.
 */
class WindowsSpeechSynthesizerTest {

    private final WindowsSpeechSynthesizer synthesizer = new WindowsSpeechSynthesizer();

    @Test
    void producesARealPlayableWavFile(@TempDir Path tempDir) throws Exception {
        File output = tempDir.resolve("speech.wav").toFile();

        synthesizer.speakToFile("This is a test of the speech synthesizer.", output);

        assertTrue(output.exists());
        assertTrue(output.length() > 1000, "Expected a non-trivial wav file, was " + output.length() + " bytes");

        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(output)) {
            assertTrue(audioInputStream.getFrameLength() > 0);
            assertTrue(audioInputStream.getFormat().getSampleRate() > 0);
        }
    }

    @Test
    void producesLongerAudioForLongerText(@TempDir Path tempDir) throws Exception {
        File shortOutput = tempDir.resolve("short.wav").toFile();
        File longOutput = tempDir.resolve("long.wav").toFile();

        synthesizer.speakToFile("Hi.", shortOutput);
        synthesizer.speakToFile("This is a much longer sentence that should take noticeably more time for the speech synthesizer to read aloud than a short one.", longOutput);

        assertTrue(longOutput.length() > shortOutput.length(),
                "Expected longer text to produce a larger audio file: short=" + shortOutput.length() + " long=" + longOutput.length());
    }

    @Test
    void rejectsBlankText(@TempDir Path tempDir) {
        File output = tempDir.resolve("blank.wav").toFile();
        assertThrows(IllegalArgumentException.class, () -> synthesizer.speakToFile("", output));
        assertThrows(IllegalArgumentException.class, () -> synthesizer.speakToFile(null, output));
    }
}
