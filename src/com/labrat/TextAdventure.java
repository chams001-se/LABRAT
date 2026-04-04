package com.labrat;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.labrat.actors.*;
import com.labrat.commands.*;
import com.labrat.rooms.*;
import com.labrat.commandhandlers.*;
import com.labrat.rooms.RoomsCreator;
import com.labrat.view.Printer;
import com.labrat.view.PrinterColor;

public class TextAdventure {
    // Declare global variables

    final String CLEAR = "\033[H\033[2J"; // Escape sequence to clear terminal
    Scanner scanner;
    String userInput;

    // Create receiver
    Actor mainCharacter = new MainCharacter();

    // Create RoomHandler
    RoomsCreator rc = new RoomsCreator();

    // Create HashMap of CommandTypes mapped to their appropriate Command Handlers
    Map<CommandType, CommandHandler> commandHandlers = new HashMap<>();

    public TextAdventure() {

        // Initialize scanner
        scanner = new Scanner(System.in);
        userInput = "";

        // Set user to the starting room.
        mainCharacter.setCurrentRoom(rc.getStartRoom());

        // Initialize text handlers by mapping the handler to the appropriate command type.
        commandHandlers.put(CommandType.MOVE, new MoveHandler(mainCharacter));
    }


    // CHANGE: Removed internal while loop and result variable
    // TODO: Delegated Printer class, perhaps serving as the View in our MVC
    public void start() {
        Printer p = new Printer();
        // Main Gameplay Loop
        while (true) {

            System.out.print(CLEAR);
            System.out.flush();

            // Print out room description
            PrinterColor roomColor = mainCharacter.getCurrentRoom().getDescriptionColor();
            String roomDescription = mainCharacter.getCurrentRoom().getDescription();
            p.printToString(roomDescription, roomColor);

            System.out.print("> ");
            try {
                userInput = scanner.nextLine();
                userInput = userInput.toLowerCase().trim();

                // Tokenizes input
                String[] words = userInput.split("\\s+");

                // Maybe a better way to go about this?
                if (words[0].equals("quit")) break;

                // Find command type, then execute it through a handler.
                // If the command type is invalid it will throw IllegalArguementException, which will get caught, preventing a crash
                CommandType ct = CommandType.fromString(words[0].toUpperCase());

                System.out.println(commandHandlers.get(ct).performRequest(words));
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        TextAdventure labrat = new TextAdventure();

        labrat.start();
    }
}