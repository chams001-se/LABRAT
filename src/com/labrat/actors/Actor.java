package com.labrat.actors;

import com.labrat.rooms.*;

public interface Actor {
    public void setCurrentRoom(Room selectedRoom);
    public Room getCurrentRoom();

    // Since the coordinate system to determine rooms has been removed all methods in the actor interface pertaining to coordinates have been removed
}
