package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;

public class HideHandler extends BaseHandler {
    HideHandler() {
        super(CommandType.HIDE);
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
