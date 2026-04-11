package com.labrat.commands;

import com.labrat.actors.Actor;

import java.util.List;

public class HideCommand implements Command {
    private final Actor actor;
    private final List<String> args;
    private final CommandType commandType;

    public HideCommand(Actor actor, List<String> args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.HIDE;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        return true; // hide takes no args
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public void execute() {
        actor.setHidden(true);
        System.out.println("You hide in the shadows.");
    }
}