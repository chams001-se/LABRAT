import java.awt.Point;
import java.util.List;
import java.util.Scanner;

import com.labrat.actors.*;
import com.labrat.commands.*;
import com.labrat.rooms.RoomHandler;
import com.labrat.commandhandlers.*;

public class TextAdventure {
    // Declare global variables
    Scanner scanner;
    String userInput;
    String result;

    // Create receiver
    Actor mainCharacter = new MainCharacter();

    // Create RoomHandler
    RoomHandler roomHandler = new RoomHandler();

    // Create commands
    Command moveCommand = new MoveCommand(mainCharacter, roomHandler);

    // Create Arraylist of Command Handlers
    List<CommandHandler> commandHandlers;

    public TextAdventure() {
        // Initialize variables
        result = "";

        // Initialize scanner
        scanner = new Scanner(System.in);
        userInput = "";

        // Set user to a room
        // DEBUG: Change Point to desired x and y coords
        Point startPoint = new Point(0, 0);
        mainCharacter.setCoord(startPoint);
        mainCharacter.setCurrentRoom(roomHandler.roomLookup(startPoint));

        // Initialize text handlers
        commandHandlers = List.of(
                new MoveHandler(mainCharacter, (MoveCommand) moveCommand)
        );
    }

    public void start() {
        // Main Gameplay Loop
        while (!userInput.equals("quit")) {
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
            // Get room description
            System.out.println(mainCharacter.getCurrentRoom().getDescription());
            // Print previous result
            System.out.println(result);

            // Clear input and get user input until valid command
            result = "";
            while (result.isEmpty()) {
                // Get user input
                System.out.print("> ");
                try {
                    userInput = scanner.nextLine();
                    userInput = userInput.toLowerCase().trim();
                    String[] words = userInput.split("\\s+");

                    // Iterate through command handlers
                    // Perform request and break if valid request found
                    for (CommandHandler handler : commandHandlers) {
                        if (handler.canHandleRequest(words)) {
                            result = handler.performRequest(words);
                            break;
                        }
                    }
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void main(String args[]) {
        TextAdventure labrat = new TextAdventure();
        labrat.start();
    }
}