package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.items.ItemType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

// Purpose is to print results from the use command

public class UseHandler extends BaseItemHandler {
    UseHandler() {
        super(CommandType.USE, ItemType.USABLE);
    }

    @Override
    public void performRequest(Command command) {
        if (canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            if (args.length == 0) {
                actor.setResultText(new ResultText("Use what?", PrinterColor.YELLOW));
            }
            else if (super.hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText(
                        "You can't use that.",
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
