package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.ItemType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

// Purpose is to support commands which take Items from Rooms

public class TakeHandler extends BaseItemHandler {

    TakeHandler() {
        super(CommandType.TAKE, ItemType.TAKEABLE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        return super.itemInRoom(command);
    }

    @Override
    public void performRequest(Command command) {
        if (super.canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            if (args.length == 0) {
                actor.setResultText(new ResultText("Take what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(
                        new ResultText(
                                "You can't take that.",
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
