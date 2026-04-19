package com.labrat.commandhandlers;

import com.labrat.audio.SoundEffect;
import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class InventoryHandler extends BaseHandler {
    private boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.INVENTORY;
    }

    @Override
    public ResultText performRequest(Command command) {
        String[] args = command.getArgs();

        if (canHandle(command)) {
            if (command.hasValidArgs()){
                command.execute();
                return command.getResult();
            } else {
                return new ResultText("Unable to provide inventory.");
            }
        }
        return super.performRequest(command);
    }
}
