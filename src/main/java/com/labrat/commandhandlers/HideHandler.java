package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class HideHandler extends BaseItemHandler {
    HideHandler() {
        super(CommandType.HIDE, ItemType.HIDEABLE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        // Item in Room that is Hideable
        return itemInRoom(command);
    }

    @Override
    public void performRequest(Command command) {
        Actor actor = command.actor();
        String[] args = command.args();

        // Check if current handler can perform request
        if (super.canHandle(command)) {
            if (args.length == 0){
                actor.setResultText(new ResultText("Hide where?", PrinterColor.DARK_YELLOW));
            } else if (hasValidArgs(command)) {
                command.execute();
            } else {
                actor.setResultText(new ResultText(
                        "You can't hide there.",
                        PrinterColor.RED,
                        SoundEffect.COMMANDERROR));
            }

        } else {
            super.performRequest(command);
        }

    }
}
