package com.labrat.commandhandlers;

import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

// Purpose is to print all user commands and their functionality

public class HelpHandler extends BaseHandler {
    private boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.HELP;
    }

    @Override
    public ResultText performRequest(Command command) {
        if (canHandle(command)) {
            StringBuilder cmdlist = new StringBuilder("VALID COMMANDS\n");
            for (CommandType c : CommandType.values()){
                cmdlist.append(c.getCommandNotation()).append(": ").append(c.getCommandDescription()).append("\n");
            }

            return new ResultText(cmdlist.toString(), PrinterColor.DIM, SoundEffect.BEEP);
        }
        else {
            return super.performRequest(command);
        }
    }
}
