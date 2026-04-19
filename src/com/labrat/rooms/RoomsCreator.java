package com.labrat.rooms;

import com.labrat.audio.SoundEffect;
import com.labrat.items.Item;
import com.labrat.items.Note;
import com.labrat.view.PrinterColor;

import java.util.ArrayList;
import java.util.List;

import static com.labrat.audio.SoundEffect.MUTE;
import static com.labrat.rooms.Direction.*;
import static com.labrat.view.PrinterColor.DIM;

// Initializes rooms with their respective properties to allow the player to traverse them

public class RoomsCreator {
    // Declare variables
    List<Room> rooms;

    public RoomsCreator() {
        // Initialize variables
        rooms = new ArrayList<>();

        // Create NullObject room for index 0
        rooms.add(new RoomBuilder()
                .withName("Null Room")
                .build()
        );

        /* Builder pattern applied to create each individual room */
        rooms.add(new RoomBuilder()
                .withName("Start Room")
                .withDescription("Hello World!\nThere is an exit to the south and west.\nThere is a note on the ground titled 'Hello World' and some cheese to the right of it.")
                .withDescriptionColor(PrinterColor.LIGHT_MAGENTA)
                .withItem(new Note(
                        "Hello World",
                        "You hold the 'Hello World' note in your hand.\n'The password is FOOBAR.'",
                        PrinterColor.CYAN,
                        SoundEffect.NOTEPAPER))
                .withItem(new Item(
                        "Wedge Of Cheese",
                        "You hold up the cheese to your nose, it smells funny.",
                        PrinterColor.DARK_YELLOW,
                        MUTE))
                .build()
        );

        rooms.add(new RoomBuilder()
                .withName("Dark Room")
                .withDescription("It's dark in here!\n You see the silhouette of a rock on the floor. \n There is an exit to the north and east.")
                .withDescriptionColor(PrinterColor.LIGHT_BLUE)
                .withSoundEffect(SoundEffect.SCARYROOM)
                        .withItem(new Item("Rock",
                                "It has a happy face scribbled on it.",
                                PrinterColor.MAGENTA,
                                MUTE))
                .build()
        );

        rooms.add(new RoomBuilder()
                .withName("Hall Room")
                .withDescription("Wow! This is a hallway... Amazing!\n There is a bird pecking at the floor. \n There is an exit to the southeast and the east.\nThe southeast door is locked.")
                .withDescriptionColor(PrinterColor.LIGHT_CYAN)
                        .withItem(new Item("Bird",
                                "It looks at you with curiousity",
                                PrinterColor.CYAN,
                                MUTE))
                .build()
        );

        rooms.add(new RoomBuilder()
                .withName("Steam Room")
                .withDescription("It's very humid in here.\nThere is an exit to the west.")
                .build()
        );

        // The rooms must be wired together, done through the room itself.
        // Another benefit of splitting the design of room creation and room wiring is that the rooms must be created
        // to actually link them together, or else a room could have an exit to a null room reference.
        rooms.get(1).setExit(SOUTH, rooms.get(2), true);
        rooms.get(1).setExit(WEST, rooms.get(3), true);

        rooms.get(2).setExit(NORTH, rooms.get(1), true);
        rooms.get(2).setExit(EAST, rooms.get(4), true);

        rooms.get(3).setExit(SOUTHEAST, rooms.get(2), false);
        rooms.get(3).setExit(EAST, rooms.get(1), true);

        rooms.get(4).setExit(WEST, rooms.get(2), true);

        // Set Items

    }

    // Sets the user to start in the assigned room
    public Room getStartRoom() {
        return rooms.get(1);
    }
}
