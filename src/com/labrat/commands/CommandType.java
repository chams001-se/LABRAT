package com.labrat.commands;

public enum CommandType {
    MOVE("move <direction>", "Attempts to move the player into a room depending on the direction chosen."),
    EXAMINE("examine <object>", "Provides a deeper description of certain details of a room, typically mentioned by the room description upon entering."),
    TAKE("take <object>", "Takes an object found in a room and places it in the players inventory."),
    READ("read <document>", "Reads a document that you have acquired."),
    HIDE("hide", "Places the player in a hiding state."),
    HELP("help", "Provides summary of all available commands"),
    QUIT("quit", "Exits the game.");
    String commandNotation; // How the command is formed
    String commandDescription; // What the command does

    CommandType(String commandNotation, String commandDescription) {
        this.commandNotation = commandNotation;
        this.commandDescription = commandDescription;
    }

    public static CommandType fromString(String str){
        for (CommandType type : CommandType.values()) {
            if (type.name().equalsIgnoreCase(str)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid command type: " + str);
    }

    public String getCommandNotation(){
        return commandNotation;
    }

    public String getCommandDescription(){
        return commandDescription;
    }
}

