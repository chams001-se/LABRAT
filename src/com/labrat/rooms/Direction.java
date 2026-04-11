package com.labrat.rooms;

import java.util.HashMap;

public enum Direction {
    NORTH("north"),
    NORTHEAST("northeast"),
    EAST("east"),
    SOUTHEAST("southeast"),
    SOUTH("south"),
    SOUTHWEST("southwest"),
    WEST("west"),
    NORTHWEST("northwest")
    ;

    public final String dir;

    private Direction(String dir) {
        this.dir = dir;
    }

    private static final HashMap<String, Direction> dirHash = new HashMap<String, Direction>();

    static {
        for (Direction d : Direction.values()){
            dirHash.put(d.name().toLowerCase(), d);
        }
    }
    public static boolean isValidDirection(String str) {
        return dirHash.containsKey(str.toLowerCase());
    }

    // Simply converts a string into a direction
    public static Direction fromString(String str){
        for (Direction type : Direction.values()) {
            if (type.name().equalsIgnoreCase(str)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid direction : " + str);
    }

    @Override
    public String toString() {
        return dir;
    }
}
