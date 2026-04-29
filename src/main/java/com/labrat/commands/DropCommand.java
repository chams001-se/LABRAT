package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Item;
import com.labrat.rooms.Room;

public class DropCommand extends BaseCommand {
    public DropCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.DROP);
    }

    @Override
    public void execute() {
        // Drop only supports dropping items from the inventory to the room
        Item item = super.actor.getInventory().getItem(super.argsToString(super.args));

        super.actor.getActorState().drop(item);
    }
}