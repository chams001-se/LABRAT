package com.labrat.rooms;

import java.util.AbstractMap;
import java.util.Map;
import java.util.HashMap;

import com.labrat.audio.SoundEffect;
import com.labrat.items.*;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

public class Room {
    /* HashMap exits
    // Direction:   The Direction that the player can travel
    // Room:        The Room that the player will end up in if they move towards Direction
    // Boolean:     TRUE: Player may move from currentRoom to Room
    //              FALSE: Player may not move to Room due to a condition, such as a locked door
     */
    private final Map<Direction, Map.Entry<Room, Boolean>> exits;
    private final Map<String, Item> items;
    private final ResultText roomText;
    private final String name;

    public Room(String name, String description, PrinterColor descriptionColor, SoundEffect sfx, Map<String,Item> items) {
        this.name = name;
        this.exits = new HashMap<>();
        this.items = items;
        this.roomText = new ResultText(description, descriptionColor, sfx);
    }

    public void setExit(Direction direction, Room room, Boolean bool) {
        exits.put(direction, new AbstractMap.SimpleEntry<>(room, bool));
    }

    private boolean isValidDirection(Direction direction) {
        return exits.containsKey(direction);
    }

    public boolean isOpenExit(Direction direction) {
        if (isValidDirection(direction)) {
            Map.Entry<Room, Boolean> mapEntry = exits.get(direction);
            return mapEntry.getValue();
        }
        else { return false; }
    }

    public String getName() {
        return name;
    }

    public Room getExit(Direction direction) {
        Map.Entry<Room, Boolean> mapEntry = exits.get(direction);
        return mapEntry.getKey();
    }

    public Map<String, Item> getRoomItems() {
        return items;
    }

    public ResultText getRoomText() {
        return roomText;
    }

    public Item getItem(String name) {
        return items.get(name.toLowerCase());
    }

    public Item removeItem(String name) {
        return items.remove(name.toLowerCase());
    }

    public void addItem(Item item) {
        items.put(item.getName().toLowerCase(), item);
    }

    public boolean hasItem(String itemName) {
        return items.containsKey(itemName);
    }
}
