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
        int count = 0;
        int size = inventory.size();

        for (Item item : inventory.values()) {
            count++;

            sb.append(item.getName());

            // Add comma+tab unless this is the LAST item overall
            if (count < size) {
                sb.append(",\t");
            }

            // Newline every 3 items
            if (count % 3 == 0) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }
}
