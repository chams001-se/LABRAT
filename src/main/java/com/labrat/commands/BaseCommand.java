package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.view.ResultText;

public abstract class BaseCommand implements Command {
    protected final Actor actor;
    protected final String[] args;
    protected final CommandType commandType;

    public BaseCommand(Actor actor, String[] args, CommandType commandType) {
        this.actor = actor;
        this.args = args;
        this.commandType = commandType;
    }

    // Converts args String array into a single String
    protected String argsToString(String[] args) {
        StringBuilder sb = new StringBuilder();
        for (String s : args) {
            sb.append(s).append(" ");
        }

        if (!sb.isEmpty()) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString().toLowerCase();
    }

    @Override
    public Actor actor() {
        return actor;
    }

    @Override
    public String[] args() {
        return args;
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    public abstract void execute();
}
