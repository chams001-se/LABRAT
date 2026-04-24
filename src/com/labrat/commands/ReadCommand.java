package com.labrat.commands;

import com.labrat.actors.Actor;

public class ReadCommand extends BaseCommand {
    public ReadCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.READ);
    }

    @Override
    public void execute() {
        actor.read(argsToString(args));
    }
}