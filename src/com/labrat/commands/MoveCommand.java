package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.rooms.Direction;
import com.labrat.rooms.RoomHandler;
import com.labrat.commands.CommandType;

public class MoveCommand implements Command {
    private Actor actor;
    private Direction direction;
    private RoomHandler roomHandler;
    private CommandType commandType;

    public MoveCommand(Actor actor, RoomHandler roomHandler) {
        this.actor = actor;
        this.roomHandler = roomHandler;
        this.commandType = CommandType.MOVE;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void execute() {
        // Perform move operation
        actor.moveDirection(direction);

        // Update player room
        actor.setCurrentRoom(roomHandler.roomLookup(actor.getCoord()));
    }
}