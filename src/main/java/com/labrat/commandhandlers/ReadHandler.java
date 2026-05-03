package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

// Purpose is to print results from the read command

public class ReadHandler extends BaseItemHandler {
    ReadHandler() {
        super(CommandType.READ, ItemType.READABLE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        Actor actor = command.actor();
        String itemName = argsToString(command.args());

        // Check inventory first
        Item item = actor.getInventory().getItem(itemName);
        if (item != null && item.isItemType(ItemType.READABLE)) return true;

        // Fall back to room - fixed items are usable in place
        item = actor.getCurrentRoom().getItem(itemName);
        return item != null && item.isItemType(ItemType.READABLE) && !item.isItemType(ItemType.TAKEABLE);
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            // Check number of arguments
            if (args.length == 0) {
                actor.setResultText(new ResultText("Read what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText(
                        "You can't read that.",
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
