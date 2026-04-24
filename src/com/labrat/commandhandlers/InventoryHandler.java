package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.ResultText;

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
        Actor actor = command.getActor();

        if (canHandle(command)) {
            command.execute();
        }
        else {
            super.performRequest(command);
        }
    }
}
