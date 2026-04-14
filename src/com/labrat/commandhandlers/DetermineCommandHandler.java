package com.labrat.commandhandlers;

import com.labrat.commands.*;
import com.labrat.view.ResultText;

public class DetermineCommandHandler {
    // Array of CommandHandlers
    CommandHandler[] commandHandlers;

    public DetermineCommandHandler() {
        // Create the command handlers and their linked handlers for Chain of Responsibility
        // Chain of Responsibility order begins from top to bottom
        commandHandlers  = new CommandHandler[] {
                new MoveHandler(),
                new ReadHandler(),
                new HelpHandler()
        };

        // Sets the next CommandHandler to the next in the commandHandlers array
        // i.e. MoveHandler.setNext(HelpHandler)
        for (int i = 0; i < commandHandlers.length-1; i++) {
            commandHandlers[i].setNext(commandHandlers[i+1]);
        }
    }

    public Boolean isQuit(Command userCommand) {
        return userCommand.getCommandType() == CommandType.QUIT;
    }

    // Starts processing the request in the Chain of Responsibility from the first CommandHandler
    public ResultText performRequest(Command command) {
        return commandHandlers[0].performRequest(command);
    }
}
