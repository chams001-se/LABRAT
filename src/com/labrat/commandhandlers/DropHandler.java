package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.ItemType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

// Purpose is to support commands which take Items from Rooms

public class DropHandler extends BaseItemHandler {

    DropHandler() {
        super(CommandType.DROP, ItemType.DROPPABLE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // True if there is an item in the inventory that can be dropped
        Actor actor = command.actor();
        String[] args = command.args();
        String itemName = super.argsToString(args);

        if (itemName.isEmpty()) { return false; }

        // Check if the item is in inventory
        if (actor.hasItem(itemName)) {
            return actor.getInventoryItem(itemName).isItemType(itemType);
        }
        else {
            return false;
        }
    }

    @Override
    public void performRequest(Command command) {
        if (super.canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            if (args.length == 0) {
                actor.setResultText(new ResultText("Drop what?", PrinterColor.YELLOW));
            }
            else if (super.hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(
                        new ResultText(
                                "You can't drop that.",
                                PrinterColor.RED,
                                SoundEffect.COMMANDERROR
                        )
                );
            }
        }
        else {
            super.performRequest(command);
        }
    }
}
