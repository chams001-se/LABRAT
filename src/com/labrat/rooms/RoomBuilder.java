package com.labrat.rooms;

import com.labrat.audio.SoundEffect;
import com.labrat.items.Item;
import com.labrat.view.PrinterColor;

import java.util.HashMap;
import java.util.Map;

/*
// Applies the builder design pattern through the separation of Room parameters into individual
// methods to harvest data for actually creating a room. Should not be used for anything but Room attributes
 */

public class RoomBuilder {
    // Declare variables
    String name;
    String description;
    PrinterColor descriptionColor;
    SoundEffect sfx;
    private final Map<String, Item> items;

    RoomBuilder() {
        // Default values
        name = "";
        description = "";
        descriptionColor = PrinterColor.DEFAULT;
        sfx = SoundEffect.MUTE;
        items = new HashMap<>();
    }

    public RoomBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    public RoomBuilder withDescriptionColor(PrinterColor c) {
        this.descriptionColor = c;
        return this;
    }

    public RoomBuilder withSoundEffect(SoundEffect sfx) {
        this.sfx = sfx;
        return this;
    }

    public RoomBuilder withItem(Item item) {
        this.items.put(item.getName().toLowerCase(), item);
        return this;
    }

    public Room build() {
        return new Room(name, description, descriptionColor, sfx, items);
    }
}
