package com.labrat.audio;

import com.labrat.view.PrinterColor;

import java.util.HashMap;

public enum SoundEffect {
    // Null Sound Object
    MUTE,

    // Game State
    GAMEOVER,
    MONSTERSTEP,
    MONSTERBREATH,

    // Command Feedback
    BEEP,
    COMMANDERROR,
    HUMANFOOTSTEPSLAB,
    HUMANFOOTSTEPSFOREST,
    HUMANFOOTSTEPSGRAVEL,

    // Item Feedback
    FUSEHISS,
    GENERATORINSTALL,
    KEYCARD,
    MAINTENANCELOCKER,
    METALSAWUNSHEATHE,
    NOTEPAPER,
    STORAGECRATE,
    TAKEKEY,

    // Room Feedback
    CLAPPING,
    CRACKLE,
    ELECTRICITY,
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
        if (str == null) return MUTE;
        if (sfxMap.containsKey(str)) {
            return sfxMap.get(str);
        }
        else {
            throw new IllegalArgumentException("Invalid SoundEffect: " + str);
        }
    }
}