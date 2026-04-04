package com.labrat.view;
public class Printer {
    public String getColor(PrinterColor c){
        return switch (c) {
            case RED -> "\033[31m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            default -> "\033[0m";
        };

    }
    public void printToString(Object info, PrinterColor color){
        String colorString = getColor(color);
        System.out.println(colorString + info + "\033[0m");
    }

    public void printToString(Object info){
        System.out.println(info);
    }
}
