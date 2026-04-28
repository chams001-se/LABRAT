package com.labrat.items;

public enum ItemType {
    // Actions
    VISIBLE,            // Item description that is displayed upon entering room
    TAKABLE,            // Player is able to take the item and remove it from the current room
    DROPPABLE,          // Player is able to drop the item and place it in the current room
    USABLE,             // Player is able to use the item
    EXAMINABLE,         // Player is able to examine the item
    READABLE,           // Player is able to read the item

    // Categories
    KEY,
    LOCK
}
