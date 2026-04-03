package com.labrat.rooms;

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

    public static boolean isValidDirection(String str) {
        for (Direction type : Direction.values()) {
            if (type.name().equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
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
