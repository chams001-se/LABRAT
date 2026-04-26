package com.labrat.actorstates;

import com.labrat.actors.Actor;
import com.labrat.audio.SoundEffect;
import com.labrat.commands.CommandType;
import com.labrat.items.Item;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Room;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import java.util.Map;

// BaseState implements commands that are not dependent on state transitions
// i.e. ExploreState -> HideState or HideState -> ExploreState

public abstract class BaseState implements ActorState {
    protected Actor actor;

    public BaseState(Actor actor) {
        this.actor = actor;
    }

    // Since BaseState serves as an abstract class, there are no available commands in this state
    // This method will be implemented inside of concrete states where their available commands will simply be returned
    @Override
    public abstract CommandType[] getAvailableCommands();

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
    }

    @Override
    public void use(String itemName) {
        Item item = actor.getInventoryItem(itemName);

        if (item != null) {
            actor.setResultText(item.getResultText());
        } else {
            actor.setResultText(new ResultText(itemName + " not in inventory", PrinterColor.RED));
        }
    }

    @Override
    public void read(String itemName) {
        Item item = actor.getCurrentRoom().getItem(itemName);
        actor.setResultText(item.getResultText());
    }

    @Override
    public void take(String itemName) {
        Room room = actor.getCurrentRoom();
        Item item = room.getItem(itemName);

        actor.addItem(item);
        room.removeItem(itemName);
    }

    @Override
    public void examine(String itemName) {
        //TODO Change to ExaminableItem
        Item item = actor.getCurrentRoom().getItem(itemName);
        actor.setResultText(item.getResultText());
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
