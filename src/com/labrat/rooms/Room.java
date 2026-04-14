package com.labrat.rooms;

import java.util.AbstractMap;
import java.util.Map;
import java.util.HashMap;

import com.labrat.items.*;
import com.labrat.items.Readable;
import com.labrat.view.ResultText;
import com.labrat.view.PrinterColor;

public class Room {
    /* HashMap exits
    // Direction:   The Direction that the player can travel
    // Room:        The Room that the player will end up in if they move towards Direction
    // Boolean:     TRUE: Player may move from currentRoom to Room
    //              FALSE: Player may not move to Room due to a condition, such as a locked door
     */
    private Map<Direction, Map.Entry<Room, Boolean>> exits;
    private final Map<String, Item> items;
    private ResultText description;

    public Room(String rawDescription, PrinterColor descriptionColor) {
        this.exits = new HashMap<>();
        this.items = new HashMap<>();
        this.description = new ResultText(rawDescription, descriptionColor);
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

    public Room getExit(Direction direction) {
        Map.Entry<Room, Boolean> mapEntry = exits.get(direction);
        return mapEntry.getKey();
    }

    public ResultText getColoredText() {
        return description;
    }

    public void addItem(Item item) {
        items.put(item.getName().toLowerCase(), item);
    }

    public Item getItem(String name) {
        return items.get(name.toLowerCase());
    }

    public Item removeItem(String name) {
        return items.remove(name.toLowerCase());
    }
}
