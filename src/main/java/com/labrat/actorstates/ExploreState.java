package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.items.KeyItem;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Room;
import com.labrat.rooms.RoomTheme;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultFormatter;
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
        // Get shared variables
        Room currentRoom = actor.getCurrentRoom();
        SoundEffect sfx = SoundEffect.MUTE;

        // Auto save when leaving room
        if (currentRoom.isSaveRoom()) {
            System.out.println("Set current room as save spot.");
            actor.setSaveRoom(currentRoom);
        }

        // Get sound effect
        switch (currentRoom.getTheme()) {
            case LAB ->             sfx = SoundEffect.HUMANFOOTSTEPSLAB;
            case GREENHOUSE ->      sfx = SoundEffect.HUMANFOOTSTEPSFOREST;
            case SEWER ->           sfx = SoundEffect.HUMANFOOTSTEPSGRAVEL;
        }

        // Check if direction has an open exit
        if (currentRoom.isOpenExit(direction)) {
            actor.setCurrentRoom(currentRoom.getRoomInDir(direction));
            actor.setResultText(
                    new ResultText("You moved " + direction.toString().toLowerCase() + ".",
                    PrinterColor.YELLOW,
                    sfx)
            );

            // Check if actor is in greenhouse
            if (actor.getCurrentRoom().getTheme() == RoomTheme.GREENHOUSE) {
                actor.decrementMonsterTimer();
                int monsterTimer = actor.getMonsterTimer();

                if (monsterTimer == 1) {
                    actor.setMonsterProgressResult(new ResultText(
                            "You can hear the heavy breathing of something nearby!",
                            PrinterColor.RED,
                            SoundEffect.MONSTERBREATH)
                    );
                }
                else if (monsterTimer > 1 && monsterTimer <= 3) {
                    actor.setMonsterProgressResult(new ResultText(
                            "The footsteps grow louder...",
                            PrinterColor.RED,
                            SoundEffect.MONSTERSTEP)
                    );
                }
                else if (monsterTimer > 3 && monsterTimer <= 5) {
                    actor.setMonsterProgressResult(new ResultText(
                            "You hear distant footfalls of a large beast.",
                            PrinterColor.RED,
                            SoundEffect.MONSTERSTEP)
                    );
                }

                // Check if lowered monster timer to 0
                if (actor.getMonsterTimer() <= 0) {
                    // GAME OVER
                    actor.setResultText(new ResultText(
                            "The monster is upon you! You try your best to defend yourself against the mass of flesh and bone, but it overwhelms you!\nThe monster uses its boney appendage to impale you in the chest!\nYour consciousness fades to black.\nHowever, instead of passing on, you wake up in a familiar place...",
                            PrinterColor.RED,
                            SoundEffect.GAMEOVER
                    ));
                    actor.setCurrentRoom(actor.getSaveRoom());
                    actor.resetMonsterTimer();
                }
            }
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
        if (actor.getCurrentRoom().hasItem(item.getInternalName())){
            actor.setResultText(item.getResultText(ItemType.EXAMINABLE));
        } else {
            actor.setResultText(new ResultText("Enter inventory to examine specified item."));
        }
    }

    @Override
    public void use(Item item) {
        Room currentRoom = actor.getCurrentRoom();

        if (item instanceof KeyItem key) {
            boolean keyUsed = currentRoom.tryUnlockWith(key);
            if (keyUsed) {
                if (item.isOneUse()) {
                    actor.getInventory().removeItem(item.getInternalName());
                }
            } else { // key not used
                actor.setResultText(new ResultText(
                        "Can't use that here.",
                        PrinterColor.RED,
                        SoundEffect.COMMANDERROR)
                );
                return;
            }

        }
        actor.setResultText(item.getResultText(ItemType.USABLE));
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
    public void hide(Item item) {
        actor.setResultText(item.getResultText(ItemType.HIDEABLE));

        // Reset monster timer if within warning range
        if (actor.getMonsterTimer() <= 5) {
            actor.resetMonsterTimer();
        }

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
