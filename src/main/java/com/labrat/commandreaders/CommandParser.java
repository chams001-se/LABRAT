package com.labrat.commandreaders;

import com.labrat.actors.Actor;
import com.labrat.commands.*;

public class CommandParser {
    private final Actor actor;

    public CommandParser(Actor actor) {
        this.actor = actor;
    }

    public Command parse(String[] input) {
        // Declare variables
        String[] args;

        // Check valid CommandType
        CommandType type = CommandType.fromString(input[0]);

        // Create arguments array and remove first element (CommandType)
        if (input.length >= 2) {
            args = new String[input.length-1];
            System.arraycopy(input, 1, args, 0, input.length - 1);
        }
        else {
            args = new String[0];
        }

        return switch (type) {
            case MOVE       -> new MoveCommand(actor, args);
            case EXAMINE    -> new ExamineCommand(actor, args);
            case TAKE       -> new TakeCommand(actor, args);
            case DROP       -> new DropCommand(actor, args);
            case USE        -> new UseCommand(actor, args);
            case READ       -> new ReadCommand(actor, args);
            case INVENTORY, CLOSEINVENTORY  -> new InventoryCommand(actor, args);
            case HIDE       -> new HideCommand(actor, args);
            case UNHIDE     -> new UnhideCommand(actor, args);
            case HELP       -> new HelpCommand(actor, args);
            case QUIT       -> new QuitCommand(actor, args);
        };
    }
}
