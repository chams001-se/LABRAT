package com.labrat.view;

import com.labrat.audio.SoundEffect;

public abstract class NamedResultText {
    // Declare variables
    String name;
    String description;
    PrinterColor descriptionColor;
    SoundEffect sfx;

    NamedResultText() {
        // Default values
        name = "";
        description = "";
        descriptionColor = PrinterColor.DEFAULT;
        sfx = SoundEffect.MUTE;
    }
}
