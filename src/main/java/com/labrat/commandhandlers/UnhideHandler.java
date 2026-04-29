package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;

public class UnhideHandler extends BaseHandler {
    UnhideHandler() {
        super(CommandType.UNHIDE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // No arguments
        return true;
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (super.canHandle(command)) {
            command.execute();
        }
        else {
            super.performRequest(command);
        }
    }
}
