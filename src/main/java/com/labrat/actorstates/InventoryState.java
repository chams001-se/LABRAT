package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.items.ItemType;
import com.labrat.items.KeyItem;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Exit;
import com.labrat.rooms.Room;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import static com.labrat.commands.CommandType.*;
import static com.labrat.commands.CommandType.HELP;
import static com.labrat.commands.CommandType.HIDE;
import static com.labrat.commands.CommandType.INVENTORY;
import static com.labrat.commands.CommandType.QUIT;
import static com.labrat.commands.CommandType.READ;
import static com.labrat.commands.CommandType.TAKE;

public class InventoryState extends BaseState {
    public InventoryState(Actor actor) {
        super(actor);
    }

    @Override
    public ActorStateType getActorStateType() {
        return ActorStateType.INVENTORY;
    }

    @Override
    protected CommandType[] getAvailableCommands() {
        return availableCommands;
    }
    private final CommandType[] availableCommands = {
            //MOVE,
            EXAMINE,
            USE,
            READ,
            //TAKE,
            DROP,
            //HIDE,
            //UNHIDE,
            //INVENTORY,
            CLOSEINVENTORY,
            HELP,
            QUIT
    };
    /* Allowed Commands */
    @Override
    public void examine(Item item) {
        if (actor.getInventory().hasItem(item.getInternalName())){
            actor.setResultText(item.getResultText(ItemType.EXAMINABLE));
        } else {
            actor.setResultText(new ResultText("Exit inventory to examine specified item."));
        }
    }

    @Override
    public void use(Item item) {
        Room currentRoom = actor.getCurrentRoom();

        if (item instanceof KeyItem key) {
            boolean keyUsed = currentRoom.tryUnlockWith(key);
            if (item.isOneUse()) {
                actor.getInventory().removeItem(item.getInternalName());
            }
            if (!keyUsed){
                actor.setResultText(new ResultText("Key already used!"));
                return;
            }

        }
        actor.setResultText(item.getResultText(ItemType.USABLE));
    }

    @Override
    public void drop(Item item) {
        actor.getCurrentRoom().addItem(item);
        actor.getInventory().removeItem(item.getInternalName());
        actor.setResultText(item.getResultText(ItemType.DROPPABLE));
    }

    @Override
    public void read(Item item) {
        actor.setResultText(item.getResultText(ItemType.READABLE));
    }

    /* Blocked Commands */
    @Override
    public void inventory(String arg) {
        if (arg.equals("open")) {
            actor.setResultText(new ResultText(
                    "Inventory is already open.",
                    PrinterColor.RED
            ));
        }
        else if (arg.equals("close")) {
            actor.changeState(new ExploreState(actor));
        }
    }

    @Override
    public void take(Item item) {
        actor.setResultText(new ResultText(
                "You cannot take while in the inventory.\nUse \"closeinventory\" to close the inventory.",
                PrinterColor.RED
        ));
    }

    @Override
    public void move(Direction direction) {
        actor.setResultText(new ResultText(
                "You cannot move while in the inventory.\nType \"inventory close\" to close the inventory.",
                PrinterColor.RED
        ));
    }

    @Override
    public void hide() {
        actor.setResultText(new ResultText(
                "You cannot hide while in the inventory.\nType \"inventory close\" to close the inventory.",
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
