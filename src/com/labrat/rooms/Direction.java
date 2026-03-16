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

    public static boolean isValidDirection(String value) {
        for (Direction type : Direction.values()) {
            if (type.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return dir;
    }
}
