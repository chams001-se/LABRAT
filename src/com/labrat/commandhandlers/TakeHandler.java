package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class TakeHandler extends BaseHandler {
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.TAKE;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // True if there is an item in the current room that can be taken
        Actor actor = command.actor();
        String[] args = command.args();
        String target = argsToString(args);

        // TODO create classes which implement Takable interface
        //return actor.getCurrentRoom().getItem(target) instanceof Takeable;
        return actor.getCurrentRoom().hasItem(target);
    }

    @Override
    public void performRequest(Command command) {
        if (canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();
            String target = argsToString(args);

            if (args.length == 0) {
                actor.setResultText(new ResultText("Take what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
                actor.setResultText(new ResultText("You have taken " + target.toUpperCase() + "!", PrinterColor.BLUE));
            }
            else {
                actor.setResultText(new ResultText("You can't take " + target.toUpperCase() + ".", PrinterColor.RED));
            }
        }
        else {
            super.performRequest(command);
        }
    }
}
