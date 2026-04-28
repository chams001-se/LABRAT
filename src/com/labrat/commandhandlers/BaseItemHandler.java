package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.ItemType;

public abstract class BaseItemHandler extends BaseHandler {
    protected final ItemType itemType;

    // Passes child CommandType to BaseHandler
    BaseItemHandler(CommandType commandType, ItemType itemType) {
        this.itemType = itemType;
        super(commandType);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // True if there is an item in the current room that can be used
        Actor actor = command.actor();
        String[] args = command.args();
        String itemName = super.argsToString(args);

        if (itemName.isEmpty()) { return false; }

        // Check if item is in current room
        if (actor.getCurrentRoom().hasItem(itemName)) {
            // Get the item and check if it is usable by the handler
            return actor.getCurrentRoom().getItem(itemName).isItemType(itemType);
        }
        // Check if the item is in inventory
        else if (actor.hasItem(itemName)) {
            return actor.getInventoryItem(itemName).isItemType(itemType);
        }
        else {
            return false;
        }
    }
}
