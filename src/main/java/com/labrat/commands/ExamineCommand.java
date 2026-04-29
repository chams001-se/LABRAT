package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.items.Item;

public class ExamineCommand extends BaseCommand {
    public ExamineCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.EXAMINE);
    }

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

        super.actor.getActorState().examine(item);
    }
}