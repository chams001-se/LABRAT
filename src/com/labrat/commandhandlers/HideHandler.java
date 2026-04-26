package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;

public class HideHandler extends BaseHandler{
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.HIDE;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // No arguments
        return true;
    }

    @Override
    public void performRequest(Command command) {
        Actor actor = command.actor();
        // Check if current handler can perform request
        if (canHandle(command)) {
            command.execute();
        }
        else {
            super.performRequest(command);
        }
    }
}
