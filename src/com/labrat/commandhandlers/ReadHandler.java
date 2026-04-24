package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

// Purpose is to print results from the move command

public class ReadHandler extends BaseHandler {
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.READ;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // True if there is an item in the current room that can be read
        Actor actor = command.getActor();
        String[] args = command.getArgs();
        String target = argsToString(args);

        // TODO create classes which implement Readable interface
        //return actor.getCurrentRoom().getItem(target) instanceof Readable;
        return actor.getCurrentRoom().hasItem(target);
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (canHandle(command)) {
            Actor actor = command.getActor();
            String[] args = command.getArgs();

            // Check number of arguments
            if (args.length == 0) {
                actor.setResultText(new ResultText("Read what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText("You can't read that.", PrinterColor.YELLOW));
            }
        }
        else {
            super.performRequest(command);
        }
    }
}
