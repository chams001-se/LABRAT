
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.labrat.actors.*;
import com.labrat.commands.*;
import com.labrat.rooms.*;
import com.labrat.commandhandlers.*;
import com.labrat.rooms.RoomsCreator;

public class TextAdventure {
    // Declare global variables
    Scanner scanner;
    String userInput;

    // Create receiver
    Actor mainCharacter = new MainCharacter();

    // Create RoomHandler
    RoomsCreator rc = new RoomsCreator();

    // Create commands
    Command moveCommand = new MoveCommand(mainCharacter, Direction.SOUTH);

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
        // Main Gameplay Loop
        while (true) {
            // TODO find a way to run our system through an actual terminal, not IntelliJ, to use escape sequences
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }


            // Print out room description
            System.out.println(mainCharacter.getCurrentRoom().getDescription());

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