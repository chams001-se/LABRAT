package com.labrat.rooms;

import com.labrat.view.PrinterColor;

/*
// Applies the builder design pattern through the separation of Room parameters into individual
// methods to harvest data for actually creating a room. Should not be used for anything but Room attributes
 */

public class RoomBuilder {
    // Declare variables
    String description;
    PrinterColor descriptionColor;
    String id;

    RoomBuilder() {
        // Default color for descriptions
        descriptionColor = PrinterColor.DEFAULT;
        id = "";
    }

    public RoomBuilder withDescription(String desc) {
        this.description = desc;
        return this;
    }

    public RoomBuilder withDescriptionColor(PrinterColor c) {
        this.descriptionColor = c;
        return this;
    }

    public Room build() {
        return new Room(description, descriptionColor);
    }
}
