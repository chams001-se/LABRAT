package com.labrat.rooms;

import com.labrat.commands.CommandType;

import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

import com.labrat.items.Examinable;
import com.labrat.items.Item;
import com.labrat.view.ColoredText;
import com.labrat.view.PrinterColor;

public class Room {
    private Map<Direction, Room> exits;
    private String name;
    private ArrayList<CommandType> commands;
    private ColoredText description;

    public Room(String rawDescription, ArrayList<CommandType> commands, String name, PrinterColor descriptionColor) {
        exits = new HashMap<>();
        this.description = new ColoredText(rawDescription, descriptionColor);
        this.commands = commands;
        this.name = name;
    }

    private final Map<String, Examinable> examinables = new HashMap<>();
    public void addItem(Item item) {
        examinables.put(item.getName().toLowerCase(), item);
    }

    public Examinable getExaminable(String name) {
        return examinables.get(name.toLowerCase());
    }

    public boolean isValidExit(Direction direction) {
        return exits.containsKey(direction);
    }

    public void setExit(Direction d, Room r){
        exits.put(d, r);
    }

    public Room getExit(Direction direction) {
        return this.exits.get(direction);
    }

    public ColoredText getColoredText() {
        return description;
    }

    public Item removeItem(String name) {
        Examinable obj = examinables.remove(name.toLowerCase());
        if (obj instanceof Item item) {
            return item;
        }
        return null;
    }
}
