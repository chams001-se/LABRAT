package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

// Purpose is to print results from the use command

public class UseHandler extends BaseItemHandler {
    UseHandler() {
        super(CommandType.USE, ItemType.USABLE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        Actor actor = command.actor();
        String itemName = argsToString(command.args());

        // Check inventory first
        Item item = actor.getInventory().getItem(itemName);
        if (item != null && item.isItemType(ItemType.USABLE)) return true;

        // Fall back to room - fixed items are usable in place
        item = actor.getCurrentRoom().getItem(itemName);
        return item != null && item.isItemType(ItemType.USABLE) && !item.isItemType(ItemType.TAKEABLE);
    }

    @Override
    public void performRequest(Command command) {
        if (canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            if (args.length == 0) {
                actor.setResultText(new ResultText("Use what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText(
                        "Item cannot be used or does not exist.",
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
