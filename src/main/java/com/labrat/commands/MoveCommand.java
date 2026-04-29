package com.labrat.commands;

import com.labrat.actors.Actor;
import com.labrat.rooms.Direction;

public class MoveCommand extends BaseCommand {
    public MoveCommand(Actor actor, String[] args) {
        super(actor, args, CommandType.MOVE);
    }

    @Override
    public void execute() {
        super.actor.getActorState().move(Direction.fromString(args[0]));
    }
   }