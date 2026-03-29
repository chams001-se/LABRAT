package com.labrat.rooms;

import com.labrat.commands.CommandType;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

public class Room {
    private Map<Direction, Room> exits;
    private String description;
    private String name;
    private ArrayList<CommandType> commands;

    public Room(String description, ArrayList<CommandType> commands, String name) {
        exits = new HashMap<>();
        this.description = description;
        this.commands = commands;
        this.name = name;
    }

    public boolean isValidExit(Direction direction) {
        return exits.containsKey(direction);
    }

    public boolean isValidCmd(String str) {
        for (CommandType type : commands) {
            if (type.name().equals(str)) {
                return true;
            }
        }
        return false;
    }

    public void setExit(Direction d, Room r){
        exits.put(d, r);
    }

    public Room getExit(Direction direction) {
        return this.exits.get(direction);
    }

    public String getDescription() {
        return this.description;
    }
}
