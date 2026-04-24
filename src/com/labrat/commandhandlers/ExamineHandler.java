package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

// Purpose is to get advanced info of an item

public class ExamineHandler extends BaseHandler {
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.READ;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // True if there is an item in the current room that can be examined
        Actor actor = command.getActor();
        String[] args = command.getArgs();
        String target = argsToString(args);

        // TODO create classes which implement Examinable
        //return actor.getCurrentRoom().getItem(target) instanceof Examinable;
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
