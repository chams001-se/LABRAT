package com.labrat.commands;

import com.labrat.actors.Actor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CommandParser {
    private final Actor actor;

    public CommandParser(Actor act) {
        actor = act;
    }

    public Command parse(String[] input) {
        CommandType type = CommandType.fromString(input[0]);

        // Create args arraylist, remove first element (CommandType)
        List<String> args = new ArrayList<>(Arrays.asList(input));
        args.removeFirst();

        switch (type) {
            case MOVE:
                return new MoveCommand(actor, args);
            case EXAMINE:
                break;
            case TAKE:
                break;
            case READ:
                break;
            case HIDE:
                break;
            case HELP:
                return new HelpCommand();
            case QUIT:
                return new QuitCommand();
        };
        throw new IllegalArgumentException("Invalid Command: "  + Arrays.toString(input));
    }
}
