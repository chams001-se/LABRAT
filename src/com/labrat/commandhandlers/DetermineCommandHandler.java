package com.labrat.commandhandlers;

import com.labrat.commands.*;
import com.labrat.view.ColoredText;

public class DetermineCommandHandler {
    // First Command Handler should be reachable for all methods
    HelpHandler helpHandler;

    public DetermineCommandHandler() {
        // Create the command handlers and their linked handlers for chain of responsibility
        helpHandler = new HelpHandler();
        MoveHandler moveHandler = new MoveHandler();

        helpHandler.setNext(moveHandler);
    }

    public Boolean isQuit(Command userCommand) {
        return userCommand.getCommandType() == CommandType.QUIT;
    }

    public ColoredText performRequest(Command command) {
        return helpHandler.performRequest(command);
    }
}
