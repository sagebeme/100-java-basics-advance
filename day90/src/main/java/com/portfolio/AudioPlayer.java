package com.portfolio;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class AudioPlayer {

    /**
     * Opens the wav file into a Clip without starting playback - useful for validating that a
     * generated file is actually a well-formed, playable audio stream.
     */
    public Clip open(File wavFile) throws IOException, UnsupportedAudioFileException, javax.sound.sampled.LineUnavailableException {
        try (AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(wavFile)) {
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        }
    }

    /**
     * Plays the file and blocks until playback finishes.
     */
    public void play(File wavFile) throws IOException, UnsupportedAudioFileException, javax.sound.sampled.LineUnavailableException, InterruptedException {
        Clip clip = open(wavFile);
        CountDownLatch done = new CountDownLatch(1);
        clip.addLineListener(event -> {
            if (event.getType() == LineEvent.Type.STOP) {
                done.countDown();
            }
        });
        clip.start();
        done.await();
        clip.close();
    }
}
