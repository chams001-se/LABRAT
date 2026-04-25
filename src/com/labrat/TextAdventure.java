package com.labrat;

import java.util.Scanner;

import com.labrat.actors.*;
import com.labrat.audio.AudioManager;
import com.labrat.commands.*;
import com.labrat.rooms.*;
import com.labrat.commandhandlers.*;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultFormatter;
import com.labrat.view.ResultText;

public class TextAdventure {
    // Declare global variables
    // Declare user input variables
    Scanner scanner;
    String userInput;
    Command userCommand;
    Room currentRoom;
    ResultText previousResult;

    // Declare character variables
    Actor mainCharacter;

    // Declare command variables
    CommandLexer lexer;
    CommandParser parser;
    DetermineCommandHandler dch;

    // Declare room variables
    RoomsCreator rc;

    public TextAdventure() {
        // Initialize main character
        mainCharacter = new MainCharacter();

        // Initialize scanner
        scanner = new Scanner(System.in);
        userInput = "";

        // Initialize commands
        lexer = new CommandLexer();
        parser = new CommandParser(mainCharacter);
        dch = new DetermineCommandHandler();

        // Initialize sounds
        AudioManager.getInstance().init();

        // Initialize rooms
        rc = new RoomsCreator();

        // Set user to the starting room
        mainCharacter.setCurrentRoom(rc.getStartRoom());
    }


    public void start() {
        /* Main Gameplay Loop */
        while (!mainCharacter.isQuitting()) {
            // Clear console by printing empty lines
            ResultFormatter.getInstance().printLines();

            // Print current room description
            currentRoom = mainCharacter.getCurrentRoom();
            ResultFormatter.getInstance().print(currentRoom.getRoomText());

            // Print current room item descriptions
            for (var entry : currentRoom.getRoomItems().entrySet()) {
                ResultFormatter.getInstance().print(entry.getValue().getRoomText());
            }

            /*
            // Intuitively it would seem the best way to print results is through the current iteration, however, if we did this
            // the next iteration would immediately clear the output. Thus, we output the previous commands result on the next iteration,
            // so the user can read it DURING the time waiting for input.
             */
            previousResult = mainCharacter.getResultText();
            if (previousResult != null && !previousResult.text().isEmpty()) {
                ResultFormatter.getInstance().print(previousResult);
            }

            System.out.print("> ");
            try {
                // Play current room audio
                AudioManager.getInstance().play(currentRoom.getRoomText().soundEffect());

                // Play previous result's audio
                AudioManager.getInstance().play(previousResult.soundEffect());

                // Set result to empty so if the next command outputs nothing, the previous command cannot be printed
                mainCharacter.setResultText(new ResultText(""));

                // Tokenize and parse user input
                userInput = scanner.nextLine();
                userCommand = parser.parse(lexer.lex(userInput));

                // Finds the command type, then execute it through handlers.
                // If the command type is invalid it will throw IllegalArgumentException, which will get caught, preventing a crash
                dch.performRequest(userCommand);
            }
            catch (Exception e) {
                /* Exceptions caught:
                // IllegalArgumentEx   ception, RuntimeException,
                // UnsupportedAudioFileException, IOException, LineUnavailableException
                 */
                mainCharacter.setResultText(new ResultText(e.getMessage(), PrinterColor.LIGHT_RED));
            }
        }
    }

    public void end() {
        /* Cleanup */
        // Close all audio clips
        AudioManager.getInstance().close();
    }

    public static void main(String[] args) {
        TextAdventure labrat = new TextAdventure();
        labrat.start();
        labrat.end();
    }
}