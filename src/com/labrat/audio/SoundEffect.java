package com.labrat.audio;

import com.labrat.rooms.Direction;

import java.util.HashMap;

public enum SoundEffect {
    MUTE,
    BEEP;

    // Static map index for Direction enum
    private static final HashMap<String, SoundEffect> soundMap = new HashMap<>();
    static {
        for (SoundEffect s : SoundEffect.values()) {
            soundMap.put(s.name().toLowerCase(), s);
        }
    }

    // Empty Constructor
    SoundEffect() {}

    // Converts a String into a SoundEffect
    public static SoundEffect fromString(String str) {
        if (soundMap.containsKey(str)) {
            return soundMap.get(str);
        }
        else {
            throw new IllegalArgumentException("Invalid sound: " + str);
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}