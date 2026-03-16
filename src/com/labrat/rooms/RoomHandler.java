package com.labrat.rooms;

import com.labrat.roombuilders.Room;

import java.awt.*;

public class RoomHandler {
    RoomDirector roomDirector;

    public RoomHandler() {
        this.roomDirector = new RoomDirector();
    }

    public Room roomLookup(Point coord) {
        return roomDirector.getRoomMap().get(coord);
    }
}
