package com.labrat.rooms;

import java.util.HashMap;

public enum RoomTheme {
    LAB,
    GREENHOUSE,
    SEWER;

    // Static map index for Direction enum
    private static final HashMap<String, RoomTheme> stringToThemeMap = new HashMap<>();
    static {
        for (RoomTheme t : RoomTheme.values()) {
            stringToThemeMap.put(t.name().toLowerCase(), t);
        }
    }

    // Converts a String into a Direction
    public static RoomTheme fromString(String str) {
        if (stringToThemeMap.containsKey(str)) {
            return stringToThemeMap.get(str);
        }
        else {
            throw new IllegalArgumentException("Invalid room theme: " + str);
        }
    }

    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
