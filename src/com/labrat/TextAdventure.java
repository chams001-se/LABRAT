package com.labrat;

import java.util.Scanner;

import com.labrat.actors.*;
import com.labrat.audio.AudioManager;
import com.labrat.commands.*;
import com.labrat.rooms.*;
import com.labrat.commandhandlers.*;
import com.labrat.view.*;

public class TextAdventure {
    // Declare global variables
    // Declare user input variables
    Scanner scanner;
    String userInput;
    Command userCommand;
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
        previousResult = new ResultText("", PrinterColor.DEFAULT);

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
        while (true) {
            // Clear console by printing empty lines
            ResultFormatter.getInstance().printLines();

            // Print out current room description
            ResultFormatter.getInstance().print(mainCharacter.getCurrentRoom().getColoredText());

            /*
            // Intuitively it would seem the best way to print results is through the current iteration, however, if we did this
            // the next iteration would immediately clear the output. Thus, we output the previous commands result on the next iteration,
            // so the user can read it DURING the time waiting for input.
             */
            if (previousResult != null && !previousResult.text().isEmpty()) {
                ResultFormatter.getInstance().print(previousResult);
            }

            System.out.print("> ");
            try {
                // Play previous result's audio
                AudioManager.getInstance().play(previousResult.soundEffect());

                // Set result to empty so if the next command outputs nothing, the previous command cannot be printed
                previousResult = new ResultText("");

                // Tokenize and parse user input
                userInput = scanner.nextLine();
                userCommand = parser.parse(lexer.lex(userInput));

                // If the first word is quit, we simply exit the while loop.
                // Must handle an exit in its own function call because it requires a boolean return type, not ResultText
                if (dch.isQuit(userCommand)) { break; }

                // Finds the command type, then execute it through handlers.
                // If the command type is invalid it will throw IllegalArgumentException, which will get caught, preventing a crash
                previousResult = dch.performRequest(userCommand);
            }
            catch (Exception e) {
                /* Exceptions caught:
                // IllegalArgumentException, RuntimeException,
                // UnsupportedAudioFileException, IOException, LineUnavailableException
                 */
                previousResult = new ResultText(e.getMessage(), PrinterColor.LIGHT_RED);
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