package com.labrat;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.labrat.actors.*;
import com.labrat.commands.*;
import com.labrat.rooms.*;
import com.labrat.commandhandlers.*;
import com.labrat.rooms.RoomsCreator;
import com.labrat.view.ColoredText;
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

    public TextAdventure() {
        // Initialize scanner
        scanner = new Scanner(System.in);
        userInput = "";

        // Set user to the starting room.
        mainCharacter.setCurrentRoom(rc.getStartRoom());
    }


    public void start() {
        ColoredText previousResult = new ColoredText("", PrinterColor.DEFAULT);

        DetermineCommandHandler dch = new DetermineCommandHandler(mainCharacter);

        // Main Gameplay Loop
        while (true) {
            System.out.print(CLEAR);
            System.out.flush();

            ColoredText roomDesc = mainCharacter.getCurrentRoom().getColoredText();

            // Print out room description
            Printer.getInstance().print(roomDesc);

            /*
            Intuitively it would seem the best way to print results is through the current iteration, however, if we did this
            the next iteration would immediately clear the output. Thus, we output the previous commands result on the next iteration,
            so the user can read it DURING the time waiting for input.
             */
            if (previousResult != null && !previousResult.getText().isEmpty()){
                Printer.getInstance().print(previousResult);
                // Set result to empty so if the next command outputs nothing, the previous command cannot be printed
                previousResult = new ColoredText("");
            }

            System.out.print("> ");
            try {
                userInput = scanner.nextLine();
                userInput = userInput.toLowerCase().trim();

                // Tokenizes input
                String[] words = userInput.split("\\s+");

                // If the first word is quit, we simply exit the while loop.
                // Must handle an exit in its own function call because it requires a boolean return type, not ColoredText
                if (dch.isExit(words)) break;

                // Finds the command type, then execute it through handlers.
                // If the command type is invalid it will throw IllegalArgumentException, which will get caught, preventing a crash
                previousResult = dch.performRequest(words);

            } catch (IllegalArgumentException e) {
                previousResult = new ColoredText(e.getMessage(), PrinterColor.LIGHT_RED);
            }

        }
    }

    public static void main(String[] args) {
        TextAdventure labrat = new TextAdventure();
        labrat.start();
    }
}