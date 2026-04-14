package com.labrat.commands;

import java.util.HashMap;

public enum CommandType {
    MOVE("{m/move} <direction>", "Attempts to move the player into a room towards the specified direction."),
    EXAMINE("{e} examine <object>", "Provides a deeper description of a specific object in a room or in the inventory."),
    TAKE("take <object>", "Takes an object found in a room and places it in the players inventory."),
    READ("read <document>", "Read a document that you have acquired."),
    HIDE("hide", "Hides if there is an area to hide in. "),
    HELP("help", "Provides summary of all available commands"),
    QUIT("quit", "Exits the game.");

    private final String commandNotation;       // How the command is formed
    private final String commandDescription;    // What the command does

    // Static map index for CommandType enum
    private static final HashMap<String, CommandType> comMap = new HashMap<>();
    static {
        for (CommandType c : CommandType.values()) {
            comMap.put(c.name().toLowerCase(), c);
        }
    }

    CommandType(String commandNotation, String commandDescription) {
        this.commandNotation = commandNotation;
        this.commandDescription = commandDescription;
    }

    // Converts a String to a CommandType
    public static CommandType fromString(String str) {
        if (comMap.containsKey(str)) {
            return comMap.get(str);
        }
        else {
            throw new IllegalArgumentException("Invalid command type: " + str);
        }
    }

    public String getCommandNotation() {
        return commandNotation;
    }

    public String getCommandDescription() {
        return commandDescription;
    }
}

