package com.fractalwrench.pingmachine;

import java.io.BufferedInputStream;
import java.io.InputStream;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;

class AudioPlayer implements LineListener {

    private volatile boolean finished = false;

    synchronized void play(InputStream inputStream) throws InterruptedException {
        // convert the inputstream into an audioinputstream
        try (BufferedInputStream bis = new BufferedInputStream(inputStream);
             Clip clip = AudioSystem.getClip();
             AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(bis)) {

            clip.open(audioInputStream);
            clip.setFramePosition(0); // start at beginning of track
            clip.addLineListener(this);
            clip.start();
            waitForPlaybackCompletion(); // block until at end of track
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void update(LineEvent event) {
        LineEvent.Type type = event.getType();
        if (type == LineEvent.Type.CLOSE || type == LineEvent.Type.STOP) {
            finished = true;
            notifyAll();
        }
    }

    private synchronized void waitForPlaybackCompletion() throws InterruptedException {
        while (!finished) {
            wait();
        }
    }

}
