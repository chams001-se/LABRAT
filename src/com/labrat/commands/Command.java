package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.view.ResultText;

public interface Command {
    // Return the actor used for the command
    Actor getActor();

    // Return the arguments in the command
    String[] getArgs();

    // Return the CommandType of the Command
    CommandType getCommandType();

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    void execute();
}
