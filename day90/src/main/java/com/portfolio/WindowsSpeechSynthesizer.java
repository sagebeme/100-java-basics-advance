package com.portfolio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Converts text to a real .wav file using the Windows built-in SAPI speech engine (the same
 * engine behind Narrator), via PowerShell's System.Speech assembly. This needs no API key and no
 * internet connection, unlike cloud TTS services, but is Windows-only - there is no
 * actively-maintained, license-clean, offline TTS library that ships pure Java bytecode across
 * platforms, so this is the honest "external" option the day's own README points at.
 *
 * The text is written to a temp file and read back by the PowerShell script rather than
 * interpolated into the command line, so arbitrary PDF-extracted content can never be interpreted
 * as script code.
 */
public class WindowsSpeechSynthesizer {

    private static final Duration PROCESS_TIMEOUT = Duration.ofSeconds(60);

    public void speakToFile(String text, File outputWavFile) throws IOException, InterruptedException {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Cannot synthesize empty text");
        }

        File tempTextFile = File.createTempFile("tts-input", ".txt");
        try {
            Files.writeString(tempTextFile.toPath(), text, StandardCharsets.UTF_8);

            String script = String.format(
                    "Add-Type -AssemblyName System.Speech;" +
                    "$text = Get-Content -LiteralPath '%s' -Raw -Encoding UTF8;" +
                    "$synth = New-Object System.Speech.Synthesis.SpeechSynthesizer;" +
                    "$synth.SetOutputToWaveFile('%s');" +
                    "$synth.Speak($text);" +
                    "$synth.Dispose();",
                    tempTextFile.getAbsolutePath().replace("'", "''"),
                    outputWavFile.getAbsolutePath().replace("'", "''"));

            ProcessBuilder builder = new ProcessBuilder("powershell", "-NoProfile", "-NonInteractive", "-Command", script);
            builder.redirectErrorStream(true);
            Process process = builder.start();

            String output = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
            boolean finished = process.waitFor(PROCESS_TIMEOUT.toSeconds(), TimeUnit.SECONDS);
            if (!finished) {
                process.destroyForcibly();
                throw new IOException("Speech synthesis timed out after " + PROCESS_TIMEOUT);
            }
            if (process.exitValue() != 0) {
                throw new IOException("Speech synthesis failed (exit " + process.exitValue() + "): " + output);
            }
            if (!outputWavFile.exists() || outputWavFile.length() == 0) {
                throw new IOException("Speech synthesis produced no audio output: " + output);
            }
        } finally {
            Files.deleteIfExists(tempTextFile.toPath());
        }
    }
}
