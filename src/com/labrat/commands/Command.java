package com.labrat.commands;

import com.labrat.view.ResultText;

public interface Command {
    // Return the arguments in the command
    String[] getArgs();

    // Performs a check on whether the arguments passed are valid
    boolean hasValidArgs();

    // Return the CommandType of the Command
    CommandType getCommandType();

    // Return the result of the command as printable text
    ResultText getResult();

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    void execute();
}
