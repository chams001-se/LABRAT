package com.labrat.commandhandlers;

import com.labrat.commands.*;
import com.labrat.view.ResultText;

public class DetermineCommandHandler {
    CommandHandler[] commandHandlers;

    public DetermineCommandHandler() {
        /*  Create the command handlers and their linked handlers for Chain of Responsibility
         *  Chain of Responsibility order begins from top to bottom
         *
         *  It is preferable if the most-used commands are higher in the chain
         *  and less-used commands are lower in the chain
         *  to reduce calls to the next CommandHandler
         */
        commandHandlers  = new CommandHandler[] {
                new MoveHandler(),
                new ReadHandler(),
                new UseHandler(),
                new ExamineHandler(),
                new TakeHandler(),
                new DropHandler(),
                new InventoryHandler(),
                new HideHandler(),
                new UnhideHandler(),
                new HelpHandler(),
                new QuitHandler()
        };

        // Sets the next CommandHandler to the next in the commandHandlers array
        // i.e. MoveHandler.setNext(HelpHandler)
        for (int i = 0; i < commandHandlers.length-1; i++) {
            commandHandlers[i].setNext(commandHandlers[i+1]);
        }
    }

    // Starts processing the request in the Chain of Responsibility from the first CommandHandler
    public void performRequest(Command command) {
        commandHandlers[0].performRequest(command);
    }
}
