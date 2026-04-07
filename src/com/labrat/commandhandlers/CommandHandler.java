package com.labrat.commandhandlers;

// A command handlers purpose is to translate parsed user input into a command and execute it
import com.labrat.commands.Command;
import com.labrat.view.ColoredText;

public interface CommandHandler {
    // Ultimately ends up executing a command, making the command handlers the invoker in the command pattern
    // An invoker asks the command to carry out the request
    ColoredText performRequest(Command command);

    void setNext(CommandHandler nextCommandHandler);
}
