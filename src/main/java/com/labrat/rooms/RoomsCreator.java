package com.labrat.rooms;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.labrat.items.Item;
import com.labrat.items.ItemsCreator;
import com.labrat.view.ResultFormatter;

import java.util.HashMap;
import java.util.Map;

// Initializes rooms with their respective properties to allow the player to traverse them
/* IF ERROR CAUSED BY LOOPING MAIN FUNCTION, IT IS DUE TO A BAD ROOM OR ITEM NAME IN JSON */

public class RoomsCreator {
    // Declare variables
    private final String filename = "/rooms/RoomProperties.json";
    private ObjectMapper mapper = new ObjectMapper();
    private RoomsCreatorMap data;

    private static RoomsCreator rc;
    private RoomsCreator() {} // private constructor for singleton pattern

    public static RoomsCreator getInstance() {
        if (rc == null) {
            rc = new RoomsCreator();
        }
        return rc;
    }

    public Room getStartRoom() {
        try {
            Map<String, Item> items = ItemsCreator.getInstance().getItemMap();

            data = mapper.readValue(getClass().getResourceAsStream(filename), RoomsCreatorMap.class);

            // The rooms must be wired together, done through the room itself.
            // Another benefit of splitting the design of room creation and room wiring is that the rooms must be created
            // to actually link them together, or else a room could have an exit to a null room reference.
            for (var entry : data.rooms.entrySet()) {
                entry.getValue().setInternalName(entry.getKey());
                entry.getValue().linkRooms(data.rooms);
                entry.getValue().linkItems(items);
            }

            //Room startroom = data.rooms.get("startroom");
            //System.out.println(startroom.toString());

            //Room endroom = data.rooms.get("endroom");
            //System.out.println(endroom.toString());

            return data.rooms.get("startroom");
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException("No return reached at getStartRoom()");
    }
}