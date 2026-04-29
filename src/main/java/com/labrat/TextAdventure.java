package com.labrat;

import java.util.Scanner;

import com.labrat.actors.Actor;
import com.labrat.actors.MainCharacter;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.AudioManager;
import com.labrat.commandreaders.CommandAlias;
import com.labrat.commandreaders.CommandLexer;
import com.labrat.commandreaders.CommandParser;
import com.labrat.commands.*;
import com.labrat.commandhandlers.*;
import com.labrat.items.ItemType;
import com.labrat.items.ItemsCreator;
import com.labrat.rooms.*;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultFormatter;
import com.labrat.view.ResultText;
import com.labrat.view.TextAdventureView;

public class TextAdventure {
    // Declare global variables
    // Declare user input variables
    Scanner scanner;
    String userInput;
    Command userCommand;

    // Declare character variables
    Actor mainCharacter;

    // Declare command variables
    CommandAlias aliaser;
    CommandLexer lexer;
    CommandParser parser;
    DetermineCommandHandler dch;

    // Declare room variables
    //RoomsCreator rc;

    // Declare view
    TextAdventureView textAdventureView;

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

        // Set user to the starting room
        mainCharacter.setCurrentRoom(RoomsCreator.getInstance().getStartRoom());

        // Initialize view
        textAdventureView = new TextAdventureView(mainCharacter.getCurrentRoom());
    }

    private void start() {
        /* Main Gameplay Loop */
        while (!mainCharacter.isQuitting()) {
            try {
                // Output the current view to the player
                TextAdventureView.display(mainCharacter);

                // Set result to empty so if the next command outputs nothing, the previous command cannot be printed
                mainCharacter.setResultText(new ResultText(""));

                // Update aliasing from main character's current position
                aliaser.updateAlias(mainCharacter);

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
                e.printStackTrace();
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