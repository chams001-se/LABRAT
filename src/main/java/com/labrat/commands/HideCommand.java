package com.labrat.commands;

import com.labrat.actors.Actor;

public class HideCommand extends BaseCommand {
    public HideCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.HIDE);
    }

    @Override
    public void execute() {
        super.actor.getActorState().hide();
    }
}