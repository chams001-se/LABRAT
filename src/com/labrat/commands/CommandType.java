package com.labrat.commands;

import java.util.HashMap;

public enum CommandType {
    MOVE("{m/move} <direction>", "Attempts to move the player into a room towards the specified direction."),
    EXAMINE("{e/examine} <object>", "Provides a deeper description of a specific object in a room."),
    USE("{u/use} <object>", "Uses an object in the users inventory or provides a deeper description of it."),
    TAKE("{t/take} <object>", "Takes an object found in a room and places it in the players inventory."),
    READ("{r/read} <document>", "Read a document that you have acquired."),
    HIDE("{h/hide}", "Hides if there is an area to hide in."),
    UNHIDE("unhide", "Exits out of hiding state."),
    INVENTORY("{i/inventory}", "Provides a list of the items in the user's inventory."),
    CLOSEINVENTORY("close inventory", "Closes the inventory."),
    HELP("help", "Provides summary of all available commands."),
    QUIT("quit", "Exits the game.");

    private final String commandNotation;       // How the command is called
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

