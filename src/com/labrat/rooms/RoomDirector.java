package com.labrat.rooms;

import com.labrat.roombuilders.Room;
import com.labrat.roombuilders.StartRoom;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import static com.labrat.commands.CommandType.*;
import static com.labrat.rooms.Direction.*;

public class RoomDirector {
    Room firstRoom = new StartRoom();
    Room secondRoom = new StartRoom();
    Map<Point, Room> roomMap = new HashMap<>();

    public RoomDirector() {
        firstRoom.withRoomExit(SOUTH);
        firstRoom.withCommand(MOVE);
        roomMap.put(new Point(0,0), firstRoom);

        secondRoom.withRoomExit(NORTH);
        secondRoom.withCommand(MOVE);
        secondRoom.setDescription("This is the second room");
        roomMap.put(new Point(0,-1), secondRoom);
    }

    public Map<Point, Room> getRoomMap() {
        return roomMap;
    }
}
