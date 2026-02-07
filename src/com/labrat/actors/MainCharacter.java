package com.labrat.actors;

import com.labrat.rooms.Room;

public class MainCharacter {
    private Room currentRoom;

    public void moveDirection() {
        // TODO: Use Direction ENUM
        System.out.println("You move DIRECTION.");

        // TODO: Update player position on Map/Array
    }

    public void setCurrentRoom(Room selectedRoom) {
        this.currentRoom = selectedRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
