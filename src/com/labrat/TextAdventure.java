package com.labrat;

import java.util.Scanner;

import com.labrat.actors.Actor;
import com.labrat.actors.MainCharacter;
import com.labrat.audio.AudioManager;
import com.labrat.commandreaders.CommandAlias;
import com.labrat.commandreaders.CommandLexer;
import com.labrat.commandreaders.CommandParser;
import com.labrat.commands.*;
import com.labrat.commandhandlers.*;
import com.labrat.items.ItemType;
import com.labrat.rooms.*;
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
    CommandAlias aliaser;
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
        aliaser = new CommandAlias();
        lexer = new CommandLexer(aliaser);
        parser = new CommandParser(mainCharacter);
        dch = new DetermineCommandHandler();

        // Initialize sounds
        AudioManager.getInstance().init();

        // Initialize rooms
        rc = new RoomsCreator();

        // Set user to the starting room
        mainCharacter.setCurrentRoom(rc.getStartRoom());
    }

    private void TextAdventureView() {
        // Clear console by printing empty lines
        ResultFormatter.getInstance().printLines();

        // Print current room description
        currentRoom = mainCharacter.getCurrentRoom();
        ResultFormatter.getInstance().print(currentRoom.getRoomText());

        // Flush aliasing from previous result
        aliaser.flushItemAlias();

        // Print current room item descriptions
        // and update aliasing for items in current room
        for (var entry : currentRoom.getRoomItems().entrySet()) {
            ResultFormatter.getInstance().print(entry.getValue().getResultText(ItemType.VISIBLE));
            aliaser.addRoomItemAlias(entry.getValue());
        }

        // Update aliasing for inventory items
        for (var entry : mainCharacter.getInventory().entrySet()) {
            aliaser.addInventoryItemAlias(entry.getValue());
        }

        // Play current room audio
        AudioManager.getInstance().play(currentRoom.getRoomText().soundEffect());

        /*
        // Intuitively it would seem the best way to print results is through the current iteration, however, if we did this
        // the next iteration would immediately clear the output. Thus, we output the previous commands result on the next iteration,
        // so the user can read it DURING the time waiting for input.
         */
        previousResult = mainCharacter.getResultText();
        if (previousResult != null && !previousResult.text().isEmpty()) {
            ResultFormatter.getInstance().print(previousResult);
        }

        // Play previous result's audio
        AudioManager.getInstance().play(previousResult.soundEffect());

        // Arrow for user input
        System.out.print("> ");
    }

    private void start() {
        /* Main Gameplay Loop */
        while (!mainCharacter.isQuitting()) {
            try {
                // Output the current view to the player
                TextAdventureView();

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
                // IllegalArgumentException, RuntimeException,
                // UnsupportedAudioFileException, IOException, LineUnavailableException
                 */
                mainCharacter.setResultText(new ResultText(e.getMessage(), PrinterColor.LIGHT_RED));
            }
        }
    }

    private void end() {
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