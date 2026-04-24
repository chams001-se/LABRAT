package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;

// Purpose is to print all user commands and their functionality

public class HelpHandler extends BaseHandler {

    @Override
    protected boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.HELP;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // Always available
        return true;
    }

    @Override
    public void performRequest(Command command) {
        if (canHandle(command)) {
            command.execute();
        }
        else {
            super.performRequest(command);
        }
    }
}
