package com.labrat.view;

import com.labrat.audio.SoundEffect;

import static com.labrat.view.PrinterColor.*;

public record ResultText(String text, PrinterColor color, SoundEffect soundEffect) {

    public ResultText(String text, SoundEffect soundEffect) {
        this(text, DEFAULT, soundEffect);
    }

    public ResultText(String text, PrinterColor color) {
        this(text, color, SoundEffect.MUTE);
    }

    public ResultText(String text) {
        this(text, DEFAULT, SoundEffect.MUTE);
    }
}
