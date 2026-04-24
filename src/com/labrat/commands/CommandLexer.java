package com.labrat.commands;

public class CommandLexer {
    // Aliases shorthanded user input to a valid CommandType
    private String aliasCmd(String cmdType) {
        return switch (cmdType) {
            case "m" ->         "move";
            case "e" ->         "examine";
            case "t" ->         "take";
            case "r" ->         "read";
            case "h" ->         "hide";
            case "i" ->         "inventory";
            case "exit" ->       "quit";
            default ->          cmdType;
        };
    }

    // Aliases shorthanded user input to a valid Direction
    public String aliasDir(String dir) {
        return switch (dir) {
            case "n" ->         "north";
            case "ne" ->        "northeast";
            case "e" ->         "east";
            case "se" ->        "southeast";
            case "s" ->         "south";
            case "sw" ->        "southwest";
            case "w" ->         "west";
            case "nw" ->        "northwest";
            default ->          dir;
        };
    }

    public String[] lex(String input) {
        // Declare variables
        String[] words = input.toLowerCase().trim().split("\\s+");

        // Check input length
        if (words.length >= 1) { words[0] = aliasCmd(words[0]); }
        if (words.length >= 2) {
            if (words[0].equals("move")) { words[1] = aliasDir(words[1]); }
        }

        return words;
    }
}