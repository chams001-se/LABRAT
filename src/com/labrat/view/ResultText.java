package com.labrat.view;

import com.labrat.audio.SoundEffect;

import static com.labrat.view.PrinterColor.*;

public class ResultText {
    private String text;
    private PrinterColor color;
    private SoundEffect soundEffect;

    public ResultText(String text, PrinterColor color, String sfx) {
        this.text = text;
        this.color = color;
        this.soundEffect = SoundEffect.fromString(sfx);
    }

    public ResultText(String text, PrinterColor color) {
        this.text = text;
        this.color = color;
        this.soundEffect = SoundEffect.MUTE;
    }

    public ResultText(String text) {
        this.text = text;
        this.color = DEFAULT;
        this.soundEffect = SoundEffect.MUTE;
    }

    public String getText() {
        return text;
    }

    public PrinterColor getColor() {
        return color;
    }

    public SoundEffect getSoundEffect() {
        return soundEffect;
    }
}
