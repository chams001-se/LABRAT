package com.labrat.handlers;

import com.labrat.actors.MainCharacter;
import com.labrat.rooms.*;

import java.util.List;

public class RoomHandler {
    Room selectedRoom;
    RoomBuilder roomBuilder;
    List<Room> roomList;

    public RoomHandler() {
        this.roomBuilder = new RoomBuilder();
        this.roomList = this.roomBuilder.getRoomList();
    }

    public void setStartingRoom(MainCharacter protagonist) {
        protagonist.setCurrentRoom(roomList.get(0));
    }

    public void setCharacterRoom(MainCharacter protagonist, Room selectedRoom) {
        protagonist.setCurrentRoom(selectedRoom);
    }

    public String getCharacterRoomDesc(MainCharacter protagonist) {
        this.selectedRoom = protagonist.getCurrentRoom();
        return this.selectedRoom.getDescription();
    }
}
