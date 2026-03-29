package com.labrat.actors;
import com.labrat.rooms.*;

public class MainCharacter implements Actor {
    private Room currentRoom;

    public MainCharacter() {}

    public void setCurrentRoom(Room selectedRoom) {
        this.currentRoom = selectedRoom;
        //System.out.println(selectedRoom.getDescription());
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
