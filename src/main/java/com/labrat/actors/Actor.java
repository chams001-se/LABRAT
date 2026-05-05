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
    void setMonsterProgressResult(ResultText result);
    ResultText getMonsterProgressResult();

    // Room Management
    void setCurrentRoom(Room selectedRoom);
    Room getCurrentRoom();
    void setSaveRoom(Room selectedRoom);
    Room getSaveRoom();

    // Inventory Management
    Inventory getInventory();

    // Hide Management
    boolean isHidden();
    void resetMonsterTimer();
    void decrementMonsterTimer();
    int getMonsterTimer();

    // Exit Flag
    void quit();
    boolean isQuitting();
}
