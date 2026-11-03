package com.portfolio;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

/**
 * Speaks text with a command-line speech program: {@code say} on macOS, {@code espeak-ng} on
 * Linux. Like the Windows version, the text goes in a temp file that the program reads, never on
 * the command line, so text from a PDF can't be mistaken for options or commands.
 */
public class CommandSpeechSynthesizer implements SpeechSynthesizer {

    private static final Duration PROCESS_TIMEOUT = Duration.ofSeconds(60);

    private final BiFunction<Path, Path, List<String>> command;
    private final String installHint;

    /**
     * @param command     builds the command from the text file to read and the .wav file to write
     * @param installHint what to tell the user if the program isn't installed
     */
    public CommandSpeechSynthesizer(BiFunction<Path, Path, List<String>> command, String installHint) {
        this.command = command;
        this.installHint = installHint;
    }

    /** Linux: espeak-ng, from most distributions' packages. */
    public static CommandSpeechSynthesizer espeakNg() {
        return new CommandSpeechSynthesizer(
                (textFile, wav) -> List.of("espeak-ng", "-w", wav.toString(), "-f", textFile.toString()),
                "Install espeak-ng first, e.g. 'sudo apt install espeak-ng' or 'sudo dnf install espeak-ng'.");
    }

    /** macOS: the built-in say command, asked for 16-bit WAV so Java can play it. */
    public static CommandSpeechSynthesizer macSay() {
        return new CommandSpeechSynthesizer(
                (textFile, wav) -> List.of("say", "-o", wav.toString(), "--data-format=LEI16@22050",
                        "-f", textFile.toString()),
                "The 'say' command comes with macOS; check it runs in Terminal.");
    }

    @Override
    public void speakToFile(String text, File outputWavFile) throws IOException, InterruptedException {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("Cannot synthesize empty text");
        }

        Path textFile = Files.createTempFile("tts-input", ".txt");
        try {
            Files.writeString(textFile, text, StandardCharsets.UTF_8);
            ProcessBuilder builder = new ProcessBuilder(command.apply(textFile, outputWavFile.toPath().toAbsolutePath()));
            builder.redirectErrorStream(true);

            Process process;
            try {
                process = builder.start();
            } catch (IOException e) {
                throw new IOException("Couldn't start the speech program " + builder.command().get(0) + ". " + installHint, e);
            }

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
            Files.deleteIfExists(textFile);
        }
    }
}
