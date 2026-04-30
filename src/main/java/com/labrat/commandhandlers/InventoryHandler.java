package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class InventoryHandler extends BaseHandler {
    InventoryHandler() {
        super(CommandType.INVENTORY);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        String[] args = command.args();

        String arg = super.argsToString(args);

        return (arg.equals("open") || arg.equals("close"));
    }

    @Override
    public void performRequest(Command command) {
        if (super.canHandle(command)) {
            Actor actor = command.actor();

            if (hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText(
                                "Use \"inventory open\" to open inventory.",
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
