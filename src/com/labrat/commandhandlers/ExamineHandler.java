package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

// Purpose is to get advanced info of an item

public class ExamineHandler extends BaseHandler {
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.EXAMINE;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        Actor actor = command.actor();
        // convert arg array into single arg string
        String target = argsToString(command.args());
        // find item instance in specific room
        Item item = actor.getCurrentRoom().getItem(target);

        return item != null && item.isExaminable();
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            // Check number of arguments
            if (args.length == 0) {
                actor.setResultText(new ResultText("Examine what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText("You can't examine that.", PrinterColor.YELLOW));
            }
        }
        else {
            super.performRequest(command);
        }
    }
}
