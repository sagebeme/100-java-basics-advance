package com.portfolio;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.io.TempDir;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

/**
 * The process handling is tested with a stand-in "speech program" (a one-line shell command), so
 * it runs on any Linux or macOS machine. The test against real espeak-ng only runs where it's
 * installed.
 */
@EnabledOnOs({OS.LINUX, OS.MAC})
class CommandSpeechSynthesizerTest {

    /** Half a second of silence as a real WAV file: what a speech program would write. */
    private static File sampleWav(Path dir) throws IOException {
        AudioFormat format = new AudioFormat(22050, 16, 1, true, false);
        byte[] silence = new byte[22050];
        File wav = dir.resolve("sample.wav").toFile();
        try (AudioInputStream in = new AudioInputStream(new ByteArrayInputStream(silence), format, silence.length / 2)) {
            AudioSystem.write(in, AudioFileFormat.Type.WAVE, wav);
        }
        return wav;
    }

    @Test
    void passesTheTextInAFileAndKeepsTheWavTheProgramWrites(@TempDir Path tempDir) throws Exception {
        File sample = sampleWav(tempDir);
        // The stand-in only "speaks" if the text file it was given really contains the text.
        CommandSpeechSynthesizer synth = new CommandSpeechSynthesizer(
                (textFile, wav) -> List.of("sh", "-c", "grep -q 'Habari ya asubuhi' \"$0\" && cp \"$2\" \"$1\"",
                        textFile.toString(), wav.toString(), sample.getPath()),
                "unused");
        File output = tempDir.resolve("speech.wav").toFile();

        synth.speakToFile("Habari ya asubuhi", output);

        try (AudioInputStream audio = AudioSystem.getAudioInputStream(output)) {
            assertTrue(audio.getFrameLength() > 0, "the result should be a playable WAV");
        }
    }

    @Test
    void textThatLooksLikeOptionsIsStillJustText(@TempDir Path tempDir) throws Exception {
        File sample = sampleWav(tempDir);
        CommandSpeechSynthesizer synth = new CommandSpeechSynthesizer(
                (textFile, wav) -> List.of("sh", "-c", "grep -qF -- '--help; rm -rf ~' \"$0\" && cp \"$2\" \"$1\"",
                        textFile.toString(), wav.toString(), sample.getPath()),
                "unused");
        File output = tempDir.resolve("speech.wav").toFile();

        synth.speakToFile("--help; rm -rf ~", output);

        assertTrue(output.length() > 0);
    }

    @Test
    void aMissingProgramExplainsHowToInstallIt(@TempDir Path tempDir) {
        CommandSpeechSynthesizer synth = new CommandSpeechSynthesizer(
                (textFile, wav) -> List.of("no-such-speech-program-xyz", textFile.toString()),
                "Install the speech program first.");

        IOException e = assertThrows(IOException.class,
                () -> synth.speakToFile("hello", tempDir.resolve("out.wav").toFile()));
        assertTrue(e.getMessage().contains("Install the speech program first."), e.getMessage());
    }

    @Test
    void aFailingProgramIsReportedWithItsOutput(@TempDir Path tempDir) {
        CommandSpeechSynthesizer synth = new CommandSpeechSynthesizer(
                (textFile, wav) -> List.of("sh", "-c", "echo 'no voices found'; exit 3"),
                "unused");

        IOException e = assertThrows(IOException.class,
                () -> synth.speakToFile("hello", tempDir.resolve("out.wav").toFile()));
        assertTrue(e.getMessage().contains("exit 3") && e.getMessage().contains("no voices found"), e.getMessage());
    }

    @Test
    void aProgramThatWritesNothingIsAnError(@TempDir Path tempDir) {
        CommandSpeechSynthesizer synth = new CommandSpeechSynthesizer(
                (textFile, wav) -> List.of("sh", "-c", "true"),
                "unused");

        assertThrows(IOException.class, () -> synth.speakToFile("hello", tempDir.resolve("out.wav").toFile()));
    }

    @Test
    void rejectsBlankText(@TempDir Path tempDir) {
        assertThrows(IllegalArgumentException.class,
                () -> CommandSpeechSynthesizer.espeakNg().speakToFile("  ", tempDir.resolve("out.wav").toFile()));
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void linuxUsesEspeakNg() {
        assertInstanceOf(CommandSpeechSynthesizer.class, SpeechSynthesizer.forThisComputer());
    }

    @Test
    @EnabledOnOs(OS.LINUX)
    void speaksWithTheRealEspeakNgWhenItIsInstalled(@TempDir Path tempDir) throws Exception {
        assumeTrue(onPath("espeak-ng"), "espeak-ng isn't installed here");
        File output = tempDir.resolve("real.wav").toFile();

        CommandSpeechSynthesizer.espeakNg().speakToFile("Karibu Kenya", output);

        try (AudioInputStream audio = AudioSystem.getAudioInputStream(output)) {
            assertTrue(audio.getFrameLength() > 0);
        }
    }

    private static boolean onPath(String program) {
        for (String dir : System.getenv().getOrDefault("PATH", "").split(File.pathSeparator)) {
            if (Files.isExecutable(Path.of(dir, program))) {
                return true;
            }
        }
        return false;
    }
}
