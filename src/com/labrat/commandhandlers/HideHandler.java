package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

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
        Actor actor = command.getActor();
        // Check if current handler can perform request
        if (canHandle(command)) {
            command.execute();
        }
        else {
            super.performRequest(command);
        }
    }
}
