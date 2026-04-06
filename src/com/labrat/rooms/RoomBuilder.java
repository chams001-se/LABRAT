package com.labrat.rooms;
import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;

import java.util.ArrayList;

/*
Applies the builder design pattern through the separation of Room parameters into individual
methods to harvest data for actually creating a room. Should not be used for anything but Room attributes
 */

public class RoomBuilder {
    String name;
    String description;

    // Default color
    PrinterColor descriptionColor = PrinterColor.DEFAULT;

    // FIXME: Change this to discern command of the same type?
    // The underlying issue with this is that the move command currently is based on the actor
    ArrayList<CommandType> commands = new ArrayList<>();

    public RoomBuilder withDescription(String desc){
        this.description = desc;
        return this;
    }

    public RoomBuilder withCommand(CommandType c){
        commands.add(c);
        return this;
    }

    public RoomBuilder withDescriptionColor(PrinterColor c){
        this.descriptionColor = c;
        return this;
    }

    public Room build() {
        return new Room(description, commands, name, descriptionColor);
    }
}
