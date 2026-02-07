package com.labrat.rooms;

import java.util.ArrayList;
import java.util.List;

import static com.labrat.rooms.Direction.*;

public class RoomBuilder {
    Room firstRoom = new Room();
    Room secondRoom = new Room();
    List<Room> roomList = new ArrayList<>();

    public RoomBuilder() {
        firstRoom.setExit(NORTH, secondRoom);
        firstRoom.setDescription("This is the first room.");
        roomList.add(firstRoom);

        secondRoom.setExit(SOUTH, firstRoom);
        secondRoom.setDescription("This is the second room.");
        roomList.add(secondRoom);
    }

    public List<Room> getRoomList() {
        return roomList;
    }
}
