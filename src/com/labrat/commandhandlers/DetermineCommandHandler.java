package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.CommandType;
import com.labrat.view.ColoredText;
import com.labrat.view.PrinterColor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DetermineCommandHandler implements CommandHandler {
    private Actor actor;
    Map<CommandType, CommandHandler> handlers = new HashMap<>();
    public DetermineCommandHandler(Actor act){
        this.actor = act;

        // Create the handlers here since they will likely be reused to perform multiple requests.
        handlers.put(CommandType.MOVE, new MoveHandler(actor));
        handlers.put(CommandType.HELP, new HelpHandler());
    }

    public Boolean isExit(String[] input){
        return CommandType.fromString(input[0]) == CommandType.QUIT;
    }
    public ColoredText performRequest(String[] input){
        CommandHandler delegate;
        CommandType type = CommandType.fromString(input[0]);
        delegate = handlers.get(type);

        if (delegate == null) return new ColoredText(("Invalid Command: " + Arrays.toString(input)), PrinterColor.LIGHT_RED);

        return delegate.performRequest(input);
    }
}
