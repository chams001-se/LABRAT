package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;

public class InventoryHandler extends BaseHandler {
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.INVENTORY;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // Always available
        return true;
    }

    @Override
    public void performRequest(Command command) {
        Actor actor = command.actor();

        if (canHandle(command)) {
            command.execute();
        }
        else {
            super.performRequest(command);
        }
    }
}
