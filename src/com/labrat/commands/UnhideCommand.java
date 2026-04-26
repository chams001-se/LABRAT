package com.labrat.commands;

import com.labrat.actors.Actor;

public record UnhideCommand(Actor actor, String[] args) implements Command {

    @Override
    public CommandType getCommandType() {
        return CommandType.UNHIDE;
    }

    @Override
    public void execute() {
        // Delegate to ActorState
        actor.unhide();
    }
}