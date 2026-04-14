package com.labrat.items;

import com.labrat.actors.Actor;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class Note extends Item implements Readable {
    public Note(String description, PrinterColor descColor, String name) {
        super(description, descColor, name);
    }

    @Override
    public ResultText read() {
        return new ResultText(this.getDescription(), this.getDescColor());
    }
}
