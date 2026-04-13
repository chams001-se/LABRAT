package com.labrat.items;

import com.labrat.actors.Actor;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

// TODO Not used anywhere yet
public class Item implements Examinable {
    String name;
    PrinterColor nameColor;
    String description;
    ResultText coloredName;

    public Item(String name, PrinterColor nameColor, String description){
        this.name = name;
        this.description = description;
        this.nameColor = nameColor;
        this.coloredName = new ResultText(description, nameColor);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public PrinterColor getNameColor() {
        return nameColor;
    }

    @Override
    public String examine(Actor actor) {
        return description;
    }

}
