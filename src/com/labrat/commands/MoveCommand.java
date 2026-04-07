package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.rooms.Direction;

import java.util.List;

public class MoveCommand implements Command {
    private final Actor actor;
    private final List<String> args;
    private Direction direction;
    private final CommandType commandType;

    public MoveCommand(Actor actor, List<String> args) {
        this.actor = actor;
        this.args = args;
        this.commandType = CommandType.MOVE;
    }

    @Override
    public List<String> getArgs() {
        return args;
    }

    @Override
    public boolean hasValidArgs() {
        return actor.getCurrentRoom().isValidExit(Direction.fromString(args.getFirst()));
    }

    @Override
    public CommandType getCommandType() {
        return commandType;
    }

    // In this instance, the actor itself is the receiver.
    @Override
    public void execute() {
        direction = Direction.fromString(args.getFirst());
        actor.setCurrentRoom(actor.getCurrentRoom().getExit(direction));
    }
   }