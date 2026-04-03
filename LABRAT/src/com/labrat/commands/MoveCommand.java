package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.rooms.Direction;
import com.labrat.rooms.*;

public class MoveCommand implements Command {
    private Actor actor;
    private Direction direction;
    private Room current;

    private Room next;
    private CommandType commandType;

    public MoveCommand(Actor actor, Direction direction) {
        this.actor = actor;
        this.direction = direction;
        this.commandType = CommandType.MOVE;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void execute(){
        actor.setCurrentRoom(actor.getCurrentRoom().getExit(direction));
    }
   }