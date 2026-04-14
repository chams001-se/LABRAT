package com.labrat.actors;
import com.labrat.items.Item;
import com.labrat.rooms.*;

import java.util.HashMap;
import java.util.Map;

public class MainCharacter implements Actor {
    private Room currentRoom;

    // TODO: Replace isSneaking with State Pattern
    private boolean isSneaking = false;

    private final Map<String, Item> inventory = new HashMap<>();

    public MainCharacter() {}

    public void setCurrentRoom(Room selectedRoom) {
        this.currentRoom = selectedRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void addItem(Item item) {
        inventory.put(item.getName().toLowerCase(), item);
    }

    public Item getInventoryItem(String name) {
        return inventory.get(name.toLowerCase());
    }

    public boolean hasItem(String name) {
        return inventory.containsKey(name.toLowerCase());
    }

    public void setSneaking(boolean isSneaking) {
        this.isSneaking = isSneaking;
    }

    public boolean isSneaking() {
        return isSneaking;
    }
}