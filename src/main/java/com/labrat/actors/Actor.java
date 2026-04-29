package com.labrat.actors;

import com.labrat.actorinventory.Inventory;
import com.labrat.actorstates.ActorState;
import com.labrat.actorstates.ActorStateType;
import com.labrat.items.Item;
import com.labrat.rooms.Direction;
import com.labrat.rooms.Room;
import com.labrat.view.ResultText;

import java.util.Map;

public interface Actor {
    // Actor State
    void changeState(ActorState newState);
    ActorState getActorState();
    ActorStateType getActorStateType();

    // ResultText Management
    void setResultText(ResultText result);
    ResultText getResultText();

    // Room Management
    void setCurrentRoom(Room selectedRoom);
    Room getCurrentRoom();

    // Inventory Management
    Inventory getInventory();

    // Hide Management
    boolean isHidden();

    // Exit Flag
    void quit();
    boolean isQuitting();
}
