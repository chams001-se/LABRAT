package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Item;
import com.labrat.rooms.Room;

public class TakeCommand extends BaseCommand {
    public TakeCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.TAKE);
    }

    @Override
    public void execute() {
        // Take only supports taking items from the room to the inventory
        Room room = super.actor.getCurrentRoom();
        Item item = room.getItem(super.argsToString(super.args));

        super.actor.getActorState().take(item);
    }
}