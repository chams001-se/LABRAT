package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
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
        Actor actor = command.actor();
        // convert arg array into single arg string
        String target = argsToString(command.args());
        // get item instance in specific room
        Item item = actor.getCurrentRoom().getItem(target);

        return item != null && item.isReadable();
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

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
