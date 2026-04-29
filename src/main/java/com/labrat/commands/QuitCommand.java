package com.labrat.commands;

import com.labrat.actors.Actor;

public class QuitCommand extends BaseCommand {

    public QuitCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.QUIT);
    }

    @Override
    public void execute() {
        super.actor.quit();
    }
}
