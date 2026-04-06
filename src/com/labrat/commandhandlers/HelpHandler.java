package com.labrat.commandhandlers;

import com.labrat.commands.CommandType;
import com.labrat.view.ColoredText;
import com.labrat.view.PrinterColor;

public class HelpHandler implements CommandHandler {
    public ColoredText performRequest(String[] input){
        StringBuilder cmdlist = new StringBuilder("VALID COMMANDS\n");
        for (CommandType c : CommandType.values()){
            cmdlist.append(c.getCommandNotation()).append("\n").append(c.getCommandDescription()).append("\n\n");
        }

        return new ColoredText(cmdlist.toString(), PrinterColor.DIM);
    }
}
