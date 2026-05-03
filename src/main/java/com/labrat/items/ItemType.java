package com.labrat.items;

import java.util.HashMap;

public enum ItemType {
    // Actions
    VISIBLE,            // Item description that is displayed upon entering room
    TAKEABLE,            // Player is able to take the item and remove it from the current room
    DROPPABLE,          // Player is able to drop the item and place it in the current room
    USABLE,             // Player is able to use the item
    EXAMINABLE,         // Player is able to examine the item
    READABLE,           // Player is able to read the item

    // Categories
    HIDEABLE;


    // Static map index for ItemType enum
    private static final HashMap<String, ItemType> stringToItemTypeMap = new HashMap<>();
    static {
        for (ItemType it : ItemType.values()) {
            stringToItemTypeMap.put(it.name().toLowerCase(), it);
        }
    }

    // Converts a String into an ItemType
    public static ItemType fromString(String str) {
        if (stringToItemTypeMap.containsKey(str)) {
            return stringToItemTypeMap.get(str);
        }
        else {
            throw new IllegalArgumentException("Invalid ItemType: " + str);
        }
    }
}
