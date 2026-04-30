package com.labrat.commandhandlers;

import com.labrat.actorinventory.Inventory;
import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.ItemType;

public abstract class BaseItemHandler extends BaseHandler {
    protected final ItemType itemType;

    // Passes child CommandType to BaseHandler
    BaseItemHandler(CommandType commandType, ItemType itemType) {
        super(commandType);
        this.itemType = itemType;
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        Actor actor = command.actor();

        if (actor.getActorState().getActorStateType() == ActorStateType.EXPLORE) {
            return itemInRoom(command);
        }
        else {
            return itemInInventory(command);
        }
    }

    protected boolean itemInRoom(Command command) {
        // True if there is an item in the current room that can be used
        Actor actor = command.actor();
        String[] args = command.args();
        String itemName = super.argsToString(args);

        // Check if item is in current room
        if (!itemName.isEmpty() && actor.getCurrentRoom().hasItem(itemName)) {
            // Get the item and check if it is usable by the handler
            return actor.getCurrentRoom().getItem(itemName).isItemType(itemType);
        }
        else {
            return false;
        }
    }

    protected boolean itemInInventory(Command command) {
        // True if there is an item in the current room that can be used
        Actor actor = command.actor();
        String[] args = command.args();
        String itemName = super.argsToString(args);
        Inventory inv = actor.getInventory();

        // Check if item is in current room
        if (!itemName.isEmpty() && inv.hasItem(itemName)) {
            return inv.getItem(itemName).isItemType(itemType);
        }
        else {
            return false;
        }
    }
}
