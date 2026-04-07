package com.labrat.commands;

import java.util.List;

public interface Command {
    // Return the arguments in the command
    List<String> getArgs();

    // Performs a check on whether the arguments passed are valid
    boolean hasValidArgs();

    // Return the CommandType of the Command
    CommandType getCommandType();

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    void execute();
}
