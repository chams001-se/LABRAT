package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Exit;
import com.labrat.rooms.Room;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import static com.labrat.commands.CommandType.MOVE;
import static com.labrat.commands.CommandType.EXAMINE;
import static com.labrat.commands.CommandType.USE;
import static com.labrat.commands.CommandType.HELP;
import static com.labrat.commands.CommandType.HIDE;
import static com.labrat.commands.CommandType.INVENTORY;
import static com.labrat.commands.CommandType.QUIT;
import static com.labrat.commands.CommandType.READ;
import static com.labrat.commands.CommandType.TAKE;

// Concrete states override parent methods with error messages for unused commands

public class ExploreState extends BaseState {
    public ExploreState(Actor actor) {
        super(actor);
    }

    @Override
    public ActorStateType getActorStateType() {
        return ActorStateType.EXPLORE;
    }

    @Override
    protected CommandType[] getAvailableCommands() {
        return availableCommands;
    }
    private final CommandType[] availableCommands = {
        MOVE,
        EXAMINE,
        USE,
        READ,
        TAKE,
        //DROP,
        HIDE,
        //UNHIDE,
        INVENTORY,
        //CLOSEINVENTORY,
        HELP,
        QUIT
    };

    /* Allowed commands */
    @Override
    public void help() { super.help(); } // BaseState prints available commands

    @Override
    public void move(Direction direction) {
        if (actor.getCurrentRoom().isOpenExit(direction)) {
            actor.setCurrentRoom(actor.getCurrentRoom().getRoomInDir(direction));
            actor.setResultText(
                    new ResultText("You moved " + direction.toString().toLowerCase() + ".",
                    PrinterColor.YELLOW,
                    SoundEffect.HUMANFOOTSTEPS)
            );
        }
        else {
            actor.setResultText(
                    new ResultText("You can't move " + direction.toString().toLowerCase() + ".",
                    PrinterColor.RED,
                    SoundEffect.COMMANDERROR)
            );
        }
    }

    @Override
    public void examine(Item item) {
        actor.setResultText(item.getResultText(ItemType.EXAMINABLE));
    }

    @Override
    public void use(Item item) {
        Room currentRoom = actor.getCurrentRoom();
        String itemInternalName = item.getInternalName();

        // TODO Fix smelly code
        if (item.isItemType(ItemType.KEY)) {
            // Check each exit
            for (var entry : currentRoom.getExits().entrySet()) {
                Exit exit = entry.getValue();

                // Check if there are any locked exits with key requiring itemNAme
                if (exit.isLocked() && exit.getKeyRequired().equals(itemInternalName)) {
                    // Match found
                    exit.unlock();

                    // Check if item is consumable
                    if (item.isOneUse()) {
                        currentRoom.removeItem(itemInternalName);
                    }

                    // Set output
                    actor.setResultText(item.getResultText(ItemType.KEY));

                    // Break from for-loop
                    break;
                }
            }
        }
        else {
            actor.setResultText(item.getResultText(ItemType.USABLE));
        }
    }

    @Override
    public void read(Item item) {
        actor.setResultText(item.getResultText(ItemType.READABLE));
    }

    @Override
    public void take(Item item) {
        actor.getInventory().addItem(item);
        actor.getCurrentRoom().removeItem(item.getInternalName());
        actor.setResultText(item.getResultText(ItemType.TAKEABLE));
    }

    @Override
    public void inventory(String arg) {
        if (arg.equals("open")) {
            actor.changeState(new InventoryState(actor));
        }
        else if (arg.equals("close")) {
            actor.setResultText(new ResultText(
                    "Inventory is already closed.",
                    PrinterColor.RED
            ));
        }
    }

    @Override
    public void hide() {
        actor.setResultText(new ResultText("You are hiding!", PrinterColor.BLUE));
        actor.changeState(new HideState(actor));
    }

    /* Blocked commands */
    @Override
    public void drop(Item item) {
        actor.setResultText(new ResultText(
                "You cannot drop items while the inventory is closed.\nType \"inventory open\" to open inventory.",
                PrinterColor.RED
        ));
    }

    @Override
    public void unhide() {
        actor.setResultText(new ResultText(
                "You are not hiding.",
                PrinterColor.RED
        ));
    }
}
