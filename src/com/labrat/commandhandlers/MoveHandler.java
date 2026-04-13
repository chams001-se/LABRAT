package com.labrat.commandhandlers;

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
        if (canHandle(command)) {
            List<String> args = command.getArgs();

            // Check number of arguments
            if (args.isEmpty()) {
                return new ResultText("Move where?", PrinterColor.YELLOW);
            }
            else if (args.size() == 1 && command.hasValidArgs()) {
                command.execute();
                return new ResultText("You moved " + args.getFirst() + ".", PrinterColor.YELLOW);
            }
            else {
                return new ResultText("You can't move there.", PrinterColor.YELLOW);
            }
        }
        else {
            return super.performRequest(command);
        }
    }
}