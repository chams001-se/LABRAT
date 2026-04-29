package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.items.Item;

public class UseCommand extends BaseCommand {
    public UseCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.USE);
    }

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    @Override
    public void execute() {
        Item item;
        String itemName = argsToString(args);

        if (actor.getActorState().getActorStateType() == ActorStateType.EXPLORE) {
            item = super.actor.getCurrentRoom().getItem(itemName);
        }
        else {
            item = super.actor.getInventory().getItem(itemName);
        }

        super.actor.getActorState().use(item);
    }
}
