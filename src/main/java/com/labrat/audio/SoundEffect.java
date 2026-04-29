package com.labrat.audio;

import com.labrat.view.PrinterColor;

import java.util.HashMap;

public enum SoundEffect {
    // Null Sound Object
    MUTE,

    // Command Feedback
    BEEP,
    COMMANDERROR,
    HUMANFOOTSTEPS,

    // Item Feedback
    KEYCARD,
    NOTEPAPER,

    // Room Feedback
    SCARYROOM;

    // Static map index for SoundEffect enum
    private static final HashMap<String, SoundEffect> sfxMap = new HashMap<>();
    static {
        for (SoundEffect sfx : SoundEffect.values()) {
            sfxMap.put(sfx.name().toLowerCase(), sfx);
        }
    }

    // Converts a String into a SoundEffect
    public static SoundEffect fromString(String str) {
        if (sfxMap.containsKey(str)) {
            return sfxMap.get(str);
        }
        else {
            throw new IllegalArgumentException("Invalid SoundEffect: " + str);
        }
    }
}