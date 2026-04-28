package com.labrat.rooms;

import java.util.AbstractMap;
import java.util.Map;
import java.util.HashMap;

import com.labrat.audio.SoundEffect;
import com.labrat.items.*;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

public class Room {
    /* HashMaps
    // Direction:   The Direction that the player can travel
    // Room:        The Room that the player will end up in if they move towards Direction
    // LockItem:    A lock that can be unlocked by a KeyItem with matching keyId
    // Item:        Interactable item located in the room
     */
    private final Map<Direction, Room> exits;
    private final Map<Direction, LockItem> locks;
    private final Map<String, Item> items;
    private final ResultText roomText;
    private final String name;

    public Room(String name, String description, PrinterColor descriptionColor, SoundEffect sfx, Map<String, Item> items, Map<Direction, LockItem> locks) {
        this.name = name;
        this.exits = new HashMap<>();
        this.locks = locks;
        this.items = items;
        this.roomText = new ResultText(description, descriptionColor, sfx);
    }

    // Directional Methods
    public void setExit(Direction direction, Room room) {
        exits.put(direction, room);
    }

    private boolean isValidDirection(Direction direction) {
        return exits.containsKey(direction);
    }

    public boolean isOpenExit(Direction direction) {
        if (isValidDirection(direction)) {
            // Check if direction has a lock
            if (locks.containsKey(direction)) {
                return locks.get(direction).isOpen();
            }
            return true;
        }
        else { return false; }
    }

    // Getters
    public String getName() {
        return name;
    }

    public Room getExit(Direction direction) {
        return exits.get(direction);
    }

    public ResultText getRoomText() {
        return roomText;
    }

    // Item Methods
    public Map<String, Item> getRoomItems() {
        return items;
    }

    public Item getItem(String name) {
        return items.get(name.toLowerCase());
    }

    public void removeItem(String name) {
        items.remove(name.toLowerCase());
    }

    public void addItem(Item item) {
        items.put(item.getName().toLowerCase(), item);
    }

    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }

    @Override
    public String toString() {
        return name;
    }
}
