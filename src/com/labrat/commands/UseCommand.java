package com.labrat.commands;

import com.labrat.actors.Actor;

public class UseCommand extends BaseCommand {
    public UseCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.INVENTORY);
    }

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    public void execute() {
        actor.use(argsToString(args));
    }
}
