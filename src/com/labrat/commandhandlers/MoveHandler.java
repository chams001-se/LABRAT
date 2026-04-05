package com.labrat.commandhandlers;

import com.labrat.actors.Actor;
import com.labrat.commands.MoveCommand;
import com.labrat.rooms.*;
import com.labrat.view.ColoredText;
import com.labrat.view.PrinterColor;

// Purpose is to parse input for the parameters of the move command

public class MoveHandler implements CommandHandler {
    Actor actor;

    public MoveHandler(Actor actor) {
        this.actor = actor;
    }

    public ColoredText performRequest(String[] input) {
        String arg;
        Room currentRoom;
        Direction direction;

        // Check number of arguments
        if (input.length == 1){
            return new ColoredText("Move where?", PrinterColor.YELLOW);
        } else if (input.length == 2) {
            arg = input[1];
            currentRoom = actor.getCurrentRoom();

            // Extracts the direction so the move command will enter the appropriate room.
            if (Direction.isValidDirection(arg)) {
                direction = Direction.fromString(arg);
                if (currentRoom.isValidExit(direction)) {
                    // Creates a move command for the chosen direction and executes it\
                    new MoveCommand(actor, direction).execute();

                    // Print direction
                    return new ColoredText("You moved " + direction.toString() + ".", PrinterColor.YELLOW);
                }
            }
        }
        return new ColoredText("You can't move there.", PrinterColor.YELLOW);
    }
}