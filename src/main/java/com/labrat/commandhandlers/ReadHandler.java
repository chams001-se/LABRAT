package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.ItemType;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

// Purpose is to print results from the read command

public class ReadHandler extends BaseItemHandler {
    ReadHandler() {
        super(CommandType.READ, ItemType.READABLE);
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
            else if (super.hasValidArgs(command)) {
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
