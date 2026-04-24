package com.labrat.commands;

import com.labrat.actors.Actor;

public class InventoryCommand extends BaseCommand {
    public InventoryCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.INVENTORY);
    }

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    public void execute() {
        actor.inventory();
    }
}
