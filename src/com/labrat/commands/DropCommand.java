package com.labrat.commands;

import com.labrat.actors.Actor;

public class DropCommand extends BaseCommand {
    public DropCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.DROP);
    }

    @Override
    public void execute() {
        actor.drop(argsToString(args));
    }
}