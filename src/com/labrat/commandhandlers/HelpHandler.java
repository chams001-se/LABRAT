package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.ColoredText;
import com.labrat.view.PrinterColor;

// Purpose is to print all user commands and their functionality

public class HelpHandler extends BaseHandler {
    private boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.HELP;
    }

    @Override
    public ColoredText performRequest(Command command) {
        if (canHandle(command)) {
            StringBuilder cmdlist = new StringBuilder("VALID COMMANDS\n");
            for (CommandType c : CommandType.values()){
                cmdlist.append(c.getCommandNotation()).append(": ").append(c.getCommandDescription()).append("\n");
            }

            return new ColoredText(cmdlist.toString(), PrinterColor.DIM);
        }
        else {
            return super.performRequest(command);
        }
    }
}
