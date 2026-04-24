package com.labrat.commandhandlers;

import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.view.ResultText;

public abstract class BaseHandler implements CommandHandler {
    CommandHandler nextCommandHandler;

    // canHandle is true if CommandType of command matches CommandHandler
    protected abstract boolean canHandle(Command command);

    protected abstract boolean hasValidArgs(Command command);

    // Converts args String array into a single String
    protected String argsToString(String[] args) {
        StringBuilder itemName = new StringBuilder();
        for (String s : args) {
            itemName.append(s).append(" ");
        }
        itemName.deleteCharAt(itemName.length() - 1);

        return itemName.toString().toLowerCase();
    }

    @Override
    public void performRequest(Command command) {
        if (nextCommandHandler != null) {
            nextCommandHandler.performRequest(command);
        }
        else {
            throw new IllegalArgumentException("No Command Handlers Found to handle Command: "
                    + command.getCommandType().toString());
        }
    }

    @Override
    public void setNext(CommandHandler nextCommandHandler) {
        this.nextCommandHandler = nextCommandHandler;
    }
}
