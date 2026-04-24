package com.labrat.commands;

import com.labrat.actors.Actor;

public class TakeCommand extends BaseCommand {
    public TakeCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.TAKE);
    }

    @Override
    public void execute() {
        actor.take(argsToString(args));
    }
}