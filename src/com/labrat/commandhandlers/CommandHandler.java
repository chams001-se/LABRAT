package com.labrat.commandhandlers;

// A command handlers purpose is to translate parsed user input into a command and execute it

public interface CommandHandler {
    String performRequest(String[] input);

    // Seeing if a handler can "handle" a request is no longer necessary as there is a
    // map of the command type and the handler that can handler that command type.
}
