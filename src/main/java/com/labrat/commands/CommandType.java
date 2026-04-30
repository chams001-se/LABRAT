package com.labrat.commands;

import java.util.HashMap;

public enum CommandType {
    MOVE("{m/move} <direction>", "Attempts to move the player into a room towards the specified direction.", new String[]{"m"}),
    EXAMINE("{e/examine} <item>", "Provides a deeper description of a specific item in a room.", new String[]{"e", "see"}),
    USE("{u/use} <item>", "Uses an item in the current room, or in the user's inventory.", new String[]{"u"}),
    READ("{r/read} <document>", "Read a document in the current room.", new String[]{"r"}),
    TAKE("{t/take} <item>", "Takes an item in a room and places it in the player's inventory.", new String[]{"t"}),
    DROP("{d/drop} <item>", "Drops an item from the player's inventory and places it in the current room.", new String[]{"d"}),
    HIDE("{h/hide}", "Hides if there is an area to hide in.", new String[]{"h"}),
    UNHIDE("{uh/unhide}", "Exits out of hiding state.", new String[]{"uh"}),
    INVENTORY("{i o/inventory open}", "Opens the inventory menu to focus on items in the player's inventory.", new String[]{"i"}),
    CLOSEINVENTORY("{i c/inventory close}", "Closes the inventory.", new String[]{"i c"}),
    HELP("help", "Provides summary of all available commands.", new String[]{"?"}),
    QUIT("quit", "Exits the game.", new String[]{"exit"});

    // In case there is a command that should not have any aliases
    CommandType(String commandNotation, String commandDescription) {
        this(commandNotation, commandDescription, new String[0]);
    }

    CommandType(String commandNotation, String commandDescription, String[] commandAliases) {
        this.commandAliases = commandAliases;
        this.commandNotation = commandNotation;
        this.commandDescription = commandDescription;
    }


    private final String[] commandAliases;
    private final String commandNotation;       // How the command is called
    private final String commandDescription;    // What the command does

    // Static map index for CommandType enum
    private static final HashMap<String, CommandType> comMap = new HashMap<>();
    // Runs once class is loaded into memory
    static {
        for (CommandType c : CommandType.values()) {
            comMap.put(c.name().toLowerCase(), c);
            for (String alias : c.getCommandAliases()){
                comMap.put(alias.toLowerCase(), c);
            }
        }
    }

    public String[] getCommandAliases(){
        return commandAliases;
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

