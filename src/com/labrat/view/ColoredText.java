package com.labrat.view;

import static com.labrat.view.PrinterColor.*;

public class ColoredText {
    private String text;
    private PrinterColor color;

    public ColoredText(String info, PrinterColor c){
        this.text = info;
        this.color = c;
    }

    public ColoredText(String info){
        this.text = info;
        this.color = DEFAULT;
    }

    public String getText(){
        return text;
    }

    public PrinterColor getColor(){
        return color;
    }
}
