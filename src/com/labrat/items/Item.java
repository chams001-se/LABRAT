package com.labrat.items;

import com.labrat.view.ColoredText;
import com.labrat.view.PrinterColor;

// TODO Not used anywhere yet
public class Item {
    String name;
    PrinterColor nameColor;
    String description;
    ColoredText coloredName;

    public Item(String name, PrinterColor nameColor, String description){
        this.name = name;
        this.description = description;
        this.nameColor = nameColor;
        this.coloredName = new ColoredText(description, nameColor);
    }

    String getDescription(){
        return description;
    }

    PrinterColor getNameColor(){
        return nameColor;
    }

}
