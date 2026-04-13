package com.labrat.commandhandlers;

// A command handlers purpose is to translate parsed user input into a command and execute it
import com.labrat.commands.Command;
import com.labrat.view.ResultText;

public interface CommandHandler {
    // Ultimately ends up executing a command, making the command handlers the invoker in the command pattern
    // An invoker asks the command to carry out the request
    ResultText performRequest(Command command);

    // Sets next CommandHandler to check if current CommandHandler cannot handle request
    void setNext(CommandHandler nextCommandHandler);
}
