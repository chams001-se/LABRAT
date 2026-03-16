package com.labrat.roombuilders;

import com.labrat.commands.CommandType;
import com.labrat.rooms.Direction;

import java.util.ArrayList;

public abstract class Room {
    private ArrayList<Direction> exits;
    private ArrayList<CommandType> cmds;
    private String description;

    public Room() {
        this.exits = new ArrayList<>();
        this.cmds = new ArrayList<>();
    }

    public void withRoomExit(Direction direction) {
        this.exits.add(direction);
    }

    public void withCommand(CommandType cmd) {
        this.cmds.add(cmd);
    }

    public ArrayList<Direction> getExits() {
        return exits;
    }

    public boolean isValidExit(Direction direction) {
        for (Direction dir : exits) {
            if (dir == direction) {
                return true;
            }
        }
        return false;
    }

    public boolean isValidCmd(String str) {
        for (CommandType type : cmds) {
            if (type.name().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public String getDescription() {
        return this.description;
    }
}