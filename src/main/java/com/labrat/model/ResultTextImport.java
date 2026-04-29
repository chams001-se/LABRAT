package com.labrat.model;

import com.labrat.audio.SoundEffect;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class ResultTextImport {
    public String text;
    public String color;
    public String sound;

    public ResultText newResultText() {
        return new ResultText(text, PrinterColor.fromString(color), SoundEffect.fromString(sound));
    }
}
