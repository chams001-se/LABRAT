package com.labrat.commands;

import com.labrat.actors.Actor;

public class HelpCommand extends BaseCommand {

    public HelpCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.HELP);
    }

    @Override
    public void execute() {
        super.actor.getActorState().help();
    }
}
