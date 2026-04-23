package com.labrat.actors;

import com.labrat.items.Item;
import com.labrat.permissions.CommandPermissions;
import com.labrat.rooms.*;

import java.util.Map;

public interface Actor {
    void setCurrentRoom(Room selectedRoom);
    Room getCurrentRoom();

    void addItem(Item item);
    Item getInventoryItem(String name);
    Map<String, Item> getInventory();
    boolean hasItem(String name);

    void setSneaking(boolean hidden);
    boolean isSneaking();

    CommandPermissions getPermissions();
    void setPermissions(CommandPermissions permissions);

    // Since the coordinate system to determine rooms has been removed all methods in the actor interface pertaining to coordinates have been removed
}
