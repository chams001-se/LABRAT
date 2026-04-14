package com.labrat.commands;

import com.labrat.view.ResultText;

import java.util.List;

public class HelpCommand implements Command {
    public CommandType commandType;

    public HelpCommand() {
        this.commandType = CommandType.HELP;
    }

    @Override
    public String[] getArgs() {
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
    public ResultText getResult() {
        return null;
    }

    @Override
    public void execute() {
        // Do nothing
    }
}
