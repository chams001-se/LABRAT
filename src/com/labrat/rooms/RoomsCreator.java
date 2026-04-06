package com.labrat.rooms;

import com.labrat.commands.CommandType;
import com.labrat.view.PrinterColor;

import java.awt.*;

import static com.labrat.rooms.Direction.*;

public class RoomsCreator {
    Room firstRoom;
    Room secondRoom;

    Room thirdRoom;

    Room fourthRoom;

    // No room map needed anymore since no coordinate system

    public RoomsCreator() {
        // Builder pattern applied to create each individual room, not being responsible for wiring rooms.
        // If the builder pattern were to wire rooms together, it would intrude on it's purpose of creating a room,
        // not managing multiple rooms.

        firstRoom = new RoomBuilder()
                .withDescription("Hello World!")
                .withDescriptionColor(PrinterColor.LIGHT_MAGENTA)
                .withCommand(CommandType.MOVE)
                .build();

        secondRoom = new RoomBuilder()
                .withDescription("It's dark in here!")
                .withDescriptionColor(PrinterColor.LIGHT_BLUE)
                .withCommand(CommandType.MOVE)
                .build();

        thirdRoom = new RoomBuilder()
                .withDescription("Wow! This is a hallway... Amazing!")
                .withDescriptionColor(PrinterColor.LIGHT_CYAN)
                .withCommand(CommandType.MOVE)
                .build();

        fourthRoom = new RoomBuilder()
                .withDescription("It's very humid in here.")
                .withCommand(CommandType.MOVE)
                .build();

        // The rooms must be wired together, done through the room itself.
        // Another benefit of splitting the design of room creation and room wiring is that the rooms must be created
        // to actually link them together, or else a room could have an exit to a null room reference.
        firstRoom.setExit(SOUTH, secondRoom);
        firstRoom.setExit(WEST, thirdRoom);

        secondRoom.setExit(NORTH, firstRoom);
        secondRoom.setExit(EAST, fourthRoom);

        thirdRoom.setExit(SOUTHEAST, secondRoom);
        thirdRoom.setExit(EAST, firstRoom);

        fourthRoom.setExit(WEST, secondRoom);

    }

    // Crucial for the user to spawn in the appropriate room.
    public Room getStartRoom() {
        return firstRoom;
    }
}
