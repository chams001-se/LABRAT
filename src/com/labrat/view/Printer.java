package com.labrat.view;

// Practically the view component of our MVC system.
// Additionality utilizes the singleton pattern, as there should only be one printer responsible for output.
public class Printer {
    private static Printer printer;
    private Printer() {} // private constructor for singleton pattern
    public static Printer getInstance(){
        if (printer == null){
            printer = new Printer();
        }
        return printer;
    }
    // The PrinterColor itself should not have internal implementation to convert enum to string
    // because if we are following MVC principles, that would mean the model is aware of the escape sequence
    // used to output in the view component of MVC
    public String getColor(PrinterColor c){
        return switch (c) {
            case RED -> "\033[31m";
            case GREEN -> "\033[32m";
            case BLUE -> "\033[34m";
            case YELLOW -> "\033[33m";
            default -> "\033[0m";
        };

    }
    public void print(ColoredText ct){
        String colorString = getColor(ct.getColor());
        System.out.println(colorString + ct.getText() + "\033[0m");
    }

    public void print(Object info){
        System.out.println(info);
    }
}
