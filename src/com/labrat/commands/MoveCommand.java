package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.rooms.Direction;
import com.labrat.view.ResultText;

import java.util.List;

public class MoveCommand implements Command {
    private final Actor actor;
    private final String[] args;
    private final CommandType commandType;
    private Direction direction;

    public MoveCommand(Actor actor, String[] args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.MOVE;
    }

    @Override
    public String[] getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        direction = Direction.fromString(args[0]);
        return actor.getCurrentRoom().isOpenExit(direction);
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    @Override
    public ResultText getResult() {
        return null;
    }

    // In this instance, the actor itself is the receiver.
    @Override
    public void execute() {
        actor.setCurrentRoom(actor.getCurrentRoom().getExit(direction));
    }
   }