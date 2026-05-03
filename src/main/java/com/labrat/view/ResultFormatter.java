package com.labrat.view;

// Practically the view component of our MVC system.
// Additionality utilizes the singleton pattern, as there should only be one formatter responsible for output.
public class ResultFormatter {
    private static ResultFormatter formatter;
    private ResultFormatter() {} // private constructor for singleton pattern

    public static ResultFormatter getInstance() {
        if (formatter == null) {
            formatter = new ResultFormatter();
        }
        return formatter;
    }

    // Declare variables
    private int MAX_LINES;
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_CLEAR = "\033[H\033[2J";
    private final StringBuilder sb = new StringBuilder();
    private enum VersionType {
        DEBUG,
        RELEASE
    }

    /* The PrinterColor itself should not have internal implementation to convert enum to string
    // because if we are following MVC principles, that would mean the model is aware of the escape sequence
    // used to output in the view component of MVC
     */
    private String getColor(PrinterColor c) {
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
            case DOCUMENT ->      "\033[3;30;107m";
            case NOTE ->          "\033[3;30;103m";

            default ->            "\033[0m";
        };
    }

    public void printLines() {
        // VersionType.DEBUG = Debug version for IntelliJ console
        // VersionType.RELEASE = Release version for regular console (i.e. cmd.exe)
        VersionType version = VersionType.DEBUG;
        MAX_LINES = 50;

        switch (version) {
            case DEBUG:
                for (int i = 0; i < MAX_LINES; i++) {
                    System.out.println();
                }
                break;
            case RELEASE:
                System.out.print(ANSI_CLEAR);
                break;
        }

        System.out.flush();
    }

    public void print(ResultText ct) {
        // Get StringBuilder text
        sb.append(getColor(ct.color()));
        sb.append(ct.text());
        sb.append(ANSI_RESET);

        // Print StringBuilder
        System.out.println(sb);

        // Reset StringBuilder
        sb.delete(0, sb.length());
    }
}
