package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class UseHandler extends BaseHandler{
    private boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.USE;
    }
    @Override
    public ResultText performRequest(Command command){
        if (canHandle(command)){
            String[] args = command.getArgs();

            if (args.length != 1) {
                return new ResultText("Use what?", PrinterColor.YELLOW);
            }

            if (command.hasValidArgs()){
                command.execute();
                return command.getResult();
            }
        }
        return super.performRequest(command);
    }
}
