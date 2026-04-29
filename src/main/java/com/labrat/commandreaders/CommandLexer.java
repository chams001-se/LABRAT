package com.labrat.commandreaders;

public class CommandLexer {
    CommandAlias commandAlias;

    public CommandLexer(CommandAlias commandAlias) {
        this.commandAlias = commandAlias;
    }

    public String[] lex(String input) {
        // Declare variables
        String[] words = input.toLowerCase().trim().split("\\s+");

        words = commandAlias.alias(words);

        return words;
    }
}