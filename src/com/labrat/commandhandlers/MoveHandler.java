package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.MoveCommand;
import com.labrat.roombuilders.Room;
import com.labrat.rooms.*;

public class MoveHandler implements CommandHandler {
    Actor actor;
    MoveCommand moveCommand;

    public MoveHandler(Actor actor, MoveCommand moveCommand) {
        this.actor = actor;
        this.moveCommand = moveCommand;
    }

    public String performRequest(String[] input) {
        //String verb = input[0];
        // verb == "move"
        String arg;
        Room currentRoom;
        Direction direction;

        // Check number of arguments
        switch(input.length) {
            case 1:
                return "Move where?";
            case 2:
                arg = input[1];
                currentRoom = actor.getCurrentRoom();

                if (Direction.isValidDirection(arg)) {
                    direction = Direction.valueOf(arg.toUpperCase());
                    if (currentRoom.isValidExit(direction)) {
                        // Set moveDirection to chosen direction
                        moveCommand.setDirection(direction);

                        // Execute command
                        moveCommand.execute();

                        // Print direction
                        return "You move " + direction.toString() + ".";
                    }
                }
            default:
                return "You can't move there.";
        }
    }

    public boolean canHandleRequest(String[] input) {
        String verb = input[0];
        Room currentRoom;

        // TODO: Check if current room permits action
        currentRoom = actor.getCurrentRoom();
        //return verb.equals("move");
        return currentRoom.isValidCmd(verb);
    }


}