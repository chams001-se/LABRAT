package com.labrat.actors;

import com.labrat.rooms.Direction;
import com.labrat.roombuilders.Room;
import java.awt.Point;

public class MainCharacter implements Actor {
    private Room currentRoom;
    private Point coord = new Point(0, 0);

    public MainCharacter() {
    }

    public void moveDirection(Direction direction) {
        // Update player position on Map/Array
        switch(direction) {
            case NORTH -> coord.y++;
            case SOUTH -> coord.y--;
            case WEST -> coord.x++;
            case EAST -> coord.x--;
            case NORTHWEST -> {coord.x++; coord.y++;}
            case NORTHEAST -> {coord.x--; coord.y++;}
            case SOUTHWEST -> {coord.x++; coord.y--;}
            case SOUTHEAST -> {coord.x--; coord.y--;}
        }
    }

    public void setCoord(Point newCoord) {
        this.coord.x = newCoord.x;
        this.coord.y = newCoord.y;
    }

    public Point getCoord() {
        return coord;
    }

    public void setCurrentRoom(Room selectedRoom) {
        this.currentRoom = selectedRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }
}
