package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;

public class UseCommand extends BaseCommand {
    public UseCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.USE);
    }

    // Executing the command calls some form of receiver that contains the functionality that the command delivers.
    @Override
    public void execute() {
        String itemName = argsToString(args);

        Item item = actor.getInventory().getItem(itemName);
        if (item != null && item.isItemType(ItemType.USABLE)) {
            actor.getActorState().use(item);
            return;
        }

        item = actor.getCurrentRoom().getItem(itemName);

        if (item != null && item.isItemType(ItemType.USABLE)) {
            actor.getActorState().use(item);
        }
    }
}
