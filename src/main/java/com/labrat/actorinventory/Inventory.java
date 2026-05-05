package com.labrat.actorinventory;

import com.labrat.items.Item;

import java.util.HashMap;
import java.util.Map;

public class Inventory implements ActorInventory {
    private final Map<String, Item> inventory;

    public Inventory() {
        inventory = new HashMap<>();
    }

    @Override
    public void addItem(Item item) {
        inventory.put(item.getInternalName().toLowerCase(), item);
    }

    @Override
    public void removeItem(String name) {
        inventory.remove(name.toLowerCase());
    }

    @Override
    public Item getItem(String name) {
        return inventory.get(name.toLowerCase());
    }

    @Override
    public Map<String, Item> getItemMap() {
        return inventory;
    }

    @Override
    public boolean hasItem(String name) {
        return inventory.containsKey(name.toLowerCase());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("INVENTORY\n");

        if (inventory.isEmpty()){
            sb.append("N/A");
            return sb.toString();
        }
        // Iterate over inventory, create tabs
        int i = 0;
        for (String item : inventory.keySet()) {
            i += 1 % 3;
            sb.append(inventory.get(item).getName()).append("\t");
            if (i == 3) sb.append("\n");
        }

        return sb.toString();
    }
}
