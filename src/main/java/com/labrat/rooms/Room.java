package com.labrat.rooms;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.labrat.items.Item;
import com.labrat.model.ResultTextImport;
import com.labrat.view.ResultText;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Room {
    private final Map<String, Room> linkedExits;
    private final Map<String, Item> linkedItems;

    // Generic Constructor for JSON
    public Room() {
        this.linkedExits = new HashMap<>();
        this.linkedItems = new HashMap<>();
    }

    private String internalName;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private ResultTextImport description;

    @JsonProperty("exits")
    private Map<String, Exit> exits;

    @JsonProperty("items")
    private List<String> items;

    // Directional Methods
    private boolean isValidDirection(String direction) {
        return linkedExits.containsKey(direction);
    }

    public boolean isOpenExit(Direction direction) {
        String dir = direction.toString();
        if (isValidDirection(dir)) {
            Exit exit = exits.get(dir);
            System.out.println("isOpenExit: " + !exit.isLocked());
            return !exit.isLocked();
        }
        else { return false; }
    }

    // Getters
    public String getInternalName() {
        return internalName;
    }

    public String getName() {
        return name;
    }

    public Room getRoomInDir(Direction direction) {
        return linkedExits.get(direction.toString());
    }

    public Map<String, Exit> getExits() {
        return exits;
    }

    public ResultText getRoomText() {
        return description.newResultText();
    }

    // Item Methods
    public Map<String, Item> getRoomItems() {
        return linkedItems;
    }

    public Item getItem(String itemName) {
        return linkedItems.get(itemName);
    }

    public void removeItem(String itemName) {
        linkedItems.remove(itemName);
    }

    public void addItem(Item item) {
        linkedItems.put(item.getName().toLowerCase(), item);
    }

    public boolean hasItem(String itemName) {
        return linkedItems.containsKey(itemName);
    }

    public void setInternalName(String internalName) {
        this.internalName = internalName;
    }

    // Link rooms at runtime
    public void linkRooms(Map<String, Room> allRooms) {
        for (var entry : exits.entrySet()) {
            String target = entry.getValue().getTarget();
            linkedExits.put(entry.getKey(), allRooms.get(target));
        }
    }

    // Link items at runtime
    public void linkItems(Map<String, Item> allItems) {
        if (items != null) {
            for (String item : items) {
                if (allItems.containsKey(item)) {
                    linkedItems.put(item, allItems.get(item));
                }
                else {
                    throw new RuntimeException("Item mismatch in " + name + ": "
                            + item + "\nCheck RoomProperties.json -> " + name +" -> \"items\" for typos.");
                }
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
