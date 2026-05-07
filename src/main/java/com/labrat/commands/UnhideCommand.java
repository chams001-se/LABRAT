package com.labrat.commands;

import com.labrat.actors.Actor;

public class UnhideCommand extends BaseCommand {
    public UnhideCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.UNHIDE);
    }

    @Override
    public void execute() {
        // Delegate to ActorState
        actor.unhide();
    }
}