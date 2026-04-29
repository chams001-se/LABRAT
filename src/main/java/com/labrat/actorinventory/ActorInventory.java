package com.labrat.actorinventory;

import com.labrat.items.Item;

import java.util.Map;

public interface ActorInventory {
    void addItem(Item item);
    void removeItem(String name);
    Item getItem(String name);
    Map<String, Item> getItemMap();
    boolean hasItem(String name);
}
