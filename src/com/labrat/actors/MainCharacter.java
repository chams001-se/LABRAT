package com.labrat.actors;

import com.labrat.actorstates.ActorState;
import com.labrat.actorstates.ExploreState;
import com.labrat.items.Item;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Room;
import com.labrat.view.PrinterColor;
import com.labrat.view.ResultText;

import java.util.HashMap;
import java.util.Map;

public class MainCharacter implements Actor {
    private Room currentRoom;
    private final Map<String, Item> inventory = new HashMap<>();
    private ActorState actorState;
    private ResultText previousResult;
    private boolean isQuitting;

    public MainCharacter() {
        this.actorState = new ExploreState(this);
        this.previousResult = new ResultText("", PrinterColor.DEFAULT);
        this.isQuitting = false;
    }

    // ActorState
    public void changeState(ActorState newState) { actorState = newState; }

    // Commands
    public void move(Direction dir) { actorState.move(dir); }
    public void use(String itemName) { actorState.use(itemName); }
    public void read(String itemName) { actorState.read(itemName); }
    public void take(String itemName) { actorState.take(itemName); }
    public void examine(String itemName) { actorState.examine(itemName); }
    public void inventory() { actorState.inventory(); }
    public void help() { actorState.help(); }
    public void quit() {
        isQuitting = true;
    }

    // ResultText
    public void setResultText(ResultText result) {
        this.previousResult = result;
    }
    public ResultText getResultText() {
        return previousResult;
    }

    // Room Management
    public void setCurrentRoom(Room selectedRoom) {
        this.currentRoom = selectedRoom;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }

    // Inventory Management
    public void addItem(Item item) {
        inventory.put(item.getName().toLowerCase(), item);
    }
    public Item getInventoryItem(String name) {
        return inventory.get(name.toLowerCase());
    }
    public Map<String, Item> getInventory() {
        return inventory;
    }
    public boolean hasItem(String name) {
        return inventory.containsKey(name.toLowerCase());
    }

    // Hiding Logic
    public boolean isSneaking() {
        // TODO implement HideState
        //return actorState instanceof HideState;
        return false;
    }

    // Quit Flag
    public boolean isQuitting() {
        return isQuitting;
    }
}