package com.labrat.actors;

import com.labrat.items.Item;
import com.labrat.rooms.*;

public interface Actor {
    public void setCurrentRoom(Room selectedRoom);
    public Room getCurrentRoom();

    void addItem(Item item);
    Item getInventoryItem(String name);
    boolean hasItem(String name);

    void setHidden(boolean hidden);
    boolean isHidden();

    // Since the coordinate system to determine rooms has been removed all methods in the actor interface pertaining to coordinates have been removed
}
