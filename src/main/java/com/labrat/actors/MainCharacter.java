package com.labrat.actors;

import com.labrat.actorinventory.Inventory;
import com.labrat.actorstates.ActorState;
import com.labrat.actorstates.ActorStateType;
import com.labrat.actorstates.ExploreState;
import com.labrat.rooms.Room;
import com.labrat.view.ResultText;

public class MainCharacter implements Actor {
    private ActorState actorState;
    private Room currentRoom;
    private Room saveRoom;
    private ResultText previousResult;
    private ResultText monsterProgressResult;
    private final Inventory inventory;
    private boolean isQuitting;
    private int monsterTimer;

    public MainCharacter() {
        this.actorState = new ExploreState(this);
        this.previousResult = new ResultText("");
        this.isQuitting = false;
        this.inventory = new Inventory();
        this.monsterTimer = 10;
    }

    // ActorState
    public void changeState(ActorState newState) { actorState = newState; }
    public ActorState getActorState(){
        return actorState;
    }
    public ActorStateType getActorStateType() { return actorState.getActorStateType(); }

    // ResultText
    public void setResultText(ResultText result) {
        this.previousResult = result;
    }
    public ResultText getResultText() {
        return previousResult;
    }
    public void setMonsterProgressResult(ResultText result) { this.monsterProgressResult = result; }
    public ResultText getMonsterProgressResult() { return monsterProgressResult; }

    // Room Management
    public void setCurrentRoom(Room selectedRoom) {
        this.currentRoom = selectedRoom;
    }
    public Room getCurrentRoom() {
        return currentRoom;
    }
    public void setSaveRoom(Room selectedRoom) {
        this.saveRoom = selectedRoom;
        this.resetMonsterTimer();
    }
    public Room getSaveRoom() { return saveRoom; }

    // Inventory Management
    public Inventory getInventory() {
        return inventory;
    }

    // Hiding Logic
    public boolean isHidden(){
        return this.getActorState().getActorStateType() == ActorStateType.HIDE;
    }
    public void resetMonsterTimer() {
        this.monsterTimer = 10;
        this.monsterProgressResult = null;
    }
    public void decrementMonsterTimer() { this.monsterTimer--; }
    public int getMonsterTimer() { return monsterTimer; }

    // Quit Flag
    public void quit() { isQuitting = true; }
    public boolean isQuitting() {
        return isQuitting;
    }
}