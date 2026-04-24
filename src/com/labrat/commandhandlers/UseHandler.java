package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class UseHandler extends BaseHandler {
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.USE;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // True if there is an item in the current room that can be used
        Actor actor = command.getActor();
        String[] args = command.getArgs();
        String target = argsToString(args);

        // TODO create classes which implement Usable interface
        //return actor.getCurrentRoom().getItem(target) instanceof Usable;
        return actor.getCurrentRoom().hasItem(target);
    }

    @Override
    public void performRequest(Command command) {
        if (canHandle(command)) {
            Actor actor = command.getActor();
            String[] args = command.getArgs();

            if (args.length == 0) {
                actor.setResultText(new ResultText("Use what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText("You can't use that.", PrinterColor.YELLOW));
            }
        }
        else {
            super.performRequest(command);
        }
    }
}
