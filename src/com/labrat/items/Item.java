package com.labrat.items;

import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

public abstract class Item implements Examinable {
    private final PrinterColor descColor;
    private final String description;
    private final String name;
    private final ResultText resultText;

    public Item(String description, PrinterColor descColor, String name) {
        this.description = description;
        this.descColor = descColor;
        this.name = name;
        this.resultText = new ResultText(description, descColor);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public PrinterColor getDescColor() { return descColor; }

    public ResultText getResultText() {
        return resultText;
    }

    @Override
    public ResultText examine() {
        return resultText;
    }

}
