package com.labrat.commands;

import com.labrat.actors.Actor;

import java.util.Arrays;

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

        /*
        // Permissions check for current state
        if(!actor.getPermissions().isAllowed(type)){
            throw new IllegalArgumentException("You can't do that right now.");
        }

         */

        switch (type) {
            case MOVE:
                return new MoveCommand(actor, args);
            case EXAMINE:
                return new ExamineCommand(actor, args);
            case TAKE:
                return new TakeCommand(actor, args);
            case USE:
                return new UseCommand(actor, args);
            case READ:
                return new ReadCommand(actor, args);
            case INVENTORY:
                return new InventoryCommand(actor, args);
            case CLOSEINVENTORY:
                //TODO
                //return new CloseInventoryCommand(actor, args);
            case HIDE:
                return new HideCommand(actor, args);
            case UNHIDE:
                //TODO
                //return new UnhideCommand(actor, args);
            case HELP:
                return new HelpCommand(actor, args);
            case QUIT:
                return new QuitCommand(actor, args);
        }

        throw new IllegalArgumentException("Invalid Command: "  + Arrays.toString(input));
    }
}
