package com.labrat.commandhandlers;

import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

import java.util.List;

// Purpose is to print results from the move command

public class MoveHandler extends BaseHandler {
    private boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.MOVE;
    }

    @Override
    public ResultText performRequest(Command command) {
        // Check if current handler can perform request
        if (canHandle(command)) {
            String[] args = command.getArgs();

            // Check number of arguments
            if (args.length == 0) {
                return new ResultText("Move where?", PrinterColor.YELLOW);
            }
            else if (args.length == 1 && command.hasValidArgs()) {
                command.execute();
                return new ResultText("You moved " + args[0] + ".", PrinterColor.YELLOW, SoundEffect.HUMANFOOTSTEPS);
            }
            else {
                return new ResultText("You can't move " + args[0] + ".", PrinterColor.YELLOW, SoundEffect.COMMANDERROR);
            }
        }
        else {
            return super.performRequest(command);
        }
    }
}