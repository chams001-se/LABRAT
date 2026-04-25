package com.labrat.actors;

import com.labrat.actorstates.ActorState;
import com.labrat.items.Item;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Room;
import com.labrat.view.ResultText;

import java.util.Map;

public interface Actor {
    // Actor State
    void changeState(ActorState newState);
    ActorState getActorState();

    // Commands
    void move(Direction dir);
    void use(String itemName);
    void read(String itemName);
    void take(String itemName);
    void examine(String itemName);
    void inventory();
    void help();
    void quit();
    void hide();

    // ResultText Management
    void setResultText(ResultText result);
    ResultText getResultText();

    // Room Management
    void setCurrentRoom(Room selectedRoom);
    Room getCurrentRoom();

    // Inventory Management
    void addItem(Item item);
    Item getInventoryItem(String name);
    Map<String, Item> getInventory();
    boolean hasItem(String name);

    // Hide Management
    void toggleHiding();
    boolean isHidden();

    // Exit Flag
    boolean isQuitting();
}
