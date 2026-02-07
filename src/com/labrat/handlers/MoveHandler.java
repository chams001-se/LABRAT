package com.labrat.handlers;

import com.labrat.rooms.*;

public class MoveHandler implements TextHandler {
    String verb;
    String arg;
    Direction direction;
    Room targetRoom;

    public void performRequest(String[] input) {
        verb = input[0];

            // Check number of arguments
            switch(input.length) {
                case 1:
                    System.out.println("Move where?");
                    break;
                case 2:
                    arg = input[1];
                    // TODO: Check if Room has valid exit

                    break;
                default:
                    System.out.println("You can't move there.");
            }
    }

    @Override
    public boolean canHandleRequest(String[] input) {
        verb = input[0];

        // Check if user wants to move
        return verb.equals("move");
    }


}