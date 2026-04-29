package com.labrat.view;

import com.labrat.actors.Actor;
import com.labrat.actorstates.ActorStateType;
import com.labrat.audio.AudioManager;
import com.labrat.items.ItemType;
import com.labrat.rooms.Room;

public class TextAdventureView {
    private static Room currentRoom;
    private static ResultText previousResult;

    public TextAdventureView(Room room) {
        currentRoom = room;
    }

    public static void display(Actor mainCharacter) {
        // Clear console by printing empty lines
        ResultFormatter.getInstance().printLines();

        // Print current room description
        currentRoom = mainCharacter.getCurrentRoom();
        ResultFormatter.getInstance().print(currentRoom.getRoomText());

        // Print current room item descriptions
        // and update aliasing for items in current room
        for (var entry : currentRoom.getRoomItems().entrySet()) {
            ResultFormatter.getInstance().print(entry.getValue().getResultText(ItemType.VISIBLE));
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

        // Print inventory if the inventory is open
        if (mainCharacter.getActorStateType() == ActorStateType.INVENTORY) {
            ResultFormatter.getInstance().print(new ResultText(mainCharacter.getInventory().toString(), PrinterColor.GREEN));
        }

        // Arrow for user input
        System.out.print("> ");
    }
}
