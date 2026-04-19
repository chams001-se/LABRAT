package com.labrat.commands;

import com.labrat.actors.Actor;

import java.util.Arrays;

public class CommandParser {
    private final Actor actor;

    public CommandParser(Actor act) {
        actor = act;
    }

    // TODO: Get actor's state, change parse behavior on types of
    //       available commands based on state
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

        switch (type) {
            case MOVE:
                return new MoveCommand(actor, args);
            case EXAMINE:
                //TODO Implement Actor and CommandParser states first
                new ExamineCommand(actor, args);
                break;
            case TAKE:
                //TODO Figure out how to do items
                return new TakeCommand(actor, args);
            case USE:
                return new UseCommand(actor, args);
            case READ:
                return new ReadCommand(actor, args);
            case INVENTORY:
                return new InventoryCommand(actor, args);
            case HIDE:
                //TODO Implement Actor and CommandParser states first
                //return new HideCommand(actor);
                break;
            case HELP:
                return new HelpCommand();
            case QUIT:
                return new QuitCommand();
        }
        throw new IllegalArgumentException("Invalid Command: "  + Arrays.toString(input));
    }
}
