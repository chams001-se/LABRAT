package com.labrat.actors;

import com.labrat.items.Item;
import com.labrat.rooms.*;

import java.util.Map;

public interface Actor {
    public void setCurrentRoom(Room selectedRoom);
    public Room getCurrentRoom();

    void addItem(Item item);
    Item getInventoryItem(String name);
    Map<String, Item> getInventory();
    boolean hasItem(String name);

    void setSneaking(boolean hidden);
    boolean isSneaking();

    // Since the coordinate system to determine rooms has been removed all methods in the actor interface pertaining to coordinates have been removed
}
