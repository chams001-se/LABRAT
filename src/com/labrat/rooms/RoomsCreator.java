package com.labrat.rooms;

import com.labrat.items.Item;
import com.labrat.items.ItemsCreator;
import com.labrat.items.LockItem;
import com.labrat.view.PrinterColor;

import java.util.HashMap;
import java.util.Map;

import static com.labrat.rooms.Direction.*;

// Initializes rooms with their respective properties to allow the player to traverse them

public class RoomsCreator {
    // Declare variables
    private final Map<Integer, Room> rooms;
    private final Map<Integer, Item> items;

    public RoomsCreator() {
        // Initialize variables
        rooms = new HashMap<>();
        items = new ItemsCreator().getItemList();

        // Create NullObject room for index 0
        assignIdToRoom(0,
                new RoomBuilder()
                .withName("Null Room")
                .build()
        );

        /* Builder pattern applied to create each individual room */
        assignIdToRoom(1,
                 new RoomBuilder()
                 .withName("Start Room")
                 .withDescription("Hello World!\nThere is an exit to the north.")
                 .withDescriptionColor(PrinterColor.LIGHT_MAGENTA)
                 .withItem(items.get(1))     // Hello World note (readable, examinable, takable)
                 .withItem(items.get(2))     // Keycard
                 .withLock(NORTH, items.get(3))     // Reader
                 .build()
        );

        assignIdToRoom(2,
                new RoomBuilder()
                        .withName("End Room")
                        .withDescription("You did it!\nThere is an exit to the south.")
                        .withDescriptionColor(PrinterColor.LIGHT_MAGENTA)
                        .build()
        );

        // The rooms must be wired together, done through the room itself.
        // Another benefit of splitting the design of room creation and room wiring is that the rooms must be created
        // to actually link them together, or else a room could have an exit to a null room reference.
        rooms.get(1).setExit(NORTH, rooms.get(2));

        rooms.get(2).setExit(SOUTH, rooms.get(1));
    }

    // Helper method to check if duplicate room with index exists
    private void assignIdToRoom(int id, Room room) {
        if (!rooms.containsKey(id)) {
            rooms.put(id, room);
        }
        else {
            throw new RuntimeException("ItemsCreator: Attempted to override "
                    + rooms.get(id).toString() + " at " + id +
                    " with " + room.toString());
        }
    }

    // Sets the user to start in the assigned room
    public Room getStartRoom() {
        return rooms.get(1);
    }
}