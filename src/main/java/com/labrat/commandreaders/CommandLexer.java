package com.labrat.commandreaders;

/*
Purpose is to split the command into tokens which should be in the form of command <argument1> <argument2> ...
 */
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