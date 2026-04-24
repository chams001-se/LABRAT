package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;

public class QuitHandler extends BaseHandler {
    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.QUIT;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // Always true
        return true;
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (canHandle(command)) {
            command.execute();
        }
        else {
            super.performRequest(command);
        }
    }
}
