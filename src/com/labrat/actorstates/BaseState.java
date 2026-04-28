package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.items.KeyItem;
import com.labrat.items.LockItem;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Room;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import java.util.Map;

// BaseState implements default behaviors for all commands
// that are not dependent on state transitions
// i.e. ExploreState -> HideState or HideState -> ExploreState

public abstract class BaseState implements ActorState {
    protected Actor actor;

    public BaseState(Actor actor) {
        this.actor = actor;
    }

    // Since BaseState serves as an abstract class, there are no available commands in this state
    // This method will be implemented inside of concrete states where their available commands will simply be returned
    protected abstract CommandType[] getAvailableCommands();

    // Helper method for finding items in either a room or in the player's inventory
    protected Item getItemInRoomOrInv(String itemName, ItemType itemType) {
        // True if there is an item in the current room
        Room currentRoom = actor.getCurrentRoom();
        Item item = null;

        // Check if item is in current room
        if (currentRoom.hasItem(itemName)) {
            item = currentRoom.getItem(itemName);
        }
        // Check if item is in inventory
        else if (actor.hasItem(itemName)) {
            item = actor.getInventoryItem(itemName);
        }

        return item;
    }

    @Override
    public void help() {
        StringBuilder cmdlist = new StringBuilder("VALID COMMANDS\n");
        for (CommandType c : getAvailableCommands()) {
            cmdlist.append(c.getCommandNotation()).append(": ").append(c.getCommandDescription()).append("\n");
        }

        actor.setResultText(new ResultText(cmdlist.toString(), PrinterColor.DIM, SoundEffect.BEEP));
    }

    @Override
    public void move(Direction direction) {
        actor.setCurrentRoom(actor.getCurrentRoom().getExit(direction));
        actor.setResultText(new ResultText("You moved " + direction.toString().toLowerCase() + ".", PrinterColor.YELLOW, SoundEffect.HUMANFOOTSTEPS));
    }

    @Override
    public void use(String itemName) {
        ItemType itemType = ItemType.USABLE;
        Item item = getItemInRoomOrInv(itemName, itemType);
        ResultText resultText = new ResultText(
                "Item is not in this room.",
                PrinterColor.RED,
                SoundEffect.COMMANDERROR
        );                                      // Default resultText
        Room currentRoom = actor.getCurrentRoom();

        // TODO Refactor so custom behavior is in Item instead of here
        if (item != null) {
            // Override use behavior by checking key
            if (item.isItemType(ItemType.KEY)) {
                for (var entry : currentRoom.getRoomItems().entrySet()) {
                    Item temp = entry.getValue();

                    // Check if item is a LockItem
                    if (temp instanceof LockItem) {
                        // Match found, downcast to subclasses
                        ((LockItem) temp).unlock((KeyItem) item);
                        if (((KeyItem) item).isOneUse()) {
                            actor.removeItem(itemName);
                            currentRoom.removeItem(itemName);
                        }

                        resultText = item.getResultText(ItemType.KEY);
                        break;
                    }
                }
            }
            else if (item.isItemType(itemType)) {
                resultText = item.getResultText(itemType);
            }
        }

        actor.setResultText(resultText);
    }

    @Override
    public void read(String itemName) {
        ItemType itemType = ItemType.READABLE;
        Item item = getItemInRoomOrInv(itemName, itemType);

        if (item != null && item.isItemType(itemType)) {
            actor.setResultText(item.getResultText(itemType));
        } else {
            actor.setResultText(new ResultText(
                            "Item is not in this room.",
                            PrinterColor.RED,
                            SoundEffect.COMMANDERROR
                    )
            );
        }
    }

    @Override
    public void examine(String itemName) {
        ItemType itemType = ItemType.EXAMINABLE;
        Item item = getItemInRoomOrInv(itemName, itemType);

        if (item != null && item.isItemType(itemType)) {
            actor.setResultText(item.getResultText(itemType));
        } else {
            actor.setResultText(new ResultText(
                            "Item is not in this room.",
                            PrinterColor.RED,
                            SoundEffect.COMMANDERROR
                    )
            );
        }
    }

    @Override
    public void take(String itemName) {
        // Take only supports taking items from the room to the inventory
        Room room = actor.getCurrentRoom();
        Item item = room.getItem(itemName);

        actor.addItem(item);
        room.removeItem(itemName);
        actor.setResultText(item.getResultText(ItemType.TAKABLE));
    }

    @Override
    public void drop(String itemName) {
        // Drop only supports dropping items from the inventory to the room
        Room room = actor.getCurrentRoom();
        Item item = actor.getInventoryItem(itemName);

        room.addItem(item);
        actor.removeItem(itemName);
        actor.setResultText(item.getResultText(ItemType.DROPPABLE));
    }

    @Override
    public void inventory() {
        StringBuilder inventory = new StringBuilder("INVENTORY\n");
        Map<String, Item> inv = actor.getInventory();

        int i = 0;
        for (String item : inv.keySet()) {
            i += 1 % 3;
            inventory.append(actor.getInventoryItem(item).getName()).append("\t");
            if (i == 3) inventory.append("\n");
        }

        actor.setResultText(new ResultText(inventory.toString(), PrinterColor.GREEN));
    }

    public abstract void hide();
    public abstract void unhide();
}
