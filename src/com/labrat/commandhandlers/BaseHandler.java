package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.view.ResultText;

public class BaseHandler implements CommandHandler {
    CommandHandler nextCommandHandler;

    @Override
    public ResultText performRequest(Command command) {
        if (nextCommandHandler != null) {
            return nextCommandHandler.performRequest(command);
        }
        throw new IllegalArgumentException("No Command Handlers Found to handle Command: "
            + command.getCommandType().toString());
    }

    @Override
    public void setNext(CommandHandler nextCommandHandler) {
        this.nextCommandHandler = nextCommandHandler;
    }
}
