package com.labrat.items;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class Note extends Item implements Readable {

    public Note(String name, String description, PrinterColor descriptionColor, SoundEffect sfx) {
        super(name, description, descriptionColor, sfx);
    }

    @Override
    public ResultText read() {
        return this.getResultText();
    }
}
