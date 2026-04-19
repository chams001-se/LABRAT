package com.labrat.commandhandlers;

import com.labrat.commands.Command;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

public class TakeHandler extends BaseHandler{
    private boolean canHandle(Command command) {
        return command.getCommandType() == CommandType.TAKE;
    }
    @Override
    public ResultText performRequest(Command command){
        if (canHandle(command)){
            String[] args = command.getArgs();

            if (args.length < 1) {
                return new ResultText("Take what?", PrinterColor.YELLOW);
            }

            if (command.hasValidArgs()){
                command.execute();
                return new ResultText("You have taken " + args[0].toUpperCase() + "!", PrinterColor.BLUE);
            }  else {
                return new ResultText("Cannot take " + args[0].toUpperCase() + ".", PrinterColor.RED);
            }
        }
        return super.performRequest(command);
    }
}
