package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.rooms.Direction;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

// Purpose is to print results from the move command

public class MoveHandler extends BaseHandler {
    MoveHandler() {
        super(CommandType.MOVE);
    }

    @Override
    protected boolean hasValidArgs(Command command) {
        String[] args = command.args();
        Direction direction = Direction.fromString(args[0]);

        // Direction successfully resolved
        return true;
    }

    @Override
    public void performRequest(Command command) {
        // Check if current handler can perform request
        if (canHandle(command)) {
            Actor actor = command.actor();
            String[] args = command.args();

            // Check number of arguments
            if (args.length == 0) {
                actor.setResultText(new ResultText("Move where?", PrinterColor.YELLOW));
            }
            else if (args.length == 1 && hasValidArgs(command)) {
                command.execute();
            }
            else {
                actor.setResultText(new ResultText(
                        "You can't move " + args[0] + ".",
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