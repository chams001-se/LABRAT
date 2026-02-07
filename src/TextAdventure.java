import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.labrat.actors.*;
import com.labrat.commands.*;
import com.labrat.handlers.*;

public class TextAdventure {
    // Declare global variables
    Scanner scanner;
    String userInput;
    String roomDesc;

    // Create RoomHandler
    RoomHandler roomHandler = new RoomHandler();

    // Create receiver
    MainCharacter myCharacter = new MainCharacter();

    // Create commands
    Command moveCommand = new MoveCommand(myCharacter);

    // Create invoker
    CommandHandler commandHandler = new CommandHandler();;

    // Create Command Handlers
    List<TextHandler> textHandlers = new ArrayList<>();
    //MoveHandler moveHandler = new MoveHandler();

    public TextAdventure() {
        // Initialize scanner
        scanner = new Scanner(System.in);
        userInput = "";

        // Initialize text handlers
        textHandlers.add(new MoveHandler());

        // Set character to start
        roomHandler.setStartingRoom(myCharacter);
    }

    public void start() {
        // Main Gameplay Loop
        while (!userInput.equals("quit")) {
            // Get room description
            roomDesc = roomHandler.getCharacterRoomDesc(myCharacter);
            System.out.println(roomDesc);

            // Get user input
            System.out.print("> ");
            userInput = scanner.nextLine();
            userInput = userInput.toLowerCase().trim();
            String[] words = userInput.split(" ");

            // TODO: Implement commandHandler inside MoveHandler
            commandHandler.setCommand(moveCommand);

            // Iterate through text handlers
            // Perform request and break if valid request found
            for (TextHandler handler : textHandlers) {
                if (handler.canHandleRequest(words)) {
                    handler.performRequest(words);
                    break;
                }
            }

            // Handle Command
            commandHandler.processCommand();
        }
    }

    public static void main(String args[]) {
        TextAdventure labrat = new TextAdventure();
        labrat.start();
    }
}