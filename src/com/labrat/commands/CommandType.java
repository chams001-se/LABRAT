package com.labrat.commands;

import java.util.HashMap;

public enum CommandType {
    MOVE("{m/move} <direction>", "Attempts to move the player into a room towards the specified direction."),
    EXAMINE("{e/examine} <item>", "Provides a deeper description of a specific item in a room."),
    USE("{u/use} <item>", "Uses an item in the current room, or in the user's inventory."),
    READ("{r/read} <document>", "Read a document in the current room."),
    TAKE("{t/take} <item>", "Takes an item in a room and places it in the player's inventory."),
    DROP("{d/drop} <item>", "Drops an item from the player's inventory and places it in the current room."),
    HIDE("{h/hide}", "Hides if there is an area to hide in."),
    UNHIDE("{uh/unhide}", "Exits out of hiding state."),
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
            throw new IllegalArgumentException("Invalid command type: "
                    + str + "\nType \"help\" for valid commands");
        }
    }

    public String getCommandNotation() {
        return commandNotation;
    }

    public String getCommandDescription() {
        return commandDescription;
    }
}

