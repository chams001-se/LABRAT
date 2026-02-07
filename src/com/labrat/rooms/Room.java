package com.labrat.rooms;

import java.util.Map;
import java.util.HashMap;

public class Room {
    private Map<Direction, Room> exits;
    private String description;

    public Room() {
        this.exits = new HashMap<>();
    }

    public void setExit(Direction direction, Room room) {
        this.exits.put(direction, room);
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public Room getExit(Direction direction) {
        return this.exits.get(direction);
    }

    public String getDescription() {
        return this.description;
    }
}
