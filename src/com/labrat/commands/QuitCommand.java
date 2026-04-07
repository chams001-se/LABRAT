package com.labrat.commands;

import java.util.List;

public class QuitCommand implements Command {
    public CommandType commandType;

    public QuitCommand() {
        this.commandType = CommandType.QUIT;
    }

    @Override
    public List<String> getArgs() {
        return null;
    }

    @Override
    public boolean hasValidArgs() {
        return true;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public void execute() {
        // Do nothing
    }
}
