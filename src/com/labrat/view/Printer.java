package com.labrat.view;

// Practically the view component of our MVC system.
// Additionality utilizes the singleton pattern, as there should only be one printer responsible for output.
public class Printer {
    private static Printer printer;
    private int MAX_LINES;
    private enum VersionType {
        DEBUG,
        RELEASE
    }

    private Printer() {} // private constructor for singleton pattern

    public static Printer getInstance() {
        if (printer == null) {
            printer = new Printer();
        }
        return printer;
    }

    // The PrinterColor itself should not have internal implementation to convert enum to string
    // because if we are following MVC principles, that would mean the model is aware of the escape sequence
    // used to output in the view component of MVC
    public String getColor(PrinterColor c) {
        return switch (c) {
            // Typical colors
            case RED ->           "\033[31m";
            case GREEN ->         "\033[32m";
            case YELLOW ->        "\033[33m";
            case BLUE ->          "\033[34m";
            case MAGENTA ->       "\033[35m";
            case CYAN ->          "\033[36m";
            case WHITE ->         "\033[37m";

            // Light variants
            case LIGHT_RED ->     "\033[91m";
            case LIGHT_GREEN ->   "\033[92m";
            case LIGHT_YELLOW ->  "\033[93m";
            case LIGHT_BLUE ->    "\033[94m";
            case LIGHT_MAGENTA -> "\033[95m";
            case LIGHT_CYAN ->    "\033[96m";
            case LIGHT_WHITE ->   "\033[97m";

            // Dark variants
            case DARK_RED ->      "\033[2;31m";
            case DARK_GREEN ->    "\033[2;32m";
            case DARK_YELLOW ->   "\033[2;33m";
            case DARK_BLUE ->     "\033[2;34m";
            case DARK_MAGENTA ->  "\033[2;35m";
            case DARK_CYAN ->     "\033[2;36m";

            // Effects
            case BOLD ->          "\033[1m";
            case DIM ->           "\033[2m";
            case ITALIC ->        "\033[3m";
            case UNDERLINE ->     "\033[4m";

            default ->            "\033[0m";
        };
    }

    public void printLines() {
        VersionType version = VersionType.DEBUG;
        MAX_LINES = 50;

        switch (version) {
            case DEBUG:
                for (int i = 0; i < MAX_LINES; i++) {
                    System.out.println();
                }
                break;
            case RELEASE:
                String CLEAR = "\033[H\033[2J";     // Escape sequence to clear terminal
                System.out.print(CLEAR);
                break;
        }
        System.out.flush();
    }

    public void print(ColoredText ct) {
        String colorString = getColor(ct.getColor());
        System.out.println(colorString + ct.getText() + "\033[0m");
    }

    public void print(Object info){
        System.out.println(info);
    }
}
