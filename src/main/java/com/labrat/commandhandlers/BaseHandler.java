package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;

public abstract class BaseHandler implements CommandHandler {
    private CommandHandler nextCommandHandler;
    protected CommandType commandType;

    BaseHandler(CommandType commandType) {
        this.commandType = commandType;
    }

    // Helper function that converts args String array into a single String
    protected String argsToString(String[] args) {
        if (args == null || args.length <= 0) return "";

        StringBuilder itemName = new StringBuilder();
        for (String s : args) {
            itemName.append(s).append(" ");
        }
        itemName.deleteCharAt(itemName.length() - 1);

        return itemName.toString().toLowerCase();
    }

    // Function implemented per handler that checks if the arguments passed
    // can support the user inputted command
    protected abstract boolean hasValidArgs(Command command);

    // canHandle is true if CommandType of command matches CommandHandler
    protected boolean canHandle(Command command) {
        return command.getCommandType() == commandType;
    }

    // Passes the command to the next handler if current handler cannot handle request
    // Throws IllegalArgumentException when reaching the end of the Chain of Responsibility
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

    // Assigns the next CommandHandler in the Chain of Responsibility
    @Override
    public void setNext(CommandHandler nextCommandHandler) {
        this.nextCommandHandler = nextCommandHandler;
    }
}
