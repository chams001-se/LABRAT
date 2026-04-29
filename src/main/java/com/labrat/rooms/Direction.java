package com.labrat.rooms;

import java.util.HashMap;

public enum Direction {
    NORTH,
    NORTHEAST,
    EAST,
    SOUTHEAST,
    SOUTH,
    SOUTHWEST,
    WEST,
    NORTHWEST;

    // Static map index for Direction enum
    private static final HashMap<String, Direction> stringToDirMap = new HashMap<>();
    static {
        for (Direction d : Direction.values()) {
            stringToDirMap.put(d.name().toLowerCase(), d);
        }
    }

    // Converts a String into a Direction
    public static Direction fromString(String str) {
        if (stringToDirMap.containsKey(str)) {
            return stringToDirMap.get(str);
        }
        else {
            throw new IllegalArgumentException("Invalid direction: " + str);
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
