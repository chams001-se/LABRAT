package com.labrat.audio;

import javax.sound.sampled.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class AudioManager {
    private static AudioManager audiomanager;
    private AudioManager() {} // private constructor for singleton pattern

    public static AudioManager getInstance() {
        if (audiomanager == null) {
            audiomanager = new AudioManager();
        }
        return audiomanager;
    }

    // Declare variables
    private static final HashMap<SoundEffect, Clip> soundEffectMap = new HashMap<>();

    // Audio files are listed in 16-bit 44100 Hz .wav format
    private String getFilename(SoundEffect s) {
        return switch (s) {
            case MUTE ->            null;
            case BEEP ->            "beep.wav";
            case COMMANDERROR ->    "commanderror.wav";
            case HUMANFOOTSTEPS ->  "humanfootsteps.wav";
            case KEYCARD ->         "keycard.wav";
            case NOTEPAPER ->       "notepaper.wav";
            case SCARYROOM ->       "scaryroom.wav";
        };
    }

    // Load SoundFiles into SoundEffect
    public void init() {
        for (SoundEffect s : SoundEffect.values()) {
            try {
                // Get audio filename
                String audioFilename = getFilename(s);

                if (audioFilename != null) {
                    // Gets the systems full, not relative path, for the audio files to be properly traced if the program is run through the terminal
                    InputStream audioFilePath = getClass().getResourceAsStream("/audiofiles/" + audioFilename);
                    if (audioFilePath != null) {
                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFilePath);
                        Clip clip = AudioSystem.getClip();
                        clip.open(audioStream);

                        // Put sound in soundEffectMap
                        soundEffectMap.put(s, clip);

                        // Close audioStream after loading into clip
                        audioStream.close();
                    }
                }
                else {
                    // filename is null due to MUTE case
                    soundEffectMap.put(s, null);
                }
            }
            catch (UnsupportedAudioFileException e) {
                throw new RuntimeException("SoundFiles: Unsupported Audio File: " + e);
            }
            catch (IOException e) {
                throw new RuntimeException("SoundFiles: Input/Output Exception: " + e);
            }
            catch (LineUnavailableException e) {
                throw new RuntimeException("SoundFiles: Line Unavailable Exception: " + e);
            }
        }
    }

    // Plays the Clip associated with SoundEffect on the soundEffectMap
    public void play(SoundEffect sfx) {
        Clip clip = soundEffectMap.get(sfx);

        // Clip is "null" if the key is MUTE
        if (clip != null) {
            if (clip.isRunning()) {
                clip.stop();
            }
            clip.setFramePosition(0);
            clip.start();
        }
    }

    // Iterates through soundEffectMap and close all Clips
    public void close() {
        for (var entry : soundEffectMap.entrySet()) {
            Clip clip = entry.getValue();
            if (clip != null) {
                entry.getValue().close();
            }
        }
    }
}