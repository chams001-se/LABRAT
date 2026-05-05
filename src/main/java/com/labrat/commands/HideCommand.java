package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.items.Item;
import com.labrat.rooms.Room;

public class HideCommand extends BaseCommand {
    public HideCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.HIDE);
    }

    @Override
    public void execute() {
        // Get item from current room
        Room room = super.actor.getCurrentRoom();
        Item item = room.getItem(super.argsToString(super.args));

        super.actor.getActorState().hide(item);
    }
}