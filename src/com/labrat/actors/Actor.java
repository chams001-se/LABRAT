package com.labrat.actors;

import com.labrat.rooms.Direction;
import com.labrat.roombuilders.Room;
import java.awt.Point;

public interface Actor {

    public void moveDirection(Direction direction);

    public void setCoord(Point newCoord);
    public Point getCoord();

    public void setCurrentRoom(Room selectedRoom);
    public Room getCurrentRoom();
}
