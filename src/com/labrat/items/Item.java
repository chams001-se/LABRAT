package com.labrat.items;

import com.labrat.audio.SoundEffect;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

public abstract class Item implements Examinable {
    private final String name;
    private final ResultText resultText;

    public Item(String name, String description, PrinterColor descriptionColor, SoundEffect sfx) {
        this.name = name;
        this.resultText = new ResultText(description, descriptionColor, sfx);
    }

    public String getName() {
        return name;
    }

    public ResultText getResultText() {
        return resultText;
    }

    @Override
    public ResultText examine() {
        return resultText;
    }

}
