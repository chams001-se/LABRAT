package com.labrat.commands;

public enum CommandType {
    MOVE;

    public static CommandType fromString(String str){
        for (CommandType type : CommandType.values()) {
            if (type.name().equalsIgnoreCase(str)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid type : " + str);
    }
}

