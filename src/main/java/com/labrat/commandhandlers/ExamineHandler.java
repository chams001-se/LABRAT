package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.ItemType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

// Purpose is to get extra information about an item

public class ExamineHandler extends BaseItemHandler {
    ExamineHandler() {
        super(CommandType.EXAMINE, ItemType.EXAMINABLE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        return itemInInventory(command) || itemInRoom(command);
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (super.canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            // Check number of arguments
            if (args.length == 0) {
                actor.setResultText(new ResultText("Examine what?", PrinterColor.YELLOW));
            }
            else if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText(
                        "You can't examine that.",
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
